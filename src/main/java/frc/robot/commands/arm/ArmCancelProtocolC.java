package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * Cancel protocol
 * 
 * 1. sets home position
 * 2. moves home
 */
public class ArmCancelProtocolC extends Command {
  public ArmCancelProtocolC() {
    requires(Robot.m_CargoArmS);
  }
  
  @Override
  protected void initialize() {
  }

  //moves home
  @Override
  protected void execute() {
    Robot.m_CargoArmS.setNextArmLevel(ArmLevel.ARM_HOME);
    Robot.m_CargoArmS.runPid();
  }

  //when at home, return true and end
  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.isAtSetPoint();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
