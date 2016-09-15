package org.usfirst.frc.team5472.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ResetMotionDataCommand extends Command{

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		//Robot.motion.resetDisplacement();
		//Robot.motion.zeroYaw();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
}
