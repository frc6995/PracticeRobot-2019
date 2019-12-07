package frc.robot;

public class RobotMap {
  //Input Devices
  public final static int OI_XBOX = 0;
  public final static int OI_BUTTONBOARD = 1;
  //CargoIntake Constants
  public final static int PWM_ID_SPARK_HAND = 0; //I don't know what these ports are
  public final static int DIO_LIMIT_HAND = 1; //or what way they are plugged in

  public static final int CAN_ID_VSPX_DRIVEBASE_LEFT_FRONT = 10;
  public static final int CAN_ID_VSPX_DRIVEBASE_LEFT_REAR = 11;
  public static final int CAN_ID_VSPX_DRIVEBASE_RIGHT_FRONT = 12;
  public static final int CAN_ID_VSPX_DRIVEBASE_RIGHT_REAR = 13;

  public static final int PCM_ID = 1;
  public static final int PWM_ID_SPARK_WHEELS = 1;
  public static final int PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY = 0;
  public static final int PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT = 1;
  public static final int PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY = 2;
  public static final int PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT = 3;

}
