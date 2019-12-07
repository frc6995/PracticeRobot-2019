package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

/**
 * General Purpose, Move the Wheels For Desired Speed 
 */
public class MoveC extends Command {
  private double Multiplier;

  public MoveC(int multiplier) {
    requires(Robot.m_ClimbS);
    this.setInterruptible(true);
    this.Multiplier = multiplier;
  }

  @Override
  protected void initialize() {
  }

  // Moves desired speed
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(RobotPreferences.wheelSpeed.getValue()*this.Multiplier);
  }

  // Keeps going until interrupted
  @Override
  protected boolean isFinished() {
    return true;
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
