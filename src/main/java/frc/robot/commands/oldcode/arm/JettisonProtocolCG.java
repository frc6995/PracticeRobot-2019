package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.arm.pid.HoldPidC;
import frc.robot.commands.arm.pid.MoveHomeC;
import frc.robot.commands.arm.pid.MovePidC;
import frc.robot.commands.arm.pid.SetHomeC;
import frc.robot.commands.arm.pid.SetLevelC;
import frc.robot.commands.claw.ClawJettisonC;

public class JettisonProtocolCG extends CommandGroup {
  public JettisonProtocolCG(Level) {
    addParallel(new HoldPidC());
    addSequential(new ClawJettisonC());
    addSequential(new SetLevelC());
    addSequential(new MovePidC());
  }
}