package frc.robot;

import com.revrobotics.CANSparkMax;

public class Drive{
    private CANSparkMax testMotor;
    
    public Drive(){
    testMotor = new CANSparkMax(11);
    }
    
    public void go(double speed){
    testMotor.set(speed);
    }
    }