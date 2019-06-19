/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.arm;

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
    ARM_HOME, ARM_CUSHION, ARM_CARGO, ARM_ROCKET
  }
  
// armTalonA is left and has the encoder plugged into it, TalonB is right
  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;

  
  private DigitalInput armUpperLimitSwitch;

  private DigitalInput armLowerLimitSwitch;

  private double armUpKp = 0.65;
  private double armDownKp = 0.05;
  private double armKi = 0.0009;
  private double armKd = 0.0;

  @Override
  public void initDefaultCommand() {
  }
  public CargoArmS() {
    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
  }
}
