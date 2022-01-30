package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase{
    /*
    Shoot Camera 0 
    Intake/Front Camera 1 
    Light?
     */

    public VisionSubsystem(){

    }
    
    /*
    Methods:
        Void:
            setLight? - set light to on/off (t/f)
            calcSpeed - calculate % speed to spin flywheel motor at 
            calcDistance- calculate distance to target in inches
            calcAlignment - calculate whether turret is CCW/aligned/CW of target (-1/0/1)
            ???

        Return:
            getDistance - calculate and return distance to target in inches
            getAligned - calculate and return whether turret is at correct angle to shoot (t/f)
            getNeccessarySpeed - calculate and return % speed to spin flywheel motor at
            getCameraIDs - return Camera IDs
            getCameraFeed - return current Camera image

    */
}
