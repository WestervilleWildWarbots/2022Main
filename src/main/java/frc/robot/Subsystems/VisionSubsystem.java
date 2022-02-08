package frc.robot.Subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase{
    /*
    Shoot Camera 0 
    Intake/Front Camera 1 
    Light?
     */

    private UsbCamera shootCamera;
    private UsbCamera intakeCamera;

    private CvSink sink;
    private CvSource outputStream;
    private Mat source;
    private Mat output;

    public VisionSubsystem(){

        shootCamera = CameraServer.startAutomaticCapture(0);
        intakeCamera = CameraServer.startAutomaticCapture(1);

        sink = CameraServer.getVideo(shootCamera);
        outputStream = CameraServer.putVideo("TEST", 320, 240);

        source = new Mat();
        output = new Mat();

    }

    public void update(){
        sink.grabFrame(source);
        try{
            Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
            outputStream.putFrame(output);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    Methods:
        Void:
            setLight? - set light to on/off (t/f)
            calcSpeed - calculate % speed to spin flywheel motor at 
            calcDistance- calculate distance to target in inches
            calcAlignment - calculate whether turret is CCW/aligned/CW of target (-1/0/1)
            ???

        Return:
            getDistance - calculate and return distance to target in inches
            getAligned - calculate and return whether turret is at correct angle to shoot (t/f)
            getNeccessarySpeed - calculate and return % speed to spin flywheel motor at
            getCameraIDs - return Camera IDs
            getCameraFeed - return current Camera image

    */
}
