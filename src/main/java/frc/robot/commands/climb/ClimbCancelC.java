package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Cancels climb if front pistons are not retracted
 */
public class ClimbCancelC extends Command {
  
  public ClimbCancelC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts all Pistons
  @Override
  protected void execute() {
    if (!Robot.m_ClimbS.frontPistons()){
      Robot.m_ClimbS.retract();
    } else {
      end();
    }  
  }

  // Returns true when pistons are fully retracted
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.frontPistons() && Robot.m_ClimbS.rearPistons();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
