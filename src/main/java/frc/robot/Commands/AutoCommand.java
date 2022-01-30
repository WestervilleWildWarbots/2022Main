package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCommand extends CommandBase{
    
    private String route;

    public AutoCommand(String route){
      this.route = route;
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