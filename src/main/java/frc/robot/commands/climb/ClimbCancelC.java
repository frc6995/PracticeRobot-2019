package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Cancels Climb
 */
public class ClimbCancelC extends Command {
  public ClimbCancelC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts both front and rear wheels in tandem
  @Override
  protected void execute() {
    Robot.m_ClimbS.retract();
  }

  // This command will end when execute() is done
  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
