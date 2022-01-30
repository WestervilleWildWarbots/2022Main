package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase{
    /*
    Mecanum / Neo|CIM / SparkMax  FL 11 
    Mecanum / Neo|CIM / SparkMax  FR 12 
    Mecanum / Neo|CIM / SparkMax  BL 21 
    Mecanum / Neo|CIM / SparkMax  BR 22
    Gyro 50 
    Accelerometer 51
    */
    public DriveSubsystem(){

    }
    
    /*
    Methods:
        Void:    
            tankDrive - drive each side(L/R) of wheels at given speeds
            drive - drive each wheel at given speeds
            PIDdrive - drive each wheel at given speeds with PID calculations
            tickDrive - drive each wheel at given speeds for given numbers of encoder ticks
            setGyro - set the gyro to a given value
            setAccelerometer - set the accelerometer to a given value
            setEncoders (2) - set each encoder to given values []; set all encoders to a value
        Return:
            getGyro - return gyro value
            getAccelerometer - return accelerometer value
            getEncoders - return encoder values []
            getEncoder - return encoder value of given controller id
    */
}
