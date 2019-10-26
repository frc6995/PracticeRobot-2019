package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;

public class DrivebaseS extends Subsystem {

  private WPI_VictorSPX driveLeftFront = null;
  private WPI_VictorSPX driveLeftRear = null;
  private WPI_VictorSPX driveRightFront = null;
  private WPI_VictorSPX driveRightRear = null;

  private DifferentialDrive differentialDrive = null;

  public double rotThrot = 0.68; //This controls how fast we can spin.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC()); //this starts the drive command running.
  }

  public DrivebaseS() {
    //initialize the 4 motor controllers
    driveLeftFront = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_FRONT);
    driveLeftRear = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_REAR);
    driveRightFront = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_FRONT);
    driveRightRear = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_REAR);

    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);

    //make sure all of the configs are default
    driveLeftRear.configFactoryDefault();
    driveLeftFront.configFactoryDefault();
    driveRightRear.configFactoryDefault();
    driveRightFront.configFactoryDefault();

    //make the neccessary follower setup
    driveLeftRear.follow(driveLeftFront);
    driveRightRear.follow(driveRightFront);

    //invert the right side
    driveLeftFront.setInverted(false);
    driveLeftRear.setInverted(InvertType.FollowMaster);
    driveRightFront.setInverted(true);
    driveRightRear.setInverted(InvertType.FollowMaster);

    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftRear.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightRear.setNeutralMode(NeutralMode.Brake);

    //the right side was already inverted above
    differentialDrive.setRightSideInverted(false);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    //Rotation throttle disabled per driver request
    //Keep in mind for other usage of arcadeDrive
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * rotThrot);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //Vision Drive has no motor deadzones. If we don't use vision on Patrick, remove
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }
}
