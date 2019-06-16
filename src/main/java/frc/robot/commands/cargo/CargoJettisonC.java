package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoJettisonC extends Command {

  private double jettisonSpeed = 0.7; //positive values jettison?

  public CargoJettisonC() {

    requires(Robot.m_CargoIntakeS);

  }

  @Override
  protected void initialize() {
    
    this.setTimeout(10); //whatever value

  }

  @Override
  protected void execute() {

    Robot.m_CargoIntakeS.cargoIntakeMotor.setSpeed(jettisonSpeed);

  }

  @Override
  protected boolean isFinished() {

    return Robot.m_CargoIntakeS.getCargoLimit() || isTimedOut();

  }

  @Override
  protected void end() {

    Robot.m_CargoIntakeS.cargoIntakeMotor.setSpeed(0.0);

  }

  @Override
  protected void interrupted() {

    end();

  }
  
}