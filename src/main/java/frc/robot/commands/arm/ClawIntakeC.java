package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Cargo Intake
 */
public class ClawIntakeC extends Command {

  // Rotates wheels to intake
  private double intakeSpeed = -0.7;

  public ClawIntakeC() {
    requires(Robot.m_ArmS);
  }

  // Set timout in case we don't get cargo
  @Override
  protected void initialize() {
    this.setTimeout(10);
  }

  // Intakes cargo
  @Override
  protected void execute() {
    Robot.m_ArmS.cargoSpeed(intakeSpeed);
  }

  //if cargo gets intaken or timed out, end command
  @Override
  protected boolean isFinished() {
    return Robot.m_ArmS.getCargoLimit()  == true || isTimedOut();
  }

  // Stop motor
  @Override
  protected void end() {
    Robot.m_ArmS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
