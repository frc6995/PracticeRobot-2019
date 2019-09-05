package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveC extends Command {
  private double speed;

  public MoveC(double speed) {
    requires(Robot.m_ClimbS);
    this.setInterruptible(true);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  // Moves forward
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(this.speed);
  }

  // Keeps going until interrupted
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
