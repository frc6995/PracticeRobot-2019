package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climb Mechanism, Autonomous
 */
public class ClimbS extends Subsystem {

  // Hardware Objects
  public static Spark mMotor;
  public static DoubleSolenoid dblSolenoidFront;
  public static DoubleSolenoid dblSolenoidRear;
  public static AnalogInput ultSensorFront;
  public static AnalogInput ultSensorMiddle;
  
  // Distance Above Podium
  public static Double Distance = 0.0;
  
  public ClimbS() {
    mMotor = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
    dblSolenoidFront = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    dblSolenoidRear = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    ultSensorFront = new AnalogInput(RobotMap.ULTRASONIC_SENSOR_FRONT);
    ultSensorMiddle = new AnalogInput(RobotMap.ULTRASONIC_SENSOR_MIDDLE);
  }

  @Override
  public void initDefaultCommand() {
  }

  // leg wheel set desired speed
  public void legWheels(double speed) {
    mMotor.set(speed);
  }

  // Deploys Wheels
  public void deploy() {
    dblSolenoidFront.set(DoubleSolenoid.Value.kForward);
    dblSolenoidRear.set(DoubleSolenoid.Value.kForward);
  }

  // Retracts Front Wheels
  public void retractFront() {
    dblSolenoidFront.set(DoubleSolenoid.Value.kReverse);
  }

  // Retracts Rear Wheels
  public void retractRear() {
    dblSolenoidRear.set(DoubleSolenoid.Value.kReverse);
  }

  // Retracts Wheels
  public void retract() {
    dblSolenoidFront.set(DoubleSolenoid.Value.kReverse);
    dblSolenoidRear.set(DoubleSolenoid.Value.kReverse);
  }

  // Gets how far above the front Wheels are
  public double getDistanceFront() {
    double distance = ultSensorFront.getValue() / 8;
    return(distance);
  }

  // Gets how far above the Center of Gravity is
  public double getDistanceMiddle() {
    double distance = ultSensorMiddle.getValue() / 8;
    return(distance);
  }

  // Returns True if the Front Wheels are over the podium
  public boolean frontIsOver(){
    if (getDistanceFront() <= Distance) {
      return true;
    } else {
      return false;
    }
  }

  // Returns True if the Center of Gravity is over the podium
  public boolean centerIsOver(){
    if (getDistanceMiddle() <= Distance) {
      return true;
    } else {
      return false;
    }
  }
}
