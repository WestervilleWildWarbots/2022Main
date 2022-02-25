package frc.robot.Commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase{

    private DriveSubsystem driveSubsystem;
    private Joystick driveStick;

    private final double speedscale = 0.7;
    private final double deadzoneY = 0.2;
    private final double deadzoneZ = 0.35;
    private final double deadzoneX = 0.3;

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

    //double leftPower = speedscale*-(z-y);
    //double rightPower = speedscale*(z+y);

    //driveSubsystem.tankDrive(leftPower, rightPower);

    double driveAngle = (-Math.toDegrees(Math.atan2(y,x))-45) % 360;
    double drivePower = speedscale*Math.sqrt(x*x+y*y)/Math.sqrt(2);

    SmartDashboard.putNumber("drive angle", driveAngle);
    SmartDashboard.putNumber("drive power", drivePower);

    double foreslashPower = drivePower*Math.cos(Math.toRadians(driveAngle));
    double backslashPower = drivePower*Math.sin(Math.toRadians(driveAngle));

    double leftPower = speedscale*0.4*z;
    double rightPower = speedscale*0.4*-z;

    driveSubsystem.regularDrive(backslashPower+leftPower, foreslashPower+rightPower, foreslashPower+leftPower, backslashPower+rightPower);

    SmartDashboard.putString("front wheels", (backslashPower+leftPower) + "\t" + (foreslashPower+rightPower));
    SmartDashboard.putString("back wheels", (foreslashPower+leftPower) + "\t" + (backslashPower + rightPower));

  }

    
    
    @Override
    public boolean isFinished() {
      return true;
    }
  
    @Override
    public void end(boolean interrupted) {
    
    }

}