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

public class ShootSubsystem extends SubsystemBase{
    private CANSparkMax flywheelMotor;
    private CANSparkMax turretMotor;
    private Servo leftServo;
    private Servo rightServo;
    private double currentSpeed = 0;

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
        flywheelMotor = new CANSparkMax(30, MotorType.kBrushless);
        turretMotor = new CANSparkMax(34, MotorType.kBrushless);

        cSensor = new ColorSensorV3(Port.kOnboard);
    }

    public void ramp(double speed){

        if(currentSpeed < speed){
            currentSpeed += 0.01;
        }else if(currentSpeed > speed){
            currentSpeed -= 0.01;
        }

        flywheelMotor.set(currentSpeed);
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