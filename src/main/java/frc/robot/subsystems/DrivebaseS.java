/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;

/**
 * Add your docs here.
 */
public class DrivebaseS extends Subsystem {
  private WPI_VictorSPX driveLeftFront = null;
  private WPI_VictorSPX driveLeftRear = null;
  private WPI_VictorSPX driveRightFront = null;
  private WPI_VictorSPX driveRightRear = null;

  private DifferentialDrive differentialDrive = null;

  public double rotThrot = 0.68;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC());
  }

  public DrivebaseS() {
    driveLeftFront = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_FRONT);
    driveLeftRear = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_REAR);
    driveRightFront = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_FRONT);
    driveRightRear = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_REAR);

    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);

    driveLeftRear.configFactoryDefault();
    driveLeftFront.configFactoryDefault();
    driveRightRear.configFactoryDefault();
    driveRightFront.configFactoryDefault();

    driveLeftRear.follow(driveLeftFront);
    driveRightRear.follow(driveRightFront);

    driveLeftFront.setInverted(false);
    driveLeftRear.setInverted(InvertType.FollowMaster);
    driveRightFront.setInverted(true);
    driveRightRear.setInverted(InvertType.FollowMaster);

    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftRear.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightRear.setNeutralMode(NeutralMode.Brake);

    differentialDrive.setRightSideInverted(false);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    //Rotation throttle disabled per driver request
    //Keep in mind for other usage of arcadeDrive
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * rotThrot);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }
}
