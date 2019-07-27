package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RobotMap {
  //Input Devices
  public final static int OI_XBOX = 0;
  public final static int OI_BUTTONBOARD = 0;
  //CargoIntake Constants
  public final static int PWM_ID_SPARK_HAND = 0; //I don't know what these ports are
  public final static int DIO_LIMIT_HAND = 0; //or what way they are plugged in
  //Arm Constants
  public final static int CAN_ID_TALON_ARM_A = 0;
  public final static int CAN_ID_TALON_ARM_B = 0;
  public final static int DIO_LIMIT_ARM_UPPER = 0;
  public final static int DIO_LIMIT_ARM_LOWER = 0;
  //Arm level encoder Constants
  public final static int ARM_HOME = 0;
  public final static int ARM_ROCKET = 500;
  public final static int ARM_SHIP = 1000;

  

  /* PID Variables/Constants*/

  private final int     LADDER_PID_SLOT = 0;

  private final int     INTEGRAL_ZONE   = 1500;
  private final double  PEAK_OUTPUT     = 0.2; //peak motor power.
  private final double  RAMP_TIME       = 0; //time to ramp to full power in seconds.

  private final double  kP              = 0.0;
  private final double  kI              = 0.0;
  private final double  kD              = 0.0;
  private final double  kF              = 0.0; //a constant feed forward (in motor power), this is to account for friction or any other constant force on the system
  
  private final double  kF_nB           = 0.0; //power needed to keep arm up without a ball;
  private final double  kF_B            = 0.0; //power needed to keep arm up with ball.
  private       double  kF_a            = 0.0; //Arbitray feed forward value. This is calculated as the PID is running.

  /*Motor Constants*/
  private final int               MOTOR_A_TALONID             = RobotMap.CAN_ID_TALON_ARM_A;
  private final int               MOTOR_B_TALONID             = RobotMap.CAN_ID_TALON_ARM_B;

  private final int               FACTORY_DEFAULT_TIMEOUT     = 100; //in ms

  private final boolean           CURRENT_LIMIT_ENABLED       = false;
  private final int               CURRENT_LIMIT_VALUE         = 40;
  private final int               CURRENT_PEAK_LIMIT_VALUE    = 50;
  private final int               CURRENT_PEAK_LIMIT_DURATION = 100; //in ms

  private final boolean           MOTOR_A_INVERT              = false;
  private final boolean           MOTOR_B_INVERT              = false;

  private final NeutralMode       MOTOR_A_NEUTRAL_MODE        = NeutralMode.Brake;
  private final NeutralMode       MOTOR_B_NEUTRAL_MODE        = NeutralMode.Brake;

  private final FeedbackDevice    FEEDBACK_DEVICE             = FeedbackDevice.QuadEncoder;
  private final boolean           FEEDBACK_DEVICE_INVERT      = false;

  private final boolean           LIMIT_SOFT_ENABLED          = true;
  private final int               LIMIT_SOFT_TIMEOUT          = 100; //in ms
  private final int               LIMIT_SOFT_FORWARD          = 1000; //in encoder units
  private final int               LIMIT_SOFT_REVERSE          = -100; //in encoder units

  private final int               ENCODER_DUTY_CYCLE          = 4096; //Number of counts per rotation
  private final int               ENCODER_POS_HORIZONTAL      = 0; //Encoder position when the arm is horizontal.
  private final double            ENCODER_TICKS_PER_DEG       = ENCODER_DUTY_CYCLE / 360;

  private       double            angleCos                    = 0;

  
}
