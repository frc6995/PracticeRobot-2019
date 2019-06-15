package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoJettisonC extends Command {
  private double intakeSpeed = 0.7; //positive values jettison?
  public CargoJettisonC() {
    requires(Robot.m_CargoIntakeS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_CargoIntakeS.cargoIntakeMotor.setSpeed(intakeSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_CargoIntakeS.getCargoLimit() || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_CargoIntakeS.cargoIntakeMotor.setSpeed(0.0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
