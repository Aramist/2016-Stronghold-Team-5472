package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.internal.HardwareTimer;

public class AutonomousCommand extends Command {

	boolean finished = false;
	private double startingYaw = 0.0;
	private long motorStart = 0L;
	private HardwareTimer ht = new HardwareTimer();

	@Override
	protected void initialize() {
		Robot.motion.zeroYaw();
		startingYaw = 0;
	}

	@Override
	protected void execute() {
		System.out.println("Starting autonomous execution");
		Robot.motion.zeroYaw();
		RobotMap.shootingSolenoid.set(Value.kReverse);
		RobotMap.armMotorLeft.set(0.1);
		RobotMap.armMotorRight.set(0.1);
		ht.delay(1.0);
		RobotMap.driveFrontLeft.set(-1.0D);
		RobotMap.driveFrontRight.set(1.0D);
		RobotMap.driveBackLeft.set(-1.0D);
		RobotMap.driveBackRight.set(1.0D);
		System.out.println("Starting Motor loop");
		motorStart = System.currentTimeMillis();
		loopMotors();
		System.out.println("Ending Motor loop");
		RobotMap.driveFrontLeft.set(0.0);
		RobotMap.driveFrontRight.set(0.0);
		RobotMap.driveBackLeft.set(0.0);
		RobotMap.driveBackRight.set(0.0);
		RobotMap.armMotorLeft.set(0.0);
		RobotMap.armMotorRight.set(0.0);
		finished = true;
		System.out.println("Autonomous ended");
	}

	public void loopMotors() {
		double tankLeft, tankRight, twist;
		tankLeft = tankRight = 1.0D;
		twist = (Robot.motion.getYaw() - this.startingYaw) / 30;
		tankLeft -= twist;
		tankRight += twist;
		tankLeft = Robot.clip(-1.0, 1.0, tankLeft);
		tankRight = Robot.clip(-1.0, 1.0, tankRight);
		RobotMap.driveFrontLeft.set(-tankLeft);
		RobotMap.driveFrontRight.set(tankRight);
		RobotMap.driveBackLeft.set(-tankLeft);
		RobotMap.driveBackRight.set(tankRight);
		if (Math.abs(System.currentTimeMillis() - this.motorStart) >= 4000L)
			return;
		else {
			ht.delay(0.020);
			loopMotors();
		}
	}

	@Override
	protected boolean isFinished() {

		return finished;
	}

	@Override
	protected void end() {
		finished = true;
	}

	@Override
	protected void interrupted() {
		end();
	}

}
