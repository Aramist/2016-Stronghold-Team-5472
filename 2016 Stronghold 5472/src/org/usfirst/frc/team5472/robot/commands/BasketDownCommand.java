package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class BasketDownCommand extends Command {

	private boolean willRun;

	public BasketDownCommand() {
	}

	@Override
	protected void initialize() {
		if (RobotMap.shootingSolenoid.get().equals(Value.kReverse)) {
			willRun = false;
		} else
			willRun = true;
	}

	@Override
	protected void execute() {
		if (!willRun)
			return;
		RobotMap.shootingSolenoid.set(Value.kReverse);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		RobotMap.shootingSolenoid.set(Value.kOff);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

}
