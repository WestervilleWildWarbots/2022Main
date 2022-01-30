package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.ShootSubsystem;

public class ShootCommand extends CommandBase{

    private ShootSubsystem shootSubsystem;

    public ShootCommand(ShootSubsystem shootSubsystem){
        this.shootSubsystem = shootSubsystem;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        //MUST SMOOTHLY RAMP between current speed and desired speed
        //TODO: sense color and spit out ball if not our team color
    }
    
    @Override
    public boolean isFinished() {
      return true;
    }
  
    @Override
    public void end(boolean interrupted) {
    
    }

}