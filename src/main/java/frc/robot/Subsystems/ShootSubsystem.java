package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Commands.ShootCommand;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import java.lang.AutoCloseable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Set;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.html.HTMLDocument.RunElement;

public class ShootSubsystem extends SubsystemBase{
    public CANSparkMax FlywheelMotor;
    public CANSparkMax TurretMotor;
    public Servo LeftServo;
    public Servo RightServo;
    public ColorSensorV3 CSensor;
    public I2C.Port i2cPort = I2C.Port.kOnboard;
    private double HalfSpeed;
    private double QuarterSpeed;
    private double ThreeQuarterSpeed;

    private ColorSensorV3 cSensor;

    /*
    //Flywheel Motor / Redline? / SparkMax 30 
    Turret Rotation Motor / Redline? / SparkMax 34 
    Gate servo L 31 
    Gate servo R 32
    //Color Sensor 52
    Beam Break Sensor 53
     */
    
    public ShootSubsystem(){
        FlywheelMotor = new CANSparkMax(30, MotorType.kBrushless);
        TurretMotor = new CANSparkMax(34, MotorType.kBrushless);

        cSensor = new ColorSensorV3(Port.kOnboard);
    }

    public void BeginRamp(double DesiredSpeed) throws InterruptedException { //Begin ramping the motor to the Desired speed as specified in ShootCommand
        long nowMilli = System.currentTimeMillis();
        long nowSec = (nowMilli/1000)%60;
        Time CurrentTime = new Time(nowSec);
        Time DesiredTime = new Time(nowSec+1);
        boolean SetQ = false;
        boolean SetH = false;
        boolean SetTQ = false;
        QuarterSpeed = DesiredSpeed*0.25;
        HalfSpeed = DesiredSpeed*0.5;
        ThreeQuarterSpeed = DesiredSpeed*0.75;
        
        // double Time = 0;
        while(CurrentTime != DesiredTime){
            if(SetQ == false){
              for(int x =0; x<=1 ; x++){
                FlywheelMotor.set(QuarterSpeed);
              }
              SetQ = true;
            }
            if(SetH == false){
                for(int x =0; x<=1 ; x++){
                  FlywheelMotor.set(HalfSpeed);
                }
                SetH = true;
            }
            if(SetTQ == false){
                for(int x =0; x<=1 ; x++){
                  FlywheelMotor.set(ThreeQuarterSpeed);
                }
                SetTQ = true;
            }
            CurrentTime = new Time(nowSec+1);
        //     if(){
        //         while(Time<=0.25){
        //             FlywheelMotor.set(QuarterSpeed);
        //             Time+=0.01;
        //        }
        //    }
        //   if(Time == 0.26){
        //       while(Time<=0.50){
        //             FlywheelMotor.set(HalfSpeed);
        //             Time+=0.01;
        //         }
        //     }
        //     if(Time == 0.51){
        //         while(Time<=0.75){
        //             FlywheelMotor.set(ThreeQuarterSpeed);
        //             Time+=0.01;
        //         }
        //     }
        //     if(Time == 0.76){
        //         while(Time<=1){
        //             FlywheelMotor.set(DesiredSpeed);
        //             Time+=0.01;
        //         }
        //     }
        }
        wait(2000, 0);
        StopRamp(DesiredSpeed);
    }

    public void StopRamp(double DesiredSpeed){
        long nowMilli = System.currentTimeMillis();
        long nowSec = (nowMilli/1000)%60;
        Time CurrentTime = new Time(nowSec);
        Time DesiredTime = new Time(nowSec+1);
        boolean SetQ = false;
        boolean SetH = false;
        boolean SetTQ = false;
        QuarterSpeed = DesiredSpeed*0.25;
        HalfSpeed = DesiredSpeed*0.5;
        ThreeQuarterSpeed = DesiredSpeed*0.75;
        while(CurrentTime != DesiredTime){
            if(SetTQ == false){
              for(int x =0; x<=1 ; x++){
                FlywheelMotor.set(ThreeQuarterSpeed);
              }
              SetQ = true;
            }
            if(SetH == false){
                for(int x =0; x<=1 ; x++){
                  FlywheelMotor.set(HalfSpeed);
                }
                SetH = true;
            }
            if(SetQ == false){
                for(int x =0; x<=1 ; x++){
                  FlywheelMotor.set(QuarterSpeed);
                }
                SetTQ = true;
            }
            CurrentTime = new Time(nowSec+1);
        }
    }
    public String returnAlliance(boolean R){
        if(R = false){
            return "Red";
        }else{
            return "Blue";
        }
        
    }

    public boolean getBallColor(){
        if(cSensor.getRed() == cSensor.getBlue()){
            return DriverStation.getAlliance() == Alliance.Red;
        }
        return cSensor.getRed() > cSensor.getBlue();
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