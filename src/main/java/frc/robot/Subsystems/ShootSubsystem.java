package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Commands.ShootCommand;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootSubsystem extends SubsystemBase{
    
    private CANSparkMax flywheelMotor;
    private CANSparkMax turretMotor;
    private Servo leftServo;
    private Servo rightServo;
    private double currentSpeed = 0;

    private ColorSensorV3 cSensor;
    private boolean gateOpen;

    /*
    //Flywheel Motor / Redline? / SparkMax 30 
    //Turret Rotation Motor / Redline? / SparkMax 34 
    //Gate servo L 1 
    //Gate servo R 2
    //Color Sensor 52
    Beam Break Sensor 53?
     */
    
    public ShootSubsystem(){
        flywheelMotor = new CANSparkMax(30, MotorType.kBrushless);
        flywheelMotor.setIdleMode(IdleMode.kCoast);
        flywheelMotor.setOpenLoopRampRate(2);
        flywheelMotor.setClosedLoopRampRate(2);
        turretMotor = new CANSparkMax(34, MotorType.kBrushless);

        cSensor = new ColorSensorV3(Port.kOnboard);

        leftServo = new Servo(1);
        rightServo = new Servo(2);

        gateOpen = false;
    }

    //Shoot Methods
    public void ramp(double speed){

        if(currentSpeed < speed){
            currentSpeed += 0.01;
        }else if(currentSpeed > speed){
            currentSpeed -= 0.01;
        }

        flywheelMotor.set(currentSpeed);
        SmartDashboard.putNumber("Ramp", currentSpeed);
    }

    public void rampReset(){
        currentSpeed = 0;
    }

    public double getShootSpeed(){
        return currentSpeed;
    };


    //In Hopper Methods
    public boolean getBallColor(){
        if(cSensor.getRed() == cSensor.getBlue()){
            return DriverStation.getAlliance() == Alliance.Red;
        }
        return cSensor.getRed() > cSensor.getBlue();
    }

    public void toggleGate(){
        gateOpen = !gateOpen;
    }

    public void setGate(boolean state){
        gateOpen = state;
    }

    public void updateServos(){
        if(gateOpen){
            leftServo.setAngle(0);
            rightServo.setAngle(0);
        }else{
            leftServo.setAngle(90);
            rightServo.setAngle(90);
        }
    }

    public boolean getGateState(){
        return gateOpen;
    }


    /*
    Methods:
        Void:
            /shoot - spin flywheel motor at given speed 
            PIDshoot - spin flywheel motor at given speed with PID calculations
            rotate - rotate "lazy susan" to a given position
            /toggleGate - set gate servos to open if closed or closed if open
            /setGate - set gate servos to open/closed (t/f) 
        Return:
            getTurretRotation - return lazy susan position
            /getShootSpeed - return current flywheel motor speed
            /getBallColor - return whether held ball is red/blue (t/f)
            /getGateState - return whether gate servos are open/closed (t/f)
            getBeamState - return whether beam is broken/unbroken (t/f)

    */
}