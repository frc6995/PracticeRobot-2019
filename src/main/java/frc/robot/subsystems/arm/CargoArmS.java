package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;// armTalonB should be the talon without encoder

  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;

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



  public int countWithinSetPoint = 0;
  private int setPointRange = RobotMap.ARM_SHIP;


  @Override
  public void initDefaultCommand() {
  }

  public CargoArmS() {

    if(kF_nB > kF_B) {
      throw new IllegalArgumentException("kf_nB must be greater then kF_B");
    }

    /*Motor Initialization*/
    armTalonA = new WPI_TalonSRX(MOTOR_A_TALONID);
    armTalonB = new WPI_TalonSRX(MOTOR_B_TALONID);

    armTalonA.configFactoryDefault(FACTORY_DEFAULT_TIMEOUT);
    armTalonB.configFactoryDefault(FACTORY_DEFAULT_TIMEOUT);

    armTalonB.follow(armTalonA);

    armTalonA.setInverted(MOTOR_A_INVERT);
    armTalonB.setInverted(MOTOR_B_INVERT);

    armTalonA.setNeutralMode(MOTOR_A_NEUTRAL_MODE);
    armTalonB.setNeutralMode(MOTOR_B_NEUTRAL_MODE);

    armTalonA.enableCurrentLimit(CURRENT_LIMIT_ENABLED);
    armTalonA.configPeakCurrentLimit(CURRENT_PEAK_LIMIT_VALUE);
    armTalonA.configPeakCurrentDuration(CURRENT_PEAK_LIMIT_DURATION);
    armTalonA.configContinuousCurrentLimit(CURRENT_LIMIT_VALUE);

    armTalonA.configSelectedFeedbackSensor(FEEDBACK_DEVICE);
    armTalonA.setSensorPhase(FEEDBACK_DEVICE_INVERT);

    armTalonA.configForwardSoftLimitThreshold(LIMIT_SOFT_FORWARD, LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitThreshold(LIMIT_SOFT_REVERSE, LIMIT_SOFT_TIMEOUT);
    armTalonA.configForwardSoftLimitEnable(LIMIT_SOFT_ENABLED, LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitEnable(LIMIT_SOFT_ENABLED, LIMIT_SOFT_TIMEOUT);

    armTalonA.selectProfileSlot(LADDER_PID_SLOT, 0);
    armTalonA.configAllowableClosedloopError(LADDER_PID_SLOT, 0);

    armTalonA.config_kP(LADDER_PID_SLOT, kP);
    armTalonA.config_kI(LADDER_PID_SLOT, kI);
    armTalonA.config_kD(LADDER_PID_SLOT, kD);
    armTalonA.config_kF(LADDER_PID_SLOT, (kF * 1023) / ENCODER_DUTY_CYCLE);

    armTalonA.config_IntegralZone(LADDER_PID_SLOT, INTEGRAL_ZONE);

    armTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, PEAK_OUTPUT);
    armTalonA.configClosedloopRamp(RAMP_TIME);

    armTalonA.set(ControlMode.PercentOutput, 0);

    /*Limit switch configuration*/
    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
  }

  //changed from double to int, position is in integers only.
  public int getEncoderCount() {
    return armTalonA.getSelectedSensorPosition();
  }

  public int getError() {
    return armTalonA.getClosedLoopError();
  }

  public void runPID() {
    angleCos =  Math.cos(Math.toRadians(
                  ((getEncoderCount() - ENCODER_POS_HORIZONTAL) / ENCODER_TICKS_PER_DEG)
                ));

    kF_a = (kF_nB + (kF_B - kF_nB)) * angleCos;

    armTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount(), DemandType.ArbitraryFeedForward, kF_a);
  }

  public int getLadderSetPointEncoderCount() {
    return 0;
  }

  public int getArmSetPointEncoderCount() {

    if (getEncoderCount() == RobotMap.ARM_HOME) {
      return RobotMap.ARM_HOME;
//    } else if (){
//   I shall complete this, long if statement that tells pid when encoders are hit    
    } else {
      return 0;
    }
  }


  public boolean Home() {
    if(getEncoderCount() == RobotMap.ARM_HOME) {
      return true;
    } else {
      return false;
    }
  }

  public boolean Rocket() {
    if (getEncoderCount() == RobotMap.ARM_ROCKET) {
      return true;
    } else {
      return false;
    }
  }
  public boolean Ship() {
    if (getEncoderCount() == RobotMap.ARM_SHIP) {
      return true;
    } else {
      return false;
    }
  }

  public void up() {
    armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount());
    if (Math.abs(getError()) <= setPointRange) {
      countWithinSetPoint++;
    } else {
      limitSwitchPressed();
    }
  }

  public void down() {
    if (Math.abs(getError()) <= setPointRange) {
      countWithinSetPoint = countWithinSetPoint - 1;
    } else {
      limitSwitchPressed();
    }
  }

  public void limitSwitchPressed() {
    if (armLowerLimitSwitch.get() == true) {
      if (getEncoderCount() != RobotMap.ARM_HOME) {
        up();
      }
    }

    if (armUpperLimitSwitch.get() == true) {
      if (getEncoderCount() != RobotMap.ARM_SHIP) {
        down();
      }
    }
  }
}
