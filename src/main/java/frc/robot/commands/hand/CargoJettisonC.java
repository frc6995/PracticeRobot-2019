package frc.robot.commands.hand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoJettisonC extends Command {

  private double jettisonSpeed = 0.7; //positive values jettison?

  public CargoJettisonC() {

    requires(Robot.m_CargoHandS);

  }

  @Override
  protected void initialize() {
    
    this.setTimeout(10); //whatever value

  }

  @Override
  protected void execute() {

    Robot.m_CargoHandS.cargoSpeed(jettisonSpeed);

  }

  @Override
  protected boolean isFinished() {

    return Robot.m_CargoHandS.getCargoLimit() || isTimedOut();

  }

  @Override
  protected void end() {

    Robot.m_CargoHandS.cargoSpeed(0.0);

  }

  @Override
  protected void interrupted() {

    end();

  }
  
}
