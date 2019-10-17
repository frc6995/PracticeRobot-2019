package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Wheels
 */
public class ClimbCancelC extends Command {
  public ClimbCancelC() {
    requires(Robot.m_ClimbS);
  }

  // Maybe Have a check Paramater, something that tells robot that 
  // front wheels are on the podium, and will not try to cancel.
  @Override
  protected void initialize() {
  }

  // Retracts Wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retract();
  }

  // When done, return true
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
