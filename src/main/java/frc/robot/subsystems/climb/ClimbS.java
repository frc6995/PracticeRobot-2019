package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climbing Mechanism
 */
public class ClimbS extends Subsystem {

  //if we are using single solenoid use these
  //public static Solenoid climbFrontRight;
  //public static Solenoid climbFrontLeft;
  //public static Solenoid climbRearRight;
  //public static Solenoid climbRearLeft;
  
  //if we are using double solenoids, use these
  public static Value in = Value.kForward;
  public static Value out = Value.kReverse;
  public static DoubleSolenoid climbFront;
  public static DoubleSolenoid climbRear;

  public static Spark legWheels;
  public static DigitalInput limitFront;
  public static DigitalInput limitRear;

  public ClimbS() {
    
    //climbFrontRight = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    //climbFrontLeft = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    //climbRearRight = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    //climbRearLeft = new Solenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);

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
    //climbFrontRight.set(true);
    //climbFrontLeft.set(true);
    //climbRearRight.set(true);
    //climbRearLeft.set(true);
    climbFront.set(out);
    climbFront.set(out);
  }

  //retracts the Wheels
  public void retractFront() {
    //climbFrontRight.set(false);
    //climbFrontLeft.set(false);
    climbFront.set(in);
  }

  public void retractRear() {
    //climbRearRight.set(false);
    //ClimbRearLeft.set(false);
    climbRear.set(in);
  }

  //retracts both Front and Rear wheels
  public void retract() {
    //climbFrontRight.set(false);
    //climbFrontLeft.set(false);
    //climbRearRight.set(false);
    //ClimbRearLeft.set(false);
    climbFront.set(in);
    climbRear.set(in);
  }

  //limit switch boolean values that return true if hit
  public boolean frontLimit() {
    return limitFront.get();
  }

  public boolean rearLimit() {
    return limitRear.get();
  }

  //leg wheel set speed
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
