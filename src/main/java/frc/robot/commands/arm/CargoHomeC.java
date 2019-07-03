package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoHomeC extends Command {
  public CargoHomeC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_CargoArmS.getEncoderCount() >= Robot.m_CargoArmS.armHome && Robot.m_CargoArmS.getEncoderCount() <= Robot.m_CargoArmS.armShip) {
      Robot.m_CargoArmS.down();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.Home();
  }

  @Override
  protected void end() {
    end();
  }

  @Override
  protected void interrupted() {
  }
}
