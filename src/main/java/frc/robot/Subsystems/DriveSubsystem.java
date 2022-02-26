package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class DriveSubsystem extends SubsystemBase{
    
    private CANSparkMax frontLeft;
    private CANSparkMax frontRight;
    private CANSparkMax backLeft;
    private CANSparkMax backRight;

    private ADXRS450_Gyro gyro;
    /*
    Mecanum / Neo|CIM / SparkMax  FL 11 
    Mecanum / Neo|CIM / SparkMax  FR 12 
    Mecanum / Neo|CIM / SparkMax  BL 21 
    Mecanum / Neo|CIM / SparkMax  BR 22
    Gyro 50 
    Accelerometer 51
    */
    public DriveSubsystem(){
        frontLeft = new CANSparkMax(11, MotorType.kBrushless);
        frontRight = new CANSparkMax(12, MotorType.kBrushless);
        backLeft = new CANSparkMax(21, MotorType.kBrushless);
        backRight = new CANSparkMax(22, MotorType.kBrushless);

        gyro = new ADXRS450_Gyro();

        frontRight.setInverted(true);
        backRight.setInverted(true);
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
        frontLeft.set(leftSpeed);
        frontRight.set(rightSpeed);
        backLeft.set(leftSpeed);
        backRight.set(rightSpeed);
      }
    
    public void regularDrive(double flSpeed, double frSpeed, double blSpeed, double brSpeed){
        frontLeft.set(flSpeed);
        frontRight.set(frSpeed);
        backLeft.set(blSpeed);
        backRight.set(brSpeed);
    }
    
    public void resetGyro(){
        gyro.reset();
    }

    public double getGyro(){
        return ((gyro.getAngle() + 180 ) % 360) - 180;
    }

    /*
    Methods:
        Void:    
            //tankDrive - drive each side(L/R) of wheels at given speeds
            //drive - drive each wheel at given speeds
            PIDdrive - drive each wheel at given speeds with PID calculations
            tickDrive - drive each wheel at given speeds for given numbers of encoder ticks
            //resetGyro - set the gyro to a zero
            setAccelerometer - set the accelerometer to a given value
            setEncoders (2) - set each encoder to given values []; set all encoders to a value
        Return:
            //getGyro - return gyro value
            getAccelerometer - return accelerometer value
            getEncoders - return encoder values []
            getEncoder - return encoder value of given controller id
    */
}
