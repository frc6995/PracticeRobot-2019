/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public final static int OI_XBOX = 0;
  public final static int OI_BUTTONBOARD = 0;

  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_FRONT = 1;
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_REAR = 2;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_FRONT = 3;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_REAR = 4;
}
