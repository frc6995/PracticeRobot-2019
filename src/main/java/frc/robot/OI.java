package frc.robot;

import frc.robot.controllermap.*;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;
import frc.robot.RobotMap;
import frc.robot.commands.arm.ArmCancelProtocolC;
import frc.robot.commands.arm.ArmLevelProtocolC;
import frc.robot.commands.arm.ArmJettisonProtocolC;
import frc.robot.commands.arm.ClawIntakeC;
import frc.robot.commands.climb.*;


public class OI {

  //input devices
  public final XBox xBox = new XBox(RobotMap.OI_XBOX);
  public final JStick jStick = new JStick(RobotMap.OI_JSTICK);

  public OI() {
      /**
       * buttons for joystick,
       * all buttons correspond to buttons mapped on image: 
       * https://samepage.io/app/#!/55f7d003b627e89b5d0b0c11258e2624567de58f/team-a4f52613587a4eddaeb9a554ab5ee00dac038e98/files/preview-796107915031679377
      */
      //arm buttons
      //all four levels:
      jStick.button_8_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_SHIP));
      jStick.button_11_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_ROCKET));
      jStick.button_9_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_SHIP));
      jStick.button_12_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_ROCKET));

      //trigger yeets
      jStick.button_5_runOnPress(new ArmJettisonProtocolC());

      //cancels level PID
      jStick.button_7_runOnPress(new ArmCancelProtocolC());

      //reload is currently button 6
      jStick.button_6_runOnPress(new ClawIntakeC());

      //Climb buttons
      //climb all-in-one
      jStick.button_3_runOnPress(new ClimbProtocolCG());
      //cancels climb, moves down to be redone
      jStick.button_10_runOnPress(new ClimbCancelProtocolCG());
  }
}
