package frc.robot.commands.hand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class CargoJettisonC extends Command {

  public CargoJettisonC() {
    requires(Robot.m_CargoHandS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(10); //whatever value
  }

  @Override
  protected void execute() {
    double speed = RobotPreferences.cargoShootSpeed.getValue();
    Robot.m_CargoHandS.cargoSpeed(speed);
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
