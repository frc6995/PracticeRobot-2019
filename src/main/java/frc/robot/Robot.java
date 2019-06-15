package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.cargo.*;
import frc.robot.subsystems.arm.*;

public class Robot extends TimedRobot {
  
  //Subsystems
  public static CargoIntakeS m_CargoIntakeS = new CargoIntakeS();
  //Commands
  public static CargoIntakeC m_CargoIntakeC;
  public static CargoJettisonC m_CargoJettisonC;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {

    //Subsystems
    m_CargoIntakeS = new CargoIntakeS();
    //Commands
    m_CargoIntakeC = new CargoIntakeC();
    m_CargoJettisonC = new CargoJettisonC();

  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {

      m_autonomousCommand.start();

    }

  }

  @Override
  public void autonomousPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {

    if (m_autonomousCommand != null) {

      m_autonomousCommand.cancel();

    }

  }

  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {

  }

}
