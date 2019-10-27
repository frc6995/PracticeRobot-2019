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
  public final static NeutralMode MOTOR_NEUTRAL_MODE = NeutralMode.Brake;
  public final static FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
  public WPI_TalonSRX armTalon = null;
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
    armTalon = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);

    //Instantiates Sparks
    cargoIntakeMotor = new Spark(RobotMap.PWM_ID_SPARK_HAND);

    // Instantiates Limit Switches
    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
    cargoLimit = new DigitalInput(RobotMap.DIO_LIMIT_HAND);

    // Sets Talons to Factory Default
    armTalon.configFactoryDefault(RobotMap.FACTORY_DEFAULT_TIMEOUT);

    // TODO: Figure out if we need talons to be inverted
    // Inverts the talons
    armTalon.setInverted(RobotMap.MOTOR_A_INVERT);

    // Sets Talons to TODO: Please explain this
    armTalon.setNeutralMode(MOTOR_NEUTRAL_MODE);

    armTalon.enableCurrentLimit(RobotMap.CURRENT_LIMIT_ENABLED);
    armTalon.configPeakCurrentLimit(RobotMap.CURRENT_PEAK_LIMIT_VALUE);
    armTalon.configPeakCurrentDuration(RobotMap.CURRENT_PEAK_LIMIT_DURATION);
    armTalon.configContinuousCurrentLimit(RobotMap.CURRENT_LIMIT_VALUE);

    // We are using a Quad-encoder
    armTalon.configSelectedFeedbackSensor(FEEDBACK_DEVICE);
    // TODO: Inverted?
    armTalon.setSensorPhase(RobotMap.FEEDBACK_DEVICE_INVERT);

    armTalon.configForwardSoftLimitThreshold(RobotMap.LIMIT_SOFT_FORWARD, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalon.configReverseSoftLimitThreshold(RobotMap.LIMIT_SOFT_REVERSE, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalon.configForwardSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalon.configReverseSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);

    armTalon.selectProfileSlot(RobotMap.ARM_PID_SLOT, 0);
    armTalon.configAllowableClosedloopError(RobotMap.ARM_PID_SLOT, 0);

    // TODO: Calculate PID
    // Calculates the P.I.D variables
    armTalon.config_kP(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kP);
    armTalon.config_kI(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kI);
    armTalon.config_kD(RobotMap.ARM_PID_SLOT, RobotMap.ARM_kD);
    armTalon.config_kF(RobotMap.ARM_PID_SLOT, (RobotMap.ARM_kF * 1023) / RobotMap.ENCODER_DUTY_CYCLE);

    armTalon.config_IntegralZone(RobotMap.ARM_PID_SLOT, RobotMap.ARM_INTEGRAL_ZONE);

    armTalon.configClosedLoopPeakOutput(RobotMap.ARM_PID_SLOT, RobotMap.ARM_PEAK_OUTPUT);
    armTalon.configClosedloopRamp(RobotMap.ARM_RAMP_TIME);

    armTalon.set(ControlMode.PercentOutput, 0); 
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
    return (armTalon.get());
  }

  // Gets how far away we are from desired set point
  public int getError() {
    return armTalon.getClosedLoopError();
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

    armTalon.set(ControlMode.Position, getArmSetPointEncoderCount(), DemandType.ArbitraryFeedForward, f_a);

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
