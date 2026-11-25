// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Drivetrain;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  private final Drivetrain m_robotDrive = new Drivetrain();
  private final CommandXboxController m_controller = new CommandXboxController(0);
  //private final DifferentialDrive m_robotDrive;
  //private final Joystick m_leftStick;
  //private final Joystick m_rightStick;

  private final TalonFX leftLeader = new TalonFX(0);
  private final TalonFX rightLeader = new TalonFX(1);
  private final TalonFX leftFollower = new TalonFX(2);
  private final TalonFX rightFollower = new TalonFX(3);

  /** Called once at the beginning of the robot program. */
  public Robot() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    //m_robotDrive = new DifferentialDrive(leftLeader::set, rightLeader::set);
    //m_leftStick = new Joystick(0);
    //m_rightStick = new Joystick(1);
    m_robotDrive.setDefaultCommand(
      m_robotDrive.tankDrive(
        () -> m_controller.getLeftY(),
        () -> m_controller.getRightY()
      )
    );

    SendableRegistry.addChild(m_robotDrive, leftLeader);
    SendableRegistry.addChild(m_robotDrive, rightLeader);
  }

  @Override

  public void robotInit() {

  }
  public void teleopPeriodic() {
    //m_robotDrive.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
  }

  public void configurebindings() {
    m_controller.axisLessThan(1, -0.5);
  }
}
