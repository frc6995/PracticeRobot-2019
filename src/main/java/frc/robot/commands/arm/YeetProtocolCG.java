package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.pid.HoldPidC;
import frc.robot.commands.arm.pid.MoveHomeC;
import frc.robot.commands.arm.pid.SetHomeC;
import frc.robot.commands.claw.ClawYeetC;

public class YeetProtocolCG extends CommandGroup {
  public YeetProtocolCG() {
    addParallel(new HoldPidC());
    addSequential(new ClawYeetC());
    addSequential(new SetHomeC());
    addSequential(new MoveHomeC());
  }
}