package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.pid.MoveHomeC;
import frc.robot.commands.arm.pid.SetHomeC;

public class CancelProtocolCG extends CommandGroup {
  public CancelProtocolCG() {
    addSequential(new SetHomeC());
    addSequential(new MoveHomeC());
  }
}
