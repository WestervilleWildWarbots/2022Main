package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drive{
    private CANSparkMax flMotor, frMotor, blMotor, brMotor;
    
    public Drive(){
        flMotor = new CANSparkMax(11, MotorType.kBrushless);
        frMotor = new CANSparkMax(12, MotorType.kBrushless);
        blMotor = new CANSparkMax(21, MotorType.kBrushless);
        brMotor = new CANSparkMax(22, MotorType.kBrushless);
    }
    
    public void go(double speed){
        flMotor.set(speed);
        frMotor.set(speed);
        blMotor.set(speed);
        brMotor.set(speed);
    }
    }