package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Arm Subsystem
 */
public class ArmS extends Subsystem {

  // Variables
  public final static NeutralMode MOTOR_A_NEUTRAL_MODE = NeutralMode.Brake;
  public final static NeutralMode MOTOR_B_NEUTRAL_MODE = NeutralMode.Brake;
  public final static FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
  public WPI_TalonSRX armTalonA = null; 
  public WPI_TalonSRX armTalonB = null;
  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;
  public static Spark cargoIntakeMotor;
  private static DigitalInput cargoLimit;

  // Levels for the arm
  public enum ArmLevel {
    ARM_HOME, ARM_ROCKET, ARM_SHIP, ARM_REVERSE_ROCKET, ARM_REVERSE_SHIP
  }

  // + & - encoder counts away from setpoint
  private int setPointRange = 250;
  // Sets default set point
  private ArmLevel nextArmLevel = ArmLevel.ARM_HOME;
  // Counts how many loops we have been within the ladder set point
  private int countWithinSetPoint = 0;
  // Number of loops > setPointLoops
  private final int setPointLoops = 0;

  @Override
  public void initDefaultCommand() {
  }

  public ArmS() {

    /**
     * TODO: This method needs further documentation
     */
    // Instantiates Talons
    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    //Instantiates Sparks
    cargoIntakeMotor = new Spark(RobotMap.PWM_ID_SPARK_HAND);

    // Instantiates Limit Switches
    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
    cargoLimit = new DigitalInput(RobotMap.DIO_LIMIT_HAND);

    // Sets Talons to Factory Default
    armTalonA.configFactoryDefault(RobotMap.FACTORY_DEFAULT_TIMEOUT);
    armTalonB.configFactoryDefault(RobotMap.FACTORY_DEFAULT_TIMEOUT);

    // Tells armTalonB to mirror everything armTalon does
    armTalonB.follow(armTalonA);

    // TODO: Figure out if we need talons to be inverted
    // Inverts the talons
    armTalonA.setInverted(RobotMap.MOTOR_A_INVERT);
    armTalonB.setInverted(RobotMap.MOTOR_B_INVERT);

    // Sets Talons to TODO: Please explain this
    armTalonA.setNeutralMode(MOTOR_A_NEUTRAL_MODE);
    armTalonB.setNeutralMode(MOTOR_B_NEUTRAL_MODE);

    armTalonA.enableCurrentLimit(RobotMap.CURRENT_LIMIT_ENABLED);
    armTalonA.configPeakCurrentLimit(RobotMap.CURRENT_PEAK_LIMIT_VALUE);
    armTalonA.configPeakCurrentDuration(RobotMap.CURRENT_PEAK_LIMIT_DURATION);
    armTalonA.configContinuousCurrentLimit(RobotMap.CURRENT_LIMIT_VALUE);

    // We are using a Quad-encoder
    armTalonA.configSelectedFeedbackSensor(FEEDBACK_DEVICE);
    // TODO: Inverted?
    armTalonA.setSensorPhase(RobotMap.FEEDBACK_DEVICE_INVERT);

    armTalonA.configForwardSoftLimitThreshold(RobotMap.LIMIT_SOFT_FORWARD, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitThreshold(RobotMap.LIMIT_SOFT_REVERSE, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configForwardSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);

    armTalonA.selectProfileSlot(RobotMap.ARM_PID_SLOT, 0);
    armTalonA.configAllowableClosedloopError(RobotMap.ARM_PID_SLOT, 0);

    // TODO: Calculate PID
    // Calculates the P.I.D variables
    armTalonA.config_kP(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kP);
    armTalonA.config_kI(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kI);
    armTalonA.config_kD(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kD);
    armTalonA.config_kF(RobotMap.ARM_PID_SLOT, (RobotMap.ARM_kF * 1023) / RobotMap.ENCODER_DUTY_CYCLE);

    armTalonA.config_IntegralZone(RobotMap.ARM_PID_SLOT, RobotMap.ARM_INTEGRAL_ZONE);

    armTalonA.configClosedLoopPeakOutput(RobotMap.ARM_PID_SLOT, RobotMap.ARM_PEAK_OUTPUT);
    armTalonA.configClosedloopRamp(RobotMap.ARM_RAMP_TIME);

    armTalonA.set(ControlMode.PercentOutput, 0); 
  }

  // Tells the PID what encoder position in relation to the set point we want to go to
  public int getArmSetPointEncoderCount() {
    switch (nextArmLevel) {
      case ARM_HOME :
        return RobotMap.ARM_HOME;
      case ARM_ROCKET :
        return RobotMap.ARM_ROCKET;
      case ARM_SHIP :
        return RobotMap.ARM_SHIP;
      case ARM_REVERSE_SHIP :
        return RobotMap.ARM_REVERSE_SHIP;
      case ARM_REVERSE_ROCKET :
        return RobotMap.ARM_REVERSE_ROCKET;
      default :
        return 0;
    }
  }

  // Sets arm level to desired Set point
  public void setNextArmLevel(ArmLevel nextLevel) {
    nextArmLevel = nextLevel;
  }

  // Gets encoder current position
  public double getCurrentEncoderCount() {
    return (armTalonA.get());
  }

  // Gets how far away we are from desired set point
  public int getError() {
    return armTalonA.getClosedLoopError();
  }

  /**
   * runPID method
   * 1. Calculates Arbitrary Feed Forward (force to overcome gravity)
   * 2. Moves motors to desired set point
   * 3. Counts the amount of loops when within desired set point
   */
  public void runPid() {
    double  f_a = 0.0;
    if(getCargoLimit() == true){
      f_a = RobotMap.ARM_kF_B * Math.cos(Math.toRadians((getCurrentEncoderCount() - RobotMap.ENCODER_POS_HORIZONTAL) / RobotMap.ENCODER_TICKS_PER_DEG));
    }else if(getCargoLimit() == false){
      f_a = RobotMap.ARM_kF_nB * Math.cos(Math.toRadians((getCurrentEncoderCount() - RobotMap.ENCODER_POS_HORIZONTAL) / RobotMap.ENCODER_TICKS_PER_DEG));
    }

    armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount(), DemandType.ArbitraryFeedForward, f_a);

    if (Math.abs(getError()) < setPointRange) {
      countWithinSetPoint++;
    } else {
      countWithinSetPoint = 0;
    }
  }
  
  // If we have been within our set point range for the right amount of loops, return true
  public boolean isAtSetPoint() {
    if (countWithinSetPoint > setPointLoops ) {
      countWithinSetPoint = 0;
      return true;
    } else {
      return false;
    }
  }

  // Returns true if the limit switch on either side is pressed
  public boolean lowerlimitSwitchPressed() {
    return armLowerLimitSwitch.get();
  }
  
  public boolean armUpperLimitSwitch() {
    return armUpperLimitSwitch.get();
  }

  // Sets the speed of claw wheels
  public void cargoSpeed(double speed) {
    cargoIntakeMotor.set(speed);
  }

  // Limit switch returns true if claw is loaded with cargo
  public boolean getCargoLimit() {
    return cargoLimit.get();
  }
}
