package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.IntakeSubsystem;

public class UnlatchCommand extends CommandBase{

    private IntakeSubsystem intakeSubsystem;

    public UnlatchCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
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