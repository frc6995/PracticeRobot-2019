package frc.robot;

import frc.robot.buttons.Xbox;
import frc.robot.buttons.BBoard;
import frc.robot.commands.hand.CargoIntakeC;
import frc.robot.commands.hand.CargoJettisonC;
import frc.robot.RobotMap;

public class OI {

  //input devices
  public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

  public OI() {
    //Button Board Commands
    buttonBoard.right_index_toggleOnPress(new CargoIntakeC());
    buttonBoard.right_index_toggleOnPress(new CargoJettisonC());
  }
}
