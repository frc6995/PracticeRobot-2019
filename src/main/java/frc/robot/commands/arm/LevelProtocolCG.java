package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.pid.HoldPidC;
import frc.robot.commands.arm.pid.MovePidC;
import frc.robot.commands.arm.pid.SetLevelC;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

public class LevelProtocolCG extends CommandGroup {
  public LevelProtocolCG(ArmLevel level) {
    addSequential(new SetLevelC());
    addSequential(new MovePidC());
    addParallel(new HoldPidC());
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
