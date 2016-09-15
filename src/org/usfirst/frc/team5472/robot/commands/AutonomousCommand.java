package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousCommand extends Command {

	boolean finished = false;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.armMotorLeft.set(0.1);
		RobotMap.armMotorRight.set(0.1);
		HardwareTimer ht = new HardwareTimer();
		RobotMap.aimingMotor.set(0.6);
		ht.delay(1.5);
		RobotMap.driveFrontLeft.set(-1.0D);
		RobotMap.driveFrontRight.set(1.0D);
		SmartDashboard.putBoolean("Autonomous Motors", true);
		ht.delay(5.0);
		SmartDashboard.putBoolean("Autonomous Motors", false);
		RobotMap.aimingMotor.set(0.0);
		RobotMap.driveFrontLeft.set(0.0);
		RobotMap.driveFrontRight.set(0.0);
		RobotMap.armMotorLeft.set(0.0);
		RobotMap.armMotorRight.set(0.0);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
