package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.ShootSubsystem;

public class GateCommand extends CommandBase{

    private ShootSubsystem shootSubsystem;

    public GateCommand(ShootSubsystem shootSubsystem){
        this.shootSubsystem = shootSubsystem;
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