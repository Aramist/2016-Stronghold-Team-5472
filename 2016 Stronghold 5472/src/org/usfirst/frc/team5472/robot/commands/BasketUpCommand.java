package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class BasketUpCommand extends Command {

	private boolean willRun;

	public BasketUpCommand() {
	}

	@Override
	protected void initialize() {
		if (RobotMap.shootingSolenoid.get().equals(Value.kForward)) {
			willRun = false;
		} else
			willRun = true;
	}

	@Override
	protected void execute() {
		if (!willRun)
			return;
		RobotMap.shootingSolenoid.set(Value.kForward);
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
		this.end();
	}

}
