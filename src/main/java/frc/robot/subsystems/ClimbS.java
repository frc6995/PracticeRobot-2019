package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Climb Subsystem
 */
public class ClimbS extends Subsystem {

  // Variables

  // Senders
  public static Spark mMotor;
  //TODO: make these single solenoids
  public static DoubleSolenoid dblSolenoidF;
  public static DoubleSolenoid dblSolenoidR;

  // Sensors
  public static DigitalInput limSolenoidPosF;
  public static DigitalInput limSolenoidPosB;
  public static Ultrasonic ultSensorR;
  public static Ultrasonic ultSensorL;
  public static Ultrasonic ultSensorF;
  public static Ultrasonic ultSensorB;

  // Returns true if solenoids are fully retracted
  public boolean SolenoidPosF = limSolenoidPosF.get();
  public boolean SolenoidPosB = limSolenoidPosB.get();

  // Returns true if Front Ultrasonic Sensor sees Podium
  //TODO: find out what to put in obj argument to say: "I see the podium"
  public boolean frontRetract = ultSensorF.equals(obj);

  // Returns true if Back Ultrasonic Sensor sees Podium
  //TODO: find out what to put in obj argument to say: "I see the podium"
  public boolean backRetract = ultSensorB.equals(obj);

  public ClimbS() {
    
    mMotor = new Spark(RobotMap.PWM_ID_SPARK_WHEELS);
    dblSolenoidF = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_FRONT_RETRACT);
    dblSolenoidR = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_DEPLOY, RobotMap.PCM_ID_DSOLENOID_CLIMB_REAR_RETRACT);
    limSolenoidPosF = new DigitalInput(RobotMap.DIO_CLIMB_FRONT_LIMIT);
    limSolenoidPosB = new DigitalInput(RobotMap.DIO_CLIMB_REAR_LIMIT);
    //TODO: find out what pingChannel and echo Channel are
    ultSensorR = new Ultrasonic(pingChannel, echoChannel);
    ultSensorL = new Ultrasonic(pingChannel, echoChannel);
    ultSensorF = new Ultrasonic(pingChannel, echoChannel);
    ultSensorB = new Ultrasonic(pingChannel, echoChannel);
    
  }
  
  @Override
  public void initDefaultCommand() {
  }

  // Deploys the wheels
  public void deploy() {
    dblSolenoidF.set(Value.kForward);
    dblSolenoidR.set(Value.kForward);
  }

  // Retracts the Wheels
  public void retractFront() {
    dblSolenoidF.set(Value.kReverse);
  }

  public void retractRear() {
    dblSolenoidR.set(Value.kReverse);
  }

  // Retracts both Front and Rear wheels
  public void retract() {
    dblSolenoidF.set(Value.kReverse);
    dblSolenoidR.set(Value.kReverse);
  }

  // Bumper sensor, if a sensor no longer sees podium, return true
  public boolean bumperSensor() {
    //TODO: find out what to put in obj argument to say: "I no longer see the podium"
    if (ultSensorL.equals(obj) == true || ultSensorR.equals(obj) == true) {
      return true;
    } else {
      return false;
    }
  }

  // leg wheel set desired speed
  public void legWheels(double speed) {
    mMotor.set(speed);
  }
}
