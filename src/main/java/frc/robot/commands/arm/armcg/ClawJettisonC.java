package frc.robot.commands.arm.armcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Jettisons cargo
 */
public class ClawJettisonC extends Command {

  private double jettisonSpeed = 0.7; //positive values jettison?

  public ClawJettisonC() {
    requires(Robot.m_ArmS);
  }

  @Override
  protected void initialize() {
  }

  //jettisons cargo
  @Override
  protected void execute() {
    Robot.m_ArmS.cargoSpeed(jettisonSpeed);
  }

  //when limit switch is released or timed out...
  @Override
  protected boolean isFinished() {
    return Robot.m_ArmS.getCargoLimit() == false || isTimedOut();
  }

  //stop motors
  @Override
  protected void end() {
    Robot.m_ArmS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
