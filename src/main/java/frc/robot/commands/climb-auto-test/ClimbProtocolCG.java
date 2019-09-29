package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.ClimbDeployC;
import frc.robot.commands.climb.climbcg.ClimbMoveForwardBC;
import frc.robot.commands.climb.climbcg.ClimbMoveForwardFC;

  /**
   * Climb Protocol
   * 
   * a. Deploys dblSolenoidF and dblSolenoidR
   * b. moves mMotor until ultSensorF returns true
   * c. retracts dblSolenoidF while mMotor is running and limSolenoidPosF returns true
   * d. proceeds to move mMotor until ultSensorR returns truee. stops mMotor
   * f. retracts dblSolenoidB
   */
public class ClimbProtocolCG extends CommandGroup {
  public ClimbProtocolCG() {
    addSequential(new ClimbDeployC());
    addSequential(new ClimbMoveForwardFC());
    addSequential(new ClimbMoveForwardBC());
  }
}
