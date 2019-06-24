package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
  Levels for Arm(all measurements are from the floor up):
	Home
		Home Position 		          = 0' 0" Enc = (please convert to encoder counts)

	Cargo Ship
	
		Highest point on cargo ship = 4' 0" Enc = 
	
	Rocket ship:
		
		First port lowest point     = 1' 7.5" Enc =
 */
public class CargoArmS extends Subsystem {

  public enum ArmLevel{
    ARM_HOME, ARM_CUSHION, ARM_SHIP, ARM_ROCKET
  }
  
// armTalonA is left and has the encoder plugged into it, TalonB should be right
  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;

  
  private DigitalInput armUpperLimitSwitch;

  private DigitalInput armLowerLimitSwitch;

  private double armUpKp = 0.65;
  private double armDownKp = 0.05;
  private double armKi = 0.0009;
  private double armKd = 0.0;

  public static final int ARM_PID_SLOT = 0;

  @Override
  public void initDefaultCommand() {
  }
  
  public CargoArmS() {
    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
  }

  public boolean armHome() {
    if(getArmLevel == ArmLevel.ARM_HOME) {
      return true;
    }
  }

  public boolean armShip() {
    if(getArmLevel == ArmLevel.ARM_SHIP) {
      return true;
    }

  }

  public boolean armRocket() {
    if(getArmLevel == ArmLevel.ARM_ROCKET) {
      return true;
    }
  }

  
  public void runPID() {
    ladderPIDActive = true;
  
    public ArmLevel getNextArmLevel() {
      return nextArmLevel;
    }

    public int getArmSetPointEncoderCount(){

      if(getArmLevel() == ArmLevel.ARM_HOME) {
          return RobotMap.ARM_HOME;
      }

      else if (getArmLevel() == ArmLevel.ARM_SHIP) {
        return RobotMap.ARM_SHIP;
      }

      else if (getArmLevel() == ArmLevel.ARM_ROCKET) {
        return RobotMap.ARM_ROCKET;
      }
      
      else {
        return 0;
      }
    }
  }
}
