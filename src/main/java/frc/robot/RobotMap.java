package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RobotMap {
  //Input Devices
  public final static int               OI_XBOX                     = 0;
  public final static int               OI_BUTTONBOARD              = 0;
  //CargoIntake Constants
  public final static int               PWM_ID_SPARK_HAND           = 0;
  public final static int               DIO_LIMIT_HAND              = 0;
  //Arm Constants
  public final static int               CAN_ID_TALON_ARM_A          = 0;
  public final static int               CAN_ID_TALON_ARM_B          = 0;
  public final static int               DIO_LIMIT_ARM_UPPER         = 0;
  public final static int               DIO_LIMIT_ARM_LOWER         = 0;
  //Arm level encoder Constants
  public final static int               ARM_HOME                    = 0;
  public final static int               ARM_ROCKET                  = 500;
  public final static int               ARM_SHIP                    = 1000;
  //Motor Constants
  public final static int               MOTOR_A_TALONID             = RobotMap.CAN_ID_TALON_ARM_A;
  public final static int               MOTOR_B_TALONID             = RobotMap.CAN_ID_TALON_ARM_B;

  public final static int               FACTORY_DEFAULT_TIMEOUT     = 100; //in ms

  public final static boolean           CURRENT_LIMIT_ENABLED       = false;
  public final static int               CURRENT_LIMIT_VALUE         = 40;
  public final static int               CURRENT_PEAK_LIMIT_VALUE    = 50;
  public final static int               CURRENT_PEAK_LIMIT_DURATION = 100; //in ms

  public final static boolean           MOTOR_A_INVERT              = false;
  public final static boolean           MOTOR_B_INVERT              = false;

  public final static NeutralMode       MOTOR_A_NEUTRAL_MODE        = NeutralMode.Brake;
  public final static NeutralMode       MOTOR_B_NEUTRAL_MODE        = NeutralMode.Brake;

  public final static FeedbackDevice    FEEDBACK_DEVICE             = FeedbackDevice.QuadEncoder;
  public final static boolean           FEEDBACK_DEVICE_INVERT      = false;

  public final static boolean           LIMIT_SOFT_ENABLED          = true;
  public final static int               LIMIT_SOFT_TIMEOUT          = 100; //in ms
  public final static int               LIMIT_SOFT_FORWARD          = 1000; //in encoder units
  public final static int               LIMIT_SOFT_REVERSE          = -100; //in encoder units

  public final static int               ENCODER_DUTY_CYCLE          = 4096; //Number of counts per rotation
  public final static int               ENCODER_POS_HORIZONTAL      = 0; //Encoder position when the arm is horizontal.
  public final static double            ENCODER_TICKS_PER_DEG       = ENCODER_DUTY_CYCLE / 360;
}
