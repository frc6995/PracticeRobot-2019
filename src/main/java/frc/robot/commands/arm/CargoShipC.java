package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoShipC extends Command {
  public CargoShipC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.m_CargoArmS.up();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.isShip();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
