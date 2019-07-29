package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.pid.HoldPidC;
import frc.robot.commands.arm.pid.MoveHomeC;
import frc.robot.commands.arm.pid.SetHomeC;
import frc.robot.commands.arm.pid.YeetC;

public class YeetProtocolCG extends CommandGroup {
  public YeetProtocolCG() {
    addParallel(new HoldPidC());
    addSequential(new YeetC());
    addSequential(new SetHomeC());
    addSequential(new MoveHomeC());
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
