
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.AutonomousCommand;
import org.usfirst.frc.team5472.robot.commands.BasketDownCommand;
import org.usfirst.frc.team5472.robot.commands.BasketUpCommand;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static HardwareTimer ht = new HardwareTimer();
	public static OI oi;
	public static AHRS motion;
	public static DriverStation driverStation = DriverStation.getInstance();
	Command autonomousCommand = null;
	// public CameraServer server;
	public boolean triggerRunning = false;
	public boolean cannonUp = false;
	public boolean cannonActive = false;
	public boolean liftActive = false;
	public boolean stopped = false;
	public boolean driver_2 = false;
	public AnalogInput pressureSensor;

	@Override
	public void robotInit() {
		oi = new OI();
		motion = new AHRS(SPI.Port.kMXP);
		pressureSensor = new AnalogInput(0);
		motion.zeroYaw();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		RobotMap.driveBackLeft.setInverted(true);
		RobotMap.driveFrontLeft.setInverted(true);
		RobotMap.driveBackRight.setInverted(false);
		RobotMap.driveFrontRight.setInverted(false);
		motion.zeroYaw();
		new AutonomousCommand().start();

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// cams.get(currentCamera).getImage(i);
		// CameraServer.getInstance().setImage(i);
	}

	@Override
	public void teleopInit() {
		// CameraServer.getInstance().setQuality(40);
		// if (!CameraServer.getInstance().isAutoCaptureStarted())
		// CameraServer.getInstance().startAutomaticCapture("cam0");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		RobotMap.driveBackLeft.setInverted(true);
		RobotMap.driveFrontLeft.setInverted(true);
		RobotMap.driveBackRight.setInverted(false);
		RobotMap.driveFrontRight.setInverted(false);
	}

	@Override
	public void disabledInit() {
	}

	/**
	 * Driver 1: - All Driving - Arms
	 * 
	 * Driver 2: - Shooting - Wench
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// if (oi.getJoystickArray()[0].getRawButton(RobotMap.switchCamera)) {
		// cams.get(currentCamera).stopCapture();
		// ht.delay(0.1);
		// currentCamera++;
		// currentCamera %= cams.size();
		// cams.get(currentCamera).startCapture();
		// }
		// cams.get(currentCamera).setExposureManual((int)
		// SmartDashboard.getNumber("Exposure"));
		// cams.get(currentCamera).setWhiteBalanceManual((int)
		// SmartDashboard.getNumber("White Balance"));
		// cams.get(currentCamera).setBrightness((int)
		// SmartDashboard.getNumber("Brightness"));
		// cams.get(currentCamera).getImage(i);
		SmartDashboard.putNumber("Velocity", motion.getVelocityZ());
		double pressureValue = (250.0 * (pressureSensor.getVoltage() / 4.95)) - 25.0;
		SmartDashboard.putNumber("Pressure", pressureValue);
		SmartDashboard.putNumber("Yaw", motion.getYaw());
		if (!oi.getJoystickArray()[0].getIsXbox())
			joyd_1();
		if (oi.getJoystickArray()[1].getIsXbox())
			xboxd_2();

		ht.delay(0.020D);

	}

	public void joyd_1() {

		twistDrive();
		if (oi.getJoystickArray()[0].getRawButton(RobotMap.shiftGear)) {
			RobotMap.gearSolenoid.set(true);
			Timer.delay(0.05);
		} else {
			RobotMap.gearSolenoid.set(false);
			Timer.delay(0.05);
		}
	}

	public void xboxd_2() {

		if (oi.getJoystickArray()[1].getRawButton(RobotMap.basketUp_x)) {
			if (!RobotMap.shootingSolenoid.get().equals(Value.kForward)) {
				RobotMap.shootingSolenoid.set(Value.kForward);
				Timer.delay(0.01);
			}
		}

		if (oi.getJoystickArray()[1].getRawButton(RobotMap.basketDown_x)) {
			if (!RobotMap.shootingSolenoid.get().equals(Value.kReverse)) {
				RobotMap.shootingSolenoid.set(Value.kReverse);
				Timer.delay(0.05);
			}
		}

		RobotMap.aimMotor.set(oi.getJoystickArray()[1].getRawAxis(RobotMap.aimAxisControl) / 2.0);

		new JoystickButton(oi.getJoystickArray()[1], RobotMap.basketUp_x).whenPressed(new BasketUpCommand());
		new JoystickButton(oi.getJoystickArray()[1], RobotMap.basketDown_x).whenPressed(new BasketDownCommand());

		double y = oi.getJoystickArray()[1].getRawAxis(RobotMap.d_2y);
		double t = oi.getJoystickArray()[1].getRawAxis(RobotMap.d_2x);

		double tankRight, tankLeft, twist;
		tankLeft = tankRight = -y;
		twist = -t / 2;
		tankLeft -= twist / (1.25);
		tankRight += twist / (1.25);
		tankLeft *= 1;
		tankRight *= 1;
		tankLeft = clip(-1.0, 1.0, tankLeft);
		tankRight = clip(-1.0, 1.0, tankRight);
		tankLeft *= -1;
		driver_2 = true;
		if (Math.abs(y + t) < 0.200) {
			y = t = 0;
			driver_2 = false;
		}
		SmartDashboard.putNumber("left", tankLeft);
		SmartDashboard.putNumber("right", tankRight);
		SmartDashboard.putNumber("twist", twist);
		if (driver_2) {
			RobotMap.driveFrontLeft.set(tankLeft);
			RobotMap.driveFrontRight.set(tankRight);
			RobotMap.driveBackLeft.set(tankLeft);
			RobotMap.driveBackRight.set(tankRight);
		}
		boolean a = oi.getJoystickArray()[1].getRawAxis(RobotMap.shootOutAxisControl_x) >= RobotMap.shootOutThreshold_x;
		boolean b = oi.getJoystickArray()[1].getRawButton(RobotMap.feedInControl_x);
		boolean c = oi.getJoystickArray()[1].getRawButton(RobotMap.feedOutControl_x);
		if (a) {
			RobotMap.fireMotor.set(-1.00);
			// RobotMap.shootingSolenoid.set(Value.kForward);
			rumble(true, true);
		}
		if (b) {
			RobotMap.feedMotor.set(1.00);
			rumble(false, true);
		} else if (c) {
			RobotMap.feedMotor.set(-1.00);
			rumble(false, true);
		}
		if (!a && !b && !c) {
			RobotMap.fireMotor.set(0.00);
			RobotMap.feedMotor.set(0.00);
			rumble(false, false);
		}
	}

	public void rumble(boolean heavy, boolean on) {
		if (on)
			oi.getJoystickArray()[1].setRumble(!heavy ? RumbleType.kRightRumble : RumbleType.kLeftRumble, 1.0F);
		else {
			oi.getJoystickArray()[1].setRumble(RumbleType.kLeftRumble, 0.0F);
			oi.getJoystickArray()[1].setRumble(RumbleType.kRightRumble, 0.0F);
		}
	}

	public void twistDrive() {
		if (driver_2)
			return;
		Joystick[] ja = oi.getJoystickArray();
		double tankRight, tankLeft, twist;
		double y = -ja[0].getY();
		tankLeft = tankRight = y;
		twist = -ja[0].getRawAxis(RobotMap.twistAxis);
		if (y > 0.1 || y < -0.1)
			if (twist < 0.3 && twist > -0.3)
				twist = 0;
		if (twist > 0.3) {
			twist = (twist - 0.3) * (10.0 / 7.0);
		}
		if (twist < -0.3) {
			twist = (twist + 0.3) * (10.0 / 7.0);
		}
		tankLeft -= twist / (1.1);
		tankRight += twist / (1.1);
		tankLeft *= 1;
		tankRight *= 1;
		tankLeft = clip(-1.0, 1.0, tankLeft);
		tankRight = clip(-1.0, 1.0, tankRight);
		tankLeft *= -1;
		SmartDashboard.putNumber("d1left", tankLeft);
		SmartDashboard.putNumber("d1right", tankRight);
		RobotMap.driveFrontRight.set(tankRight);
		RobotMap.driveFrontLeft.set(tankLeft);
		RobotMap.driveBackLeft.set(tankLeft);
		RobotMap.driveBackRight.set(tankRight);
		SmartDashboard.putNumber("twist", twist);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public static double clip(double min, double max, double num) {
		if (num > max)
			return max;
		else if (num < min)
			return min;
		else
			return num;
	}
	/**
	 * "Y'all don't know what a tough English course is!" - Anna Darwish (2016)
	 */
}