
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.AutonomousCommand;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot{

	public static HardwareTimer ht = new HardwareTimer();
	public static OI oi;
	public static AHRS motion;
	public static DriverStation driverStation = DriverStation.getInstance();
	Command autonomousCommand = null;
	public boolean triggerRunning = false;
	public boolean cannonUp = false;
	public boolean cannonActive = false;
	public boolean liftActive = false;
	public boolean stopped = false;
	public boolean driver_2 = false;
	
	@Override
	public void robotInit() {
		oi = new OI();
		motion = new AHRS(SPI.Port.kMXP);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		RobotMap.armMotorLeft.setInverted(true);
		RobotMap.aimingMotor.setInverted(true);
		new AutonomousCommand().start();

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		RobotMap.armMotorLeft.setInverted(true);
		RobotMap.aimingMotor.setInverted(true);
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
		
		if (!oi.getJoystickArray()[0].getIsXbox())
			joyd_1();
		if (oi.getJoystickArray()[1].getIsXbox())
			xboxd_2();

		ht.delay(0.020D);

	}

	public boolean cancelWait(double seconds) {
		for (int i = 0; i <= seconds; i += 50) {
			if (oi.getJoystickArray()[1].getRawButton(RobotMap.cancelControl_x))
				return false;
			ht.delay(0.050);
		}
		return true;
	}

	public void joyd_1() {

		twistDrive();

		if (oi.getJoystickArray()[0].getPOV(0) == RobotMap.armsUpControl
				|| oi.getJoystickArray()[1].getPOV(0) == RobotMap.armsUpControl) {
			RobotMap.armMotorLeft.set(0.2);
			RobotMap.armMotorRight.set(0.2);
			return;
		} else if (oi.getJoystickArray()[0].getPOV(0) == RobotMap.armsDownControl
				|| oi.getJoystickArray()[1].getPOV(0) == RobotMap.armsDownControl) {
			RobotMap.armMotorLeft.set(-0.4);
			RobotMap.armMotorRight.set(-0.4);
		} else {
			RobotMap.armMotorLeft.set(0.08);
			RobotMap.armMotorRight.set(0.08);
		}

	}

	public void xboxd_2() {

		double aimValue = oi.getJoystickArray()[1].getRawAxis(RobotMap.aimAxisControl);

		if (oi.getJoystickArray()[0].getRawButton(RobotMap.aimUpControl)) {
			RobotMap.aimingMotor.set(0.6);
		} else
			RobotMap.aimingMotor
					.set(clip(-0.3D, 1.0D, (aimValue / 1.5 >= -0.1) ? (aimValue / 1.5 + 0.2) : (aimValue / 1.5)));
		SmartDashboard.putNumber("aimValue", RobotMap.aimingMotor.get());

		double y = oi.getJoystickArray()[1].getRawAxis(RobotMap.d_2y);
		double t = oi.getJoystickArray()[1].getRawAxis(RobotMap.d_2x);

		double tankRight, tankLeft, twist;
		tankLeft = tankRight = -y;
		twist = -t;
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
		if (driver_2) {
			RobotMap.driveFrontLeft.set(tankLeft);
			RobotMap.driveFrontRight.set(tankRight);
			RobotMap.driveBackLeft.set(tankLeft);
			RobotMap.driveBackRight.set(tankRight);
		}

		double trigger = oi.getJoystickArray()[1].getRawAxis(RobotMap.triggerAxisControl_x);
		if (trigger >= RobotMap.triggerThreshold && !this.triggerRunning) {
			rumble(true, true);
			RobotMap.fireSolenoid.set(true);
			rumble(true, false);
		} else {
			if (RobotMap.fireSolenoid.get())
				RobotMap.fireSolenoid.set(false);
		}

		if (oi.getJoystickArray()[1].getRawAxis(RobotMap.spinOutAxisControl_x) >= RobotMap.spinOutThreshold_x
				|| oi.getJoystickArray()[0].getRawButton(RobotMap.spinOutControl)) {
			RobotMap.fireMotorRight.set(-0.80D);
			RobotMap.fireMotorLeft.set(0.80D);
			rumble(true, true);
			SmartDashboard.putString("Firing Wheels", "forward");
		} else if (oi.getJoystickArray()[1].getRawButton(RobotMap.spinInControl_x)
				|| oi.getJoystickArray()[0].getRawButton(RobotMap.spinInControl)) {
			RobotMap.fireMotorRight.set(0.650D);
			RobotMap.fireMotorLeft.set(-0.650D);
			rumble(false, true);
			SmartDashboard.putString("Firing Wheels", "reverse");
		} else {
			RobotMap.fireMotorRight.set(0.0D);
			RobotMap.fireMotorLeft.set(0.0D);
			rumble(false, false);
			SmartDashboard.putString("Firing Wheels", "off");
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

	public void fire() {
		if (oi.getJoystickArray()[1].getRawAxis(RobotMap.triggerAxisControl_x) >= RobotMap.triggerThreshold)
			RobotMap.fireSolenoid.set(true);
		else
			RobotMap.fireSolenoid.set(false);
	}

	public void twistDrive() {
		if (driver_2)
			return;
		Joystick[] ja = oi.getJoystickArray();
		double tankRight, tankLeft, twist;
		tankLeft = tankRight = -ja[0].getY();
		twist = -ja[0].getRawAxis(RobotMap.twistAxis);
		tankLeft -= twist / (1.1);
		tankRight += twist / (1.1);
		tankLeft *= 1;
		tankRight *= 1;
		tankLeft = clip(-1.0, 1.0, tankLeft);
		tankRight = clip(-1.0, 1.0, tankRight);
		tankLeft *= -1;
		SmartDashboard.putNumber("left", tankLeft);
		SmartDashboard.putNumber("right", tankRight);
		RobotMap.driveFrontRight.set(tankRight);
		RobotMap.driveFrontLeft.set(tankLeft);
		RobotMap.driveBackLeft.set(tankLeft);
		RobotMap.driveBackRight.set(tankRight);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public double clip(double min, double max, double num) {
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