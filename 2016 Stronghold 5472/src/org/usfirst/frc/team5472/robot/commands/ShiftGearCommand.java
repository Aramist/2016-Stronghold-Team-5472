package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearCommand extends Command {

	private static boolean active = false;

	public ShiftGearCommand() {
		super();
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (active) {
			// deactivate
			RobotMap.gearSolenoid.set(false);
		} else {
			// activate
			RobotMap.gearSolenoid.set(true);
		}
		active = !active;
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
