package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Subsystem Template
 * 
 * Subsystems are reusable components that encapsulate robot hardware
 * This example shows how to create a reusable "Intake" subsystem
 * 
 * Benefits:
 * - Clean, organized code
 * - Easy to test individual components
 * - Reusable across multiple OpModes
 * - Easier to debug issues
 */
public class IntakeSubsystem {
    
    // Hardware
    private Servo intakeServo;
    
    // Servo positions
    public static final double SERVO_OPEN = 0.0;
    public static final double SERVO_CLOSED = 1.0;
    
    /**
     * Constructor - Initialize with HardwareMap
     */
    public IntakeSubsystem(HardwareMap hardwareMap) {
        init(hardwareMap);
    }
    
    /**
     * Initialize hardware
     */
    private void init(HardwareMap hardwareMap) {
        intakeServo = hardwareMap.get(Servo.class, "intake_servo");
        
        // Set initial position
        intakeServo.setPosition(SERVO_OPEN);
    }
    
    /**
     * Open the intake
     */
    public void open() {
        intakeServo.setPosition(SERVO_OPEN);
    }
    
    /**
     * Close the intake
     */
    public void close() {
        intakeServo.setPosition(SERVO_CLOSED);
    }
    
    /**
     * Get current servo position
     */
    public double getPosition() {
        return intakeServo.getPosition();
    }
    
    /**
     * Set servo to specific position (0.0 to 1.0)
     */
    public void setPosition(double position) {
        intakeServo.setPosition(position);
    }
}
