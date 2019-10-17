package frc.robot;

public class RobotMap {

  //Drivebase constants
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_FRONT = 1;
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_REAR = 2;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_FRONT = 3;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_REAR = 4;

  //Input Devices
  /** 
   * Ports are determined by USB ports
  */
  public final static int            OI_XBOX                              = 0;
  public final static int            OI_JSTICK                            = 0;
  //Climb Constants
  /**
   * consists of 
   */
  public final static int            DIO_CLIMB_REAR_LIMIT                 = 0;
  public final static int            DIO_CLIMB_FRONT_LIMIT                = 0;

  public final static int            PCM_ID_DSOLENOID_CLIMB_FRONT_MODULE  = 0;

  public final static int            PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY  = 0;
  public final static int            PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT = 0;
  public final static int            PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY   = 0;
  public final static int            PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT  = 0;

  public final static int            PWM_ID_SPARK_WHEELS                  = 0;
  //Arm Constants
  public final static int            CAN_ID_TALON_ARM_A                   = 0;
  public final static int            CAN_ID_TALON_ARM_B                   = 0;
  public final static int            DIO_LIMIT_ARM_UPPER                  = 0;
  public final static int            DIO_LIMIT_ARM_LOWER                  = 0;
  //Arm level encoder Constants
  public final static int            ARM_HOME                             = 0;
  public final static int            ARM_ROCKET                           = 500;
  public final static int            ARM_SHIP                             = 1000;
  public final static int            ARM_REVERSE_ROCKET                   = 1500;
  public final static int            ARM_REVERSE_SHIP                     = 2000;
  //Arm PID Constants
  public final static int            ARM_PID_SLOT                         = 0;

  public final static int            ARM_INTEGRAL_ZONE                    = 1500;
  public final static double         ARM_PEAK_OUTPUT                      = 0.2; //peak motor power.
  public final static double         ARM_RAMP_TIME                        = 0; //time to ramp to full power in seconds.

  public final static double         ARM_kP                               = 0.0;
  public final static double         ARM_kI                               = 0.0;
  public final static double         ARM_kD                               = 0.0;
  public final static double         ARM_kF                               = 0.0; //a constant feed forward (in motor power), this is to account for friction or any other constant force on the system
    
  public final static double         ARM_kF_nB                            = 0.0; //power needed to keep arm up without a ball;
  public final static double         ARM_kF_B                             = 0.0; //power needed to keep arm up with ball.
  //Arm Motor Constants
  public final static int            MOTOR_A_TALONID                      = RobotMap.CAN_ID_TALON_ARM_A;
  public final static int            MOTOR_B_TALONID                      = RobotMap.CAN_ID_TALON_ARM_B;

  public final static int            FACTORY_DEFAULT_TIMEOUT              = 100; //in ms

  public final static boolean        CURRENT_LIMIT_ENABLED                = false;
  public final static int            CURRENT_LIMIT_VALUE                  = 40;
  public final static int            CURRENT_PEAK_LIMIT_VALUE             = 50;
  public final static int            CURRENT_PEAK_LIMIT_DURATION          = 100; //in ms

  public final static boolean        MOTOR_A_INVERT                       = false;
  public final static boolean        MOTOR_B_INVERT                       = false;

  public final static boolean        FEEDBACK_DEVICE_INVERT               = false;

  public final static boolean        LIMIT_SOFT_ENABLED                   = true;
  public final static int            LIMIT_SOFT_TIMEOUT                   = 100; //in ms
  public final static int            LIMIT_SOFT_FORWARD                   = 1000; //in encoder units
  public final static int            LIMIT_SOFT_REVERSE                   = -100; //in encoder units

  public final static int            ENCODER_DUTY_CYCLE                   = 4096; //Number of counts per rotation
  public final static int            ENCODER_POS_HORIZONTAL               = 0; //Encoder position when the arm is horizontal.
  public final static double         ENCODER_TICKS_PER_DEG                = ENCODER_DUTY_CYCLE / 360;
  //Subsubsystem Claw Constants
  /**
   * consists of limit switch and spark
   */
  public final static int            PWM_ID_SPARK_HAND                    = 0;
  public final static int            DIO_LIMIT_HAND                       = 0;
}
