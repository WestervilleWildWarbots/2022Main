package frc.robot.Subsystems;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
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
    private ArrayList<Mat> channels;
    private Mat blue, green, red;
    private double redWeight,blueWeight,greenWeight;
    private MatOfPoint target;
    private Mat additive;

    private static double THRESHOLD = 240;
    private static double MIN_AREA = 10;
    private static double MAX_AREA = 200;

    private static double TARGET_ASPECT_RATIO = 2.75;
    private static double TARGET_SOLIDITY_RATIO = 1;

    private static int EXPOSURE;

    private static final int[][] kernelRef = {
        {0,1,0},
        {1,1,1},
        {0,1,0},
    };

    private static Mat kernel;

    public VisionSubsystem(){

        shootCamera = CameraServer.startAutomaticCapture(0);
        //intakeCamera = CameraServer.startAutomaticCapture(1);

        shootCamera.setResolution(160,120);
        //shootCamera.setFPS(24);
        //intakeCamera.setResolution(320,240);
        //intakeCamera.setFPS(24);

        shootCamera.setExposureAuto();

        inputStream = CameraServer.getVideo(shootCamera);
        outputStream = CameraServer.putVideo("TEST", 320, 240);

        source = new Mat();
        gray = new Mat();
        binary = new Mat();
        hierarchy = new Mat();
        output = new Mat();

        channels = new ArrayList<Mat>();
        blue =  new Mat();
        green = new Mat();
        red =  new Mat();
        additive = new Mat();

        contours = new ArrayList<MatOfPoint>();
        target = new MatOfPoint();

        kernel = new Mat();

        for(int j = 0; j < kernelRef.length; j++){
            for(int i = 0; i < kernelRef[j].length; i++){
                kernel.put(j,i,kernelRef[j][i]);
            }
        }
    }

    public void update(){

        MIN_AREA = SmartDashboard.getNumber("Min Area", 10);
        MAX_AREA = SmartDashboard.getNumber("Max Area", 200);
        THRESHOLD = SmartDashboard.getNumber("Brightness Threshold", 240);
        TARGET_SOLIDITY_RATIO = SmartDashboard.getNumber("SOLIDITY", 1);
        TARGET_ASPECT_RATIO = SmartDashboard.getNumber("ASPECT", 2.75);
        blueWeight = SmartDashboard.getNumber("BLUE WEIGHT", 1);
        greenWeight = SmartDashboard.getNumber("GREEN WEIGHT", 1);
        redWeight = SmartDashboard.getNumber("RED WEIGHT", 1);
        
        //EXPOSURE = (int)SmartDashboard.getNumber("EXPOSURE", 10);
        contours.clear();
        target.release();

        inputStream.grabFrame(source);
        try{
    
            
            /*//Extract g channel
            Core.split(source, channels);
            
            red = channels.get(2);
            green = channels.get(1);
            blue = channels.get(0);
            
            /*Core.multiply(red, new Scalar(redWeight), red);
            Core.multiply(green, new Scalar(greenWeight), green);
            Core.multiply(blue, new Scalar(blueWeight), blue);
            
            channels.set(2, red);
            channels.set(1, green);
            channels.set(0, blue);

            Core.merge(channels, additive);

            Core.addWeighted(additive, 1, source, 1, 0, source);
            

            Core.addWeighted(red, redWeight, green, greenWeight, 0, additive);
            Core.addWeighted(additive, 1, blue, blueWeight, 0, additive);
            
            channels.set(0, additive);
            channels.set(1, additive);
            channels.set(2, additive);

            Core.merge(channels, source);
            */

            //Convert USB camera feed to grayscale
            Imgproc.cvtColor(source, gray, Imgproc.COLOR_BGR2GRAY);

            //Convert grayscale to binary black + white
            Imgproc.threshold(gray, binary, THRESHOLD, 255, Imgproc.THRESH_BINARY);
            
            //Imgproc.morphologyEx(binary, binary, Imgproc.MORPH_OPEN, kernel);
            //Imgproc.morphologyEx(binary, binary, Imgproc.MORPH_DILATE, kernel);

            //Find Contours within binary image
            Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            
            //Create RGB Mat to draw contours on
            Imgproc.cvtColor(binary, output, Imgproc.COLOR_GRAY2BGR);
            
            //Run contour tests
            
            double contourMin = Double.MAX_VALUE;
            int minIndex = 0;

            for (int i = 0; i < contours.size(); i++) {
                
                double contourArea = Imgproc.contourArea(contours.get(i));
                Rect boundRect = Imgproc.boundingRect(contours.get(i));
                double aspectRatio = (double)boundRect.width/boundRect.height;
                double boundingArea = boundRect.width * boundRect.height;
                double solidRatio = boundingArea/contourArea;

                // Filter extraneously small + large contours :: AREA
                if(contourArea < MIN_AREA || contourArea > MAX_AREA){
                    continue;
                }
                
                double contourValue = Math.abs(aspectRatio - TARGET_ASPECT_RATIO) + Math.abs(solidRatio - TARGET_SOLIDITY_RATIO);
                //System.out.println(contourValue);
                if(contourValue < contourMin){
                    minIndex = i;
                    contourMin = contourValue;
                }

                /*//Filter extraneously thin + wide contours :: ASPECT RATIO
                if(ratio < MIN_RATIO || ratio > MAX_RATIO){
                    continue;
                }
                //Filter extraneously skewed contours :: SOLIDITY
                if(solidRatio < MIN_SOLIDITY_RATIO){
                    continue;
                }*/
                //If the contour passes all tests, draw contour to output and add to targets list
                //targets.add(contours.get(i));
                Imgproc.drawContours(output, contours, i, new Scalar(255, 0, 0), 5);
            }

            //send output to dashboard
            if(contours.size() > 0){
            target = contours.get(minIndex);
            Imgproc.drawContours(output, contours, minIndex, new Scalar(0, 0, 255), 5);
            }
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
