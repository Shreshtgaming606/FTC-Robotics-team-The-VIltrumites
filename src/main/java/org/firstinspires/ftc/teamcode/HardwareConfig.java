package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Hardware Configuration Class
 * 
 * This class manages all hardware initialization and configuration
 * Maps physical devices to logical names
 */
public class HardwareConfig {
    
    // Motors
    public DcMotor leftFrontMotor;
    public DcMotor leftBackMotor;
    public DcMotor rightFrontMotor;
    public DcMotor rightBackMotor;
    
    // Servos
    public Servo intakeServo;
    
    // Hardware map
    public HardwareMap hwMap;
    
    /**
     * Constructor - Initialize with HardwareMap
     */
    public HardwareConfig(HardwareMap hardwareMap) {
        this.hwMap = hardwareMap;
    }
    
    /**
     * Initialize all hardware devices
     * Device names must match your FTC Robot Controller app configuration
     */
    public void init() {
        // Initialize drive motors
        // Names MUST match the configuration in the FTC app on your robot
        leftFrontMotor = hwMap.get(DcMotor.class, "left_front");
        leftBackMotor = hwMap.get(DcMotor.class, "left_back");
        rightFrontMotor = hwMap.get(DcMotor.class, "right_front");
        rightBackMotor = hwMap.get(DcMotor.class, "right_back");
        
        // Initialize servos
        intakeServo = hwMap.get(Servo.class, "intake_servo");
        
        // Set motor directions (adjust as needed for your robot)
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        
        // Set motor modes
        setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        // Stop all motors
        stopAllMotors();
    }
    
    /**
     * Set mode for all drive motors
     */
    public void setMotorMode(DcMotor.RunMode mode) {
        leftFrontMotor.setMode(mode);
        leftBackMotor.setMode(mode);
        rightFrontMotor.setMode(mode);
        rightBackMotor.setMode(mode);
    }
    
    /**
     * Stop all motors
     */
    public void stopAllMotors() {
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
    }
    
    /**
     * Drive with tank drive (left side / right side)
     */
    public void tankDrive(double leftPower, double rightPower) {
        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);
    }
    
    /**
     * Drive with arcade style (forward and rotation)
     */
    public void arcadeDrive(double forward, double rotate) {
        double left = forward + rotate;
        double right = forward - rotate;
        
        // Normalize to [-1, 1]
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1) {
            left /= max;
            right /= max;
        }
        
        tankDrive(left, right);
    }
}
