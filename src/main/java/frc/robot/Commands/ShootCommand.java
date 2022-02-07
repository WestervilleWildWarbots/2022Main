package frc.robot.Commands;

import java.text.BreakIterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.ShootSubsystem;
import frc.robot.Robot;




public class ShootCommand extends CommandBase{

    private ShootSubsystem shootSubsystem;
    public int Preset = 1;
    public boolean FireAtWill = false;

    public ShootCommand(ShootSubsystem shootSubsystem){
        this.shootSubsystem = shootSubsystem;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        //MUST SMOOTHLY RAMP between current speed and desired speed
        //First, detect Preset(Just for testing purposes for desired speed)
        //Second, Once Preset is checked, get desired maximum speed(This is the Preset)
        //Third, Once desired speed reached, Hold for ~1.5 seconds(Testing, make ~.25 - ~.5 seconds for actual)
        //Fourth, Begin Ramp Down procedure to initial speed of 0
        if (FireAtWill == true) {
            switch(Preset) {
                case 1:shootSubsystem.BeginRamp(0.25);
                break;
                case 2:shootSubsystem.BeginRamp(0.55);
                break;
                case 3:shootSubsystem.BeginRamp(0.75);
                break;
            }
        }
        FireAtWill = false;


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