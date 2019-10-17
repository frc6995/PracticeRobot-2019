package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climbcg.DeployC;
import frc.robot.commands.climbcg.MoveC;
import frc.robot.commands.climbcg.RetractRearC;

/**
 * Climb Protocol
 */
public class ClimbProtocolCG extends CommandGroup {

  public ClimbProtocolCG() {
    addSequential(new DeployC());       // Module 1: Deploys the Wheels
    addSequential(new MoveC(0.0, 0.0)); // Module 2: Moves forward until timed out, or interrupted
    addSequential(new RetractRearC());  // Module 3: When Front wheels are over podium, retract them
    addSequential(new MoveC(0.0, 0.0)); // Module 4: Moves forward until timed out, or interrupted
    addSequential(new RetractRearC());  // Module 5: When Center of Gravity is over podium, retract Rear
    addSequential(new MoveC(0.0, 0.0)); // Module 6: Move Forward onto podium
  }
}
