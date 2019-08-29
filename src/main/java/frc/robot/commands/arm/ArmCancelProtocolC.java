package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmS.ArmLevel;

/**
 * Cancel Protocol
 */
public class ArmCancelProtocolC extends Command {
  public ArmCancelProtocolC() {
    requires(Robot.m_ArmS);
  }
  
  @Override
  protected void initialize() {
  }

  // Sets home position, then moves home
  @Override
  protected void execute() {
    Robot.m_ArmS.setNextArmLevel(ArmLevel.ARM_HOME);
    Robot.m_ArmS.runPid();
  }

  // When at home, end command
  @Override
  protected boolean isFinished() {
    return Robot.m_ArmS.isAtSetPoint();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
