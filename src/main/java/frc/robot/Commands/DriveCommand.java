package frc.robot.Commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase{

    private DriveSubsystem driveSubsystem;
    private Joystick driveStick;

    private final double speedscale = 0.7;
    private final double deadzoneY = 0.2;
    private final double deadzoneZ = 0.35;
    private final double deadzoneX = 0.25;

    public DriveCommand(DriveSubsystem driveSubsystem, Joystick driveStick){
        this.driveSubsystem = driveSubsystem;
        this.driveStick = driveStick;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){     

    double x = driveStick.getX();
    double y = driveStick.getY();
    double z = driveStick.getZ();

    //Deadzone Code
    if (Math.abs(x) <= deadzoneX) {
      x = 0;
    }

    if (Math.abs(y) <= deadzoneY) {
      y = 0;
    }

    if (Math.abs(z) <= deadzoneZ) {
      z = 0;
    }


    z*=Math.abs(z);

    double leftPower = speedscale*-(z-y);
    double rightPower = speedscale*(z+y);

    driveSubsystem.tankDrive(leftPower, rightPower);

  }

    
    
    @Override
    public boolean isFinished() {
      return true;
    }
  
    @Override
    public void end(boolean interrupted) {
    
    }

}