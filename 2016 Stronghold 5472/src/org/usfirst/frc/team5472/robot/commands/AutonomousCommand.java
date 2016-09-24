package org.usfirst.frc.team5472.robot.commands;

public class AutonomousCommand {
}
//
// import org.usfirst.frc.team5472.robot.Robot;
// import org.usfirst.frc.team5472.robot.RobotMap;
//
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.internal.HardwareTimer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
// public class AutonomousCommand extends Command {
//
// boolean finished = false;
// private double startingYaw = 0.0;
// private long motorStart = 0L;
// private HardwareTimer ht = new HardwareTimer();
//
// @Override
// protected void initialize() {
// // TODO Auto-generated method stub
// Robot.motion.zeroYaw();
// startingYaw = 0;
// }
//
// @Override
// protected void execute() {
// ht.delay(5);
// Robot.motion.zeroYaw();
// // TODO Auto-generated method stub
// RobotMap.armMotorLeft.set(0.1);
// RobotMap.armMotorRight.set(0.1);
// HardwareTimer ht = new HardwareTimer();
// ht.delay(1.5);
// RobotMap.driveFrontLeft.set(-1.0D);
// RobotMap.driveFrontRight.set(1.0D);
// RobotMap.driveBackLeft.set(-1.0D);
// RobotMap.driveBackRight.set(1.0D);
// SmartDashboard.putBoolean("Autonomous Motors", true);
// motorStart = System.currentTimeMillis();
// loopMotors();
// SmartDashboard.putBoolean("Autonomous Motors", false);
// RobotMap.driveFrontLeft.set(0.0);
// RobotMap.driveFrontRight.set(0.0);
// RobotMap.driveBackLeft.set(0.0D);
// RobotMap.driveBackRight.set(0.0D);
// RobotMap.armMotorLeft.set(0.0);
// RobotMap.armMotorRight.set(0.0);
// finished = true;
// }
//
// public void loopMotors() {
// // Clockwise is positive
// double tankLeft, tankRight, twist;
// tankLeft = tankRight = 1.0D;
// twist = (Robot.motion.getYaw() - this.startingYaw) / 30;
// tankLeft -= twist;
// tankRight += twist;
// tankLeft = Robot.clip(-1.0, 1.0, tankLeft);
// tankRight = Robot.clip(-1.0, 1.0, tankRight);
// RobotMap.driveFrontLeft.set(-tankLeft);
// RobotMap.driveFrontRight.set(tankRight);
// RobotMap.driveBackLeft.set(-tankLeft);
// RobotMap.driveBackRight.set(tankRight);
// if (Math.abs(System.currentTimeMillis() - this.motorStart) >= 5000L)
// return;
// else {
// ht.delay(0.020);
// loopMotors();
// }
// }
//
// @Override
// protected boolean isFinished() {
// // TODO Auto-generated method stub
// return finished;
// }
//
// @Override
// protected void end() {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// protected void interrupted() {
// // TODO Auto-generated method stub
//
// }
//
// }
