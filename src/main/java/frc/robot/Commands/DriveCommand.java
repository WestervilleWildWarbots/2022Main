package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase{

    private DriveSubsystem driveSubsystem;
    private Joystick driveStick;

    public DriveCommand(DriveSubsystem driveSubsystem, Joystick driveStick){
        this.driveSubsystem = driveSubsystem;
        this.driveStick = driveStick;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){

    }
    
    @Override
    public boolean isFinished() {
      return true;
    }
  
    @Override
    public void end(boolean interrupted) {
    
    }

}