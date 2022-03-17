package frc.robot.Subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class DriveSubsystem extends SubsystemBase{
    
    private CANSparkMax frontLeft;
    private CANSparkMax frontRight;
    private CANSparkMax backLeft;
    private CANSparkMax backRight;

    private double pBase, iBase, dBase, p11, i11, d11, p12, i12, d12, p21, i21, d21, p22, i22, d22; 
    private PIDController controller11, controller12, controller21, controller22;

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

        pBase = SmartDashboard.getNumber("pBase", 0);
        iBase = SmartDashboard.getNumber("iBase", 0);
        dBase = SmartDashboard.getNumber("dBase", 0);

        p11 = SmartDashboard.getNumber("p11", 0);
        i11 = SmartDashboard.getNumber("i11", 0);
        d11 = SmartDashboard.getNumber("d11", 0);

        p12 = SmartDashboard.getNumber("p12", 0);
        i12 = SmartDashboard.getNumber("i12", 0);
        d12 = SmartDashboard.getNumber("d12", 0);

        p21 = SmartDashboard.getNumber("p21", 0);
        i21 = SmartDashboard.getNumber("i21", 0);
        d21 = SmartDashboard.getNumber("d21", 0);

        p22 = SmartDashboard.getNumber("p22", 0);
        i22 = SmartDashboard.getNumber("i22", 0);
        d22 = SmartDashboard.getNumber("d22", 0);

        controller11 = new PIDController(pBase + p11, iBase + i11, dBase + d11);
        controller12 = new PIDController(pBase + p12, iBase + i12, dBase + d12);
        controller21 = new PIDController(pBase + p21, iBase + i21, dBase + d21);
        controller22 = new PIDController(pBase + p22, iBase + i22, dBase + d22);

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

    public void PIDDrive(double flSpeed, double frSpeed, double blSpeed, double brSpeed){
        frontLeft.set(controller11.calculate(flSpeed));
        frontRight.set(controller12.calculate(frSpeed));
        backLeft.set(controller21.calculate(blSpeed));
        backRight.set(controller22.calculate(brSpeed));
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
