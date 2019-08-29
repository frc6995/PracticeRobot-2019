package frc.robot.commands.arm.armcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Jettisons Cargo
 */
public class ClawJettisonC extends Command {

  // Rotates wheels to shoot out
  private double jettisonSpeed = 0.7;

  public ClawJettisonC() {
    requires(Robot.m_ArmS);
  }

  @Override
  protected void initialize() {
  }

  // Jettisons cargo
  @Override
  protected void execute() {
    Robot.m_ArmS.cargoSpeed(jettisonSpeed);
  }

  // Stop when claw jettisons or is timed out
  @Override
  protected boolean isFinished() {
    return Robot.m_ArmS.getCargoLimit() == false || isTimedOut();
  }

  // Stops motors
  @Override
  protected void end() {
    Robot.m_ArmS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
