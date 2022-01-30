package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drive{
    private CANSparkMax testMotor;
    
    public Drive(){
    testMotor = new CANSparkMax(11, MotorType.kBrushless);
    }
    
    public void go(double speed){
    testMotor.set(speed);
    }
    }