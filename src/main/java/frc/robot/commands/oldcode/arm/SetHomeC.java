package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * sets the PID to HOME Position, does not move the PID
 */
public class SetHomeC extends Command {

  public SetHomeC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.setNextArmLevel(ArmLevel.ARM_HOME);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.m_CargoArmS.setNextArmLevel(ArmLevel.ARM_HOME);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
