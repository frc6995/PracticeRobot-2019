package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;;

public class UltrasonicS extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private AnalogInput ai;// = new AnalogInput(RobotMap.ULTRASONIC_SENSOR);

  public UltrasonicS() {

    ai = new AnalogInput(RobotMap.ULTRASONIC_SENSOR);
  
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    getDistance();
  }

  public double getDistance(){
    double distance = ai.getValue() / 8;
    System.out.println(distance);
    return (distance);
  }
}