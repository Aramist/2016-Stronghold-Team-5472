package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotMap {

	private enum XBOX {
		A(1), B(2), X(3), Y(4), LSTICK(9), RSTICK(1), LB(5), RB(6), START(8), BACK(7), LYAXIS(1), LXAXIS(0), RYAXIS(
				5), RXAXIS(4), RT(3), LT(2);
		private int i;

		private XBOX(int i) {
			this.i = i;
		}

		public int id() {
			return this.i;
		}
	}

	// Motors: Drive

	// 0 - Basket
	// 1 - Left Drive
	// 2 - Right Drive
	// 3 - Shooting Right
	// 4 - Shooting Left
	// 5 - Left Arm
	// 6 - Right Arm

	private static final int mFrontLeft = 0;
	private static final int mFrontRight = 3;
	private static final int mBackLeft = 1;
	private static final int mBackRight = 2;

	// Motors: Arms
	// TODO replace placeholder

	// Motors: Firing
	private static final int fiMotor = 4;
	private static final int fMotor = 5;
	private static final int aim = 6;

	// Motor Definitions
	public static final VictorSP driveFrontLeft = new VictorSP(mFrontLeft);
	public static final VictorSP driveFrontRight = new VictorSP(mFrontRight);
	public static final VictorSP driveBackLeft = new VictorSP(mBackLeft);
	public static final VictorSP driveBackRight = new VictorSP(mBackRight);
	public static final VictorSP fireMotor = new VictorSP(fiMotor);
	public static final VictorSP feedMotor = new VictorSP(fMotor);
	public static final VictorSP aimMotor = new VictorSP(aim);

	// Driver 1 Assignments
	public static final int feedControl = 5;
	public static final int fireControl = 6;
	public static final int shiftGear = 1;
	// public static final int switchCamera = 6;
	public static final int twistAxis = 2;

	// Driver 2 Assignments
	// public static final int triggerAxisControl_x = XBOX.LT.id();
	// public static final double triggerThreshold = 0.8;
	public static final int shootOutAxisControl_x = XBOX.RT.id();
	public static final double shootOutThreshold_x = 0.8;

	public static final int d_2y = XBOX.LYAXIS.id();
	public static final int d_2x = XBOX.LXAXIS.id();

	public static final int aimAxisControl = XBOX.RYAXIS.id();
	public static final int feedInControl_x = XBOX.LB.id();
	public static final int feedOutControl_x = XBOX.LT.id();
	public static final int basketUp_x = XBOX.A.id();
	public static final int basketDown_x = XBOX.X.id();
	public static final int shiftLowGear_x = XBOX.B.id();
	public static final int shiftHighGear_x = XBOX.Y.id();

	// Solenoids
	public static final int gear = 0;
	public static final int shooterSolenoidA = 1;
	public static final int shooterSolenoidB = 2;

	// Solenoid Definitions
	public static final Solenoid gearSolenoid = new Solenoid(gear);
	public static final DoubleSolenoid shootingSolenoid = new DoubleSolenoid(shooterSolenoidA, shooterSolenoidB);
}