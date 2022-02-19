package frc.robot.Subsystems;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase{
    /*
    Shoot Camera 0 
    Intake/Front Camera 1 
    Light?
     */

    private UsbCamera shootCamera;
    private UsbCamera intakeCamera;

    private CvSink inputStream;
    private CvSource outputStream;
    private Mat source;
    private Mat gray;
    private Mat binary;
    private List<MatOfPoint> contours;
    private Mat hierarchy;
    private Mat output;
    private List<MatOfPoint> targets;

    private static double THRESHOLD = 240;
    private static double MIN_AREA = 40;
    private static double MAX_AREA = Double.MAX_VALUE;

    private static double MIN_RATIO = 2;
    private static double MAX_RATIO = 1000;
    private static double MIN_SOLIDITY_RATIO = 0;

    public VisionSubsystem(){

        shootCamera = CameraServer.startAutomaticCapture(0);
        intakeCamera = CameraServer.startAutomaticCapture(1);

        inputStream = CameraServer.getVideo(shootCamera);
        outputStream = CameraServer.putVideo("TEST", 320, 240);

        source = new Mat();
        gray = new Mat();
        binary = new Mat();
        hierarchy = new Mat();
        output = new Mat();

        targets = new ArrayList<MatOfPoint>();
    }

    public void update(){

        MIN_AREA = SmartDashboard.getNumber("Min Area", 40);
        THRESHOLD = SmartDashboard.getNumber("Brightness Threshold", 240);
        MIN_SOLIDITY_RATIO = SmartDashboard.getNumber("SOLIDITY", 0);
        contours = new ArrayList<MatOfPoint>();
        targets.clear();

        inputStream.grabFrame(source);
        try{
            //Convert USB camera feed to grayscale
            Imgproc.cvtColor(source, gray, Imgproc.COLOR_BGR2GRAY);
            
            //Convert grayscale to binary black + white
            Imgproc.threshold(gray, binary, THRESHOLD, 255, Imgproc.THRESH_BINARY);

            //Find Contours within binary image
            Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            
            //Create RGB Mat to draw contours on
            Imgproc.cvtColor(binary, output, Imgproc.COLOR_GRAY2BGR);

            //Run contour tests
            for (int i = 0; i < contours.size(); i++) {
                
                // Filter extraneously small + large contours :: AREA
                double contourArea = Imgproc.contourArea(contours.get(i));
                
                if(contourArea < MIN_AREA || contourArea > MAX_AREA){
                    continue;
                }

                //Filter extraneously thin + wide contours :: ASPECT RATIO
                Rect boundRect = Imgproc.boundingRect(contours.get(i));
                /*double ratio = (double)boundRect.width/boundRect.height;

                if(ratio < MIN_RATIO || ratio > MAX_RATIO){
                    continue;
                }*/

                //Filter extraneously skewed contours :: SOLIDITY
                double boundingArea = boundRect.width * boundRect.height;
                double solidRatio = contourArea/boundingArea;

                if(solidRatio < MIN_SOLIDITY_RATIO){
                    continue;
                }
                
                //If the contour passes all tests, draw contour to output and add to targets list
                targets.add(contours.get(i));
                Imgproc.drawContours(output, contours, i, new Scalar(0, 0, 255), 5);

            }

            //send output to dashboard
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
