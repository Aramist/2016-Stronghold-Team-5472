package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class OI {
	private Joystick[] joystickArray = new Joystick[2];

	public OI() {
		/*
		 * if(DriverStation.getInstance().getJoystickName(1).toLowerCase().
		 * contains("xbox")) joystickArray[1] = new Joystick(1); else
		 * joystickArray[1] = new Joystick(0);
		 * if(DriverStation.getInstance().getJoystickName(0).toLowerCase().
		 * contains("mad")) joystickArray[0] = new Joystick(0); else
		 * joystickArray[0] = new Joystick(1);
		 */

		// if
		// (DriverStation.getInstance().getJoystickName(1).toLowerCase().contains("xbox"))
		// joystickArray[1] = new Joystick(1);
		// else
		// joystickArray[1] = new Joystick(0);
		// if
		// (DriverStation.getInstance().getJoystickName(0).toLowerCase().contains("mad"))
		// joystickArray[0] = new Joystick(0);
		joystickArray[0] = new Joystick(0);
		joystickArray[1] = new Joystick(1);

		// JoystickButton reset = new JoystickButton(joystickArray[0],
		// RobotMap.resetButton);
		// reset.whenPressed(new ResetMotionDataCommand());
	}

	public Joystick[] getJoystickArray() {
		return this.joystickArray;
	}

	public void updateJoysticks() {
		if (DriverStation.getInstance().getJoystickName(1).toLowerCase().contains("xbox"))
			joystickArray[1] = new Joystick(1);
		else
			joystickArray[1] = new Joystick(0);
		if (DriverStation.getInstance().getJoystickName(0).toLowerCase().contains("mad"))
			joystickArray[0] = new Joystick(0);
		else
			joystickArray[0] = new Joystick(1);
	}
}
