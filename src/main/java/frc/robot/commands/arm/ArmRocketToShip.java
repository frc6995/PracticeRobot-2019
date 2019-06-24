package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmRocketToShip extends Command {
  public ArmRocketToShip() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.runPID();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.armShip();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
