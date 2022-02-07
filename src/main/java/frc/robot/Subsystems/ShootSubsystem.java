package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Commands.ShootCommand;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import java.lang.AutoCloseable;
import java.sql.Time;

public class ShootSubsystem extends SubsystemBase{
    private CANSparkMax FlywheelMotor;
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
        cSensor = new ColorSensorV3(Port.kOnboard);
    }

    public void BeginRamp(double DesiredSpeed) { //Begin ramping the motor to the Desired speed as specified in ShootCommand
        QuarterSpeed = DesiredSpeed*0.25;
        HalfSpeed = DesiredSpeed*0.5;
        ThreeQuarterSpeed = DesiredSpeed*0.75;
        
        double Time = 0;
        
        if(Time == 0){
            while(Time<=0.25){
                FlywheelMotor.set(QuarterSpeed);
                Time+=0.01;
            }
        }
        if(Time == 0.26){
            while(Time<=0.50){
                FlywheelMotor.set(HalfSpeed);
                Time+=0.01;
            }
        }
        if(Time == 0.51){
            while(Time<=0.75){
                FlywheelMotor.set(ThreeQuarterSpeed);
                Time+=0.01;
            }
        }
        if(Time == 0.76){
            while(Time<=1){
                FlywheelMotor.set(DesiredSpeed);
                Time+=0.01;
            }
        }
    }

    public boolean getBallColor(){
        if(cSensor.getRed() == cSensor.getBlue()){
            return true; //TODO: get alliance color as not default
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