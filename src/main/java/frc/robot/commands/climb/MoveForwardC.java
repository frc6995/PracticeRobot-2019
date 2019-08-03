package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * practically autonomous, moves forward until a limit switch gets pressed, then stops
 */
public class MoveForwardC extends Command {
  public MoveForwardC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(0.7);//fine tune this value
  }

  //determines of a limit switch has been pressed and if true, stop moving forward
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.limitSwitchPressed();
  }

  @Override
  protected void end() {
    Robot.m_ClimbS.legWheels(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
