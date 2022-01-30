package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShootSubsystem extends SubsystemBase{
    
    /*
    Flywheel Motor / Redline? / SparkMax 30 
    Turret Rotation Motor / Redline? / SparkMax 34 
    Gate servo L 31 
    Gate servo R 32
    Color Sensor 52
    Beam Break Sensor 53
     */

    public ShootSubsystem(){

    }
    
    /*
    Methods:
        Void:
            shoot - spin flywheel motor at given speed 
            PIDshoot - spin flywheel motor at given speed with PID calculations
            rotate - rotate "lazy susan" to a given position
            toggleGate - set gate servos to open if closed or closed if open
            setGate - set gate servos to open/closed (t/f)
            setAllianceColor - set Alliance color to red/blue (t/f) 
        Return:
            getTurretRotation - return lazy susan position
            getShootSpeed - return current flywheel motor speed
            getBallColor - return whether held ball is red/blue (t/f)
            getGateState - return whether gate servos are open/closed (t/f)
            getBeamState - return whether beam is broken/unbroken (t/f)

    */
}
