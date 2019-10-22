package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.DeployC;
import frc.robot.commands.climb.climbcg.MoveC;
import frc.robot.commands.climb.climbcg.RetractFrontC;
import frc.robot.commands.climb.climbcg.RetractRearC;

/**
 * Climb Protocol
 */
public class ClimbProtocolCG extends CommandGroup {

  public ClimbProtocolCG() {
    addSequential(new DeployC(0.0));     // Module 1: Deploys the Wheels until reaching desired height
    addSequential(new MoveC(0.0, 0.0));  // Module 2: Moves forward until timed out, or interrupted
    addParallel(new RetractFrontC(0.0)); // Module 3: When Front wheels are over podium, retract them
    addSequential(new MoveC(0.0, 0.0));  // Module 4: Moves forward until timed out, or interrupted
    addParallel(new RetractRearC(0.0));  // Module 5: When Center of Gravity is over podium, retract Rear
    addSequential(new MoveC(0.0, 0.0));  // Module 6: Move Forward onto podium
  }
}
