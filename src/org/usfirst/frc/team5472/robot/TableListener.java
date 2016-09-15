package org.usfirst.frc.team5472.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class TableListener implements ITableListener {
	

	public AxisCamera camera;
	public CameraServer server;
	private Image frame;
	
	private long last = System.currentTimeMillis();
	private double distance;
	private double angle;
	private int[] size;
	private int[] tl;
	
	
	public TableListener(String ip){
		camera = new AxisCamera(ip);
		server = CameraServer.getInstance();
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0);
		
		distance = 0.0;
		angle = 0.0;
		size = new int[]{0,0};
		tl = new int[]{0,0};
	}
	
	public double getDistance(){
		return distance;
	}
	
	public double getAngle(){
		return angle;
	}
	
	public int[] getSize(){
		return size;
	}
	
	public int[] getTl(){
		return tl;
	}
	
	public long lastUpdate(){
		return this.last;
	}
	
	@Override
	public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
		if(arg3 && camera.isFreshImage()){
			if(arg1.equalsIgnoreCase("distance")){
				distance = arg0.getNumber(arg1, 0.0);
			}else if(arg1.equalsIgnoreCase("azumith")){
				angle = arg0.getNumber(arg1, 0.0);
			}else if(arg1.equalsIgnoreCase("rect")){
				int[] arr = (int[]) arg2;
				tl[0] = arr[0];
				tl[1] = arr[1];
				size[0] = arr[2];
				size[1] = arr[3];
			}else{
				return;
			}
			camera.getImage(getFrame());
			NIVision.imaqDrawShapeOnImage(getFrame(), getFrame(), new Rect(tl[1],tl[0],size[1],size[0]), DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
			server.setImage(getFrame());
			last = System.currentTimeMillis();
		}
	}

	public Image getFrame() {
		return frame;
	}
}
