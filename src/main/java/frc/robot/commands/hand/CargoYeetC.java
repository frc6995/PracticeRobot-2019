package frc.robot.commands.hand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoYeetC extends Command {

  private double yeetSpeed = 0.7; //positive values jettison?

  public CargoYeetC() {
    requires(Robot.m_CargoClawS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(10); //whatever value
  }

  @Override
  protected void execute() {
    Robot.m_CargoClawS.cargoSpeed(yeetSpeed);
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
