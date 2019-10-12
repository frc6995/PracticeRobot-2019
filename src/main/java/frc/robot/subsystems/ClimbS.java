package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climb Subsystem
 */
public class ClimbS extends Subsystem {

  // Senders
  public static Spark mMotor;
  public static Solenoid climbFrontRight;
  public static Solenoid climbFrontLeft;
  public static Solenoid climbRearRight;
  public static Solenoid climbRearLeft;
  private AnalogInput ai;// = new AnalogInput(RobotMap.ULTRASONIC_SENSOR);

  public ClimbS() {
    mMotor = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
    climbFrontRight = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    climbFrontLeft = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    climbRearRight = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    climbRearLeft = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    ai = new AnalogInput(RobotMap.ULTRASONIC_SENSOR);
  }
  
  @Override
  public void initDefaultCommand() {
    getDistance();
  }

  // Deploys the wheels
  public void deploy() {
    climbFrontRight.set(true);
    climbFrontLeft.set(true);
    climbRearRight.set(true);
    climbRearLeft.set(true);
  }

  // Retracts the wheels
  public void retractFront() {
    climbFrontLeft.set(false);
    climbFrontRight.set(false);
  }

  public void retractRear() {
    climbRearLeft.set(false);
    climbRearRight.set(false);
  }

  // Retracts both Front and Rear wheels
  public void retract() {
    climbRearLeft.set(false);
    climbRearRight.set(false);
    climbFrontLeft.set(false);
    climbFrontRight.set(false);
  }

  // leg wheel set desired speed
  public void legWheels(double speed) {
    mMotor.set(speed);
  }

  // Returns bumper is over the podium
  public boolean bumperIsOver() {
    return ultSensorFront;
  }

  // Returns if Center of Gravity is over the podium
  public boolean cGIsOver() {
    return ultSensorMiddle;
  }

  // Limit front wheelS position
  public boolean solenoidPosF() {
    return limSolenoidPosF;
  }

  public boolean solenoidPosR() {
    return limSolenoidPosR;
  }

  // Gets how far we are away from the podium
  public double getDistance(){
    double distance = ai.getValue() / 8;
    System.out.println(distance);
    return (distance);
  }
}
