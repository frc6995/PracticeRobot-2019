package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoRocketC extends Command {
  public CargoRocketC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_CargoArmS.getEncoderCount() >= Robot.m_CargoArmS.armHome && Robot.m_CargoArmS.getEncoderCount() <= Robot.m_CargoArmS.armRocket) {
      Robot.m_CargoArmS.up();
    } else {
      Robot.m_CargoArmS.down();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.Rocket();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
