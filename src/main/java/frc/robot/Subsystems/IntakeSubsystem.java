package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    
    /*
    //Intake Motor / Redline / SparkMax 40 
    Latch servo L 41 
    Latch servo R 42 
    */

    CANSparkMax intakeMotor;

    public IntakeSubsystem(){
        intakeMotor = new CANSparkMax(40,MotorType.kBrushless);
    }

    public void spin(double speed){
        intakeMotor.set(speed);
    }

    /*
    Methods:
        Void:
            /setSpeed - set intake motor to given speed
            release - set latch servos to released state
            latch - set latch servos to latched state
        Return:
            getLatched - return whether latch servos are latched/released (t/f)
    */
    
}
