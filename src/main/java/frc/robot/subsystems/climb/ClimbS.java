package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climbing Mechanism
 */
public class ClimbS extends Subsystem {

  //if we are using single solenoid use these
  //public static Solenoid climbFront;
  //public static Solenoid climbRear;

  //if we are using double solenoids, use these
  public static DoubleSolenoid climbFront;
  public static DoubleSolenoid climbRear;

  public static Spark legWheels;
  public static DigitalInput limitFront;
  public static DigitalInput limitRear;

  public ClimbS() {
    
    //climbFront = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    //climbRear = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);

    climbFront = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    climbRear = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);

    limitFront = new DigitalInput(RobotMap.DIO_CLIMB_FRONT_LIMIT);
    limitRear = new DigitalInput(RobotMap.DIO_CLIMB_REAR_LIMIT);
    legWheels = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
  }
  
  @Override
  public void initDefaultCommand() {
  }

  //deploys the wheels
  public void deploy() {
    //climbFront.set(true);
    //climbRear.set(true);
    climbFront.set(Value.kForward);
    climbFront.set(Value.kForward);
  }

  //retracts the Wheels
  public void retractFront() {
    //climbFront.set(false);
    climbFront.set(Value.kReverse);
  }

  public void retractRear() {
    //climbRear.set(false);
    climbRear.set(Value.kReverse);
  }

  //limit switch boolean values that return true if hit
  public boolean frontLimit() {
    return limitFront.get();
  }

  public boolean rearLimit() {
    return limitRear.get();
  }
  public void legWheels(double speed) {
    legWheels.set(speed);
  }
  //MoveForwardC hits a limit switch
  public boolean limitSwitchPressed() {
    if (frontLimit() == true || rearLimit() == true) {
      return true;
    } else {
      return false;
    }
  }
}
