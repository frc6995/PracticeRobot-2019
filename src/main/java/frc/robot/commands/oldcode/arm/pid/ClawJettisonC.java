package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Jettisons cargo
 */
public class ClawJettisonC extends Command {

  private double jettisonSpeed = 0.7; //positive values jettison?

  public ClawJettisonC() {
    requires(Robot.m_CargoClawS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(10); //whatever value
  }

  @Override
  protected void execute() {
    Robot.m_CargoClawS.cargoSpeed(jettisonSpeed);
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoClawS.getCargoLimit() == true || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_CargoClawS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
