package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climb Subsystem
 */
public class ClimbS extends Subsystem {

  // Senders
  public static Spark mMotor;
  public static DoubleSolenoid climbFront;
  public static DoubleSolenoid climbRear;

  public ClimbS() {
    mMotor = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
    climbFront = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    climbRear = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    
  }
  
  @Override
  public void initDefaultCommand() {
  }

  // Deploys the wheels
  public void deploy() {
    climbFront.set(Value.kForward);
    climbRear.set(Value.kForward);
  }

  // Retracts the wheels
  public void retractFront() {
    climbFront.set(Value.kReverse);
  }

  public void retractRear() {
    climbRear.set(Value.kReverse);
  }

  // Retracts both Front and Rear wheels
  public void retract() {
    climbRear.set(Value.kReverse);
    climbFront.set(Value.kReverse);
  }

  // leg wheel set desired speed
  public void legWheels(double speed) {
    mMotor.set(speed);
  }
}
