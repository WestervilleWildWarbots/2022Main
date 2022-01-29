package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.VisionSubsystem;

public class AimCommand extends CommandBase{

    private VisionSubsystem visionSubsystem;

    public AimCommand(VisionSubsystem visionSubsystem){
        this.visionSubsystem = visionSubsystem;
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