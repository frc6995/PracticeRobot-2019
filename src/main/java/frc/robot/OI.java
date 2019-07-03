package frc.robot;

import frc.robot.buttons.Xbox;
import frc.robot.buttons.BBoard;
import frc.robot.commands.arm.*;
import frc.robot.commands.hand.CargoIntakeC;
import frc.robot.commands.hand.CargoJettisonC;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class OI {

  //input devices
  public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

  public OI() {
    //Button Board Commands
    //hand
    buttonBoard.right_index_toggleOnPress(new CargoIntakeC());
    buttonBoard.right_index_toggleOnPress(new CargoJettisonC());


    /*
     encoder constants, for untested code
     Ship = 1000
     Rocket = 500
     Home = 0
    */
    //home button
    if (getEncoderCount <= 1000 && getEncoderCount >= 500){
      buttonBoard.left_ring_runOnPress(new ArmShipToHome());
    } else {
      buttonBoard.left_ring_runOnPress(new ArmRocketToHome());
    }

    //rocket button, if above Rocket level, run Down PID, if below, run Up PID
    if (getEncoderCount < 500) {
      buttonBoard.left_middle_runOnPress(new ArmHomeToRocket());
    } else {
      buttonBoard.left_middle_runOnPress(new ArmShipToRocket());
    }

    //ship level
    if (getEncoderCount >= 0 && getEncoderCount >= 500){
      buttonBoard.left_index_runOnPress(new ArmHomeToShip());
    } else {
      buttonBoard.left_index_runOnPress(new ArmRocketToShip());
    }
    

  }
}
