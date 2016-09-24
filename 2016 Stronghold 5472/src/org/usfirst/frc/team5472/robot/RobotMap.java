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

	public static final int mFrontLeft = 1;
	public static final int mFrontRight = 2;
	public static final int mBackLeft = 3;
	public static final int mBackRight = 4;

	// Motors: Arms
	public static final int armL = 5;
	public static final int armR = 6;

	// Motors: Firing
	public static final int fireMotorR = 7;
	public static final int fireMotorL = 8;

	// Motor Definitions
	public static final VictorSP driveFrontLeft = new VictorSP(mFrontLeft);
	public static final VictorSP driveFrontRight = new VictorSP(mFrontRight);
	public static final VictorSP driveBackLeft = new VictorSP(mBackLeft);
	public static final VictorSP driveBackRight = new VictorSP(mBackRight);
	public static final VictorSP fireMotorLeft = new VictorSP(fireMotorL);
	public static final VictorSP fireMotorRight = new VictorSP(fireMotorR);
	public static final VictorSP armMotorLeft = new VictorSP(armL);
	public static final VictorSP armMotorRight = new VictorSP(armR);

	// Driver 1 Assignments
	public static final int joystickId = 0;
	public static final int armsUpControl = 0;
	public static final int armsDownControl = 180;
	public static final int spinInControl = 5;
	public static final int spinOutControl = 6;
	public static final int aimUpControl = 3;
	public static final int shiftGear = 1;
	// public static final int switchCamera = 6;
	public static final int twistAxis = 2;

	// Driver 2 Assignments
	public static final int triggerAxisControl_x = XBOX.RT.id();
	public static final double triggerThreshold = 0.8;
	public static final int spinOutAxisControl_x = XBOX.LT.id();
	public static final double spinOutThreshold_x = 0.8;

	public static final int d_2y = XBOX.LYAXIS.id();
	public static final int d_2x = XBOX.LXAXIS.id();

	public static final int spinInControl_x = XBOX.LB.id();
	public static final int basketUp_x = XBOX.A.id();
	public static final int basketDown_x = XBOX.B.id();

	public static final int spinInControl_2 = 2;
	public static final int spinOutControl_2 = 3;
	public static final int trigger_2 = 1;

	// Solenoids
	public static final int fire = 0;
	public static final int shooterSolenoidA = 1;
	public static final int shooterSolenoidB = 2;

	// Solenoid Definitions
	public static final Solenoid fireSolenoid = new Solenoid(fire);
	public static final DoubleSolenoid shootingSolenoid = new DoubleSolenoid(shooterSolenoidA, shooterSolenoidB);
}