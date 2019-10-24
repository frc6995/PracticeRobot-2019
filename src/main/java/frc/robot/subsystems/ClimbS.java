package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climb Mechanism, Autonomous
 */
public class ClimbS extends Subsystem {

  // Hardware objects
  public static Spark mMotor;
  public static DoubleSolenoid dblSolenoidFrontLeft;
  public static DoubleSolenoid dblSolenoidFrontRight;
  public static DoubleSolenoid dblSolenoidRearLeft;
  public static DoubleSolenoid dblSolenoidRearRight;
  public static AnalogInput ultSensorFront;
  public static AnalogInput ultSensorMiddle;
  public static DigitalInput limPosFront;
  public static DigitalInput limPosRear;
  
  
  public ClimbS() {
    mMotor = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
    dblSolenoidFrontLeft = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_LEFT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_LEFT_RETRACT);
    dblSolenoidFrontRight = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RIGHT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RIGHT_RETRACT);
    dblSolenoidRearLeft = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_LEFT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_LEFT_RETRACT);
    dblSolenoidRearRight = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RIGHT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RIGHT_RETRACT);
    ultSensorFront = new AnalogInput(RobotMap.ULTRASONIC_SENSOR_FRONT);
    ultSensorMiddle = new AnalogInput(RobotMap.ULTRASONIC_SENSOR_MIDDLE);
    limPosFront = new DigitalInput(RobotMap.DIO_CLIMB_FRONT_LIMIT);
    limPosRear = new DigitalInput(RobotMap.DIO_CLIMB_REAR_LIMIT);
  }

  @Override
  public void initDefaultCommand() {
  }

  /**
   * mMotor Regulator
   * @param speed desired speed
   * @param timeout desired timeout
   */
  public void legWheels(double speed, double timeout) {
    mMotor.set(speed);
    mMotor.setExpiration(timeout);
  }

  // Deploys pistons
  public void deploy() {
    dblSolenoidFrontLeft.set(DoubleSolenoid.Value.kForward);
    dblSolenoidFrontRight.set(DoubleSolenoid.Value.kForward);
    dblSolenoidRearLeft.set(DoubleSolenoid.Value.kForward);
    dblSolenoidRearRight.set(DoubleSolenoid.Value.kForward);
  }

  // Retracts front pistons
  public void retractFront() {
    dblSolenoidFrontLeft.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidFrontRight.set(DoubleSolenoid.Value.kReverse);
  }

  // Retracts rear pistons
  public void retractRear() {
    dblSolenoidRearLeft.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidRearRight.set(DoubleSolenoid.Value.kReverse);
  }

  // Retracts all pistons
  public void retract() {
    dblSolenoidFrontLeft.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidFrontRight.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidRearLeft.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidRearRight.set(DoubleSolenoid.Value.kReverse);
  }

  // Gets the distance of the Front ultrasonic
  public double getDistanceFront() {
    double distance = ultSensorFront.getValue() / 8;
    return(distance);
  }

  // Gets the distance of the middle ultrasonic
  public double getDistanceMiddle() {
    double distance = ultSensorMiddle.getValue() / 8;
    return(distance);
  }

  // Returns true if the front wheels are over the podium
  public boolean frontIsOver(double podium) {
    if (getDistanceFront() <= podium) {
      return true;
    } else {
      return false;
    }
  }

  // Returns true if the center of gravity is over the podium
  public boolean centerIsOver(double podium) {
    if (getDistanceMiddle() <= podium) {
      return true;
    } else {
      return false;
    }
  }

  // Returns true when pistons reach desired height
  public boolean height(double altitude) {
    if (getDistanceMiddle() <= altitude) {
      return true;
    } else {
      return false;
    }
  }

  // Returns true when front pistons are fully retracted
  public boolean frontPistons() {
    return limPosFront.get();
  }

  // Returns true when rear pistons are fully retracted
  public boolean rearPistons() {
    return limPosRear.get();
  }
}
