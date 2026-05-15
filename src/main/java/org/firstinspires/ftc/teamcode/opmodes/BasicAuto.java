package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Autonomous OpMode Template
 * 
 * This is a basic template for autonomous robot operation
 * The robot will execute a predetermined sequence of movements
 * 
 * Key Methods:
 * - driveStraight() - Drive forward/backward
 * - turn() - Rotate the robot
 * - sleep() - Pause for a set duration
 */
@Autonomous(name = "Basic Auto", group = "Autonomous")
public class BasicAuto extends LinearOpMode {
    
    // Hardware configuration
    private HardwareConfig robot;
    
    // Timer for autonomous
    private ElapsedTime runtime = new ElapsedTime();
    
    @Override
    public void runOpMode() {
        // Initialize hardware
        robot = new HardwareConfig(hardwareMap);
        robot.init();
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start
        waitForStart();
        runtime.reset();
        
        telemetry.addData("Status", "Running");
        telemetry.update();
        
        // ========== AUTONOMOUS SEQUENCE ==========
        
        // Example: Drive forward for 2 seconds
        driveStraight(0.5, 2.0);
        
        // Stop and wait a bit
        robot.stopAllMotors();
        sleep(500);
        
        // Example: Turn right for 1 second
        turn(0.5, 1.0);
        
        // Drive forward again
        driveStraight(0.5, 2.0);
        
        // Stop
        robot.stopAllMotors();
        
        telemetry.addData("Status", "Complete");
        telemetry.update();
    }
    
    /**
     * Drive straight forward or backward
     * @param power Speed (-1.0 to 1.0). Positive = forward, Negative = backward
     * @param timeoutSeconds Time in seconds to drive
     */
    private void driveStraight(double power, double timeoutSeconds) {
        // Reset timer
        runtime.reset();
        
        // Drive while time hasn't elapsed and OpMode is still active
        while (opModeIsActive() && runtime.seconds() < timeoutSeconds) {
            robot.arcadeDrive(power, 0);  // Forward, no rotation
            
            telemetry.addData("Time", "%.1f / %.1f", runtime.seconds(), timeoutSeconds);
            telemetry.addData("Status", "Driving Forward");
            telemetry.update();
        }
        
        robot.stopAllMotors();
    }
    
    /**
     * Turn robot left or right
     * @param power Rotation speed (-1.0 to 1.0). Positive = right turn, Negative = left turn
     * @param timeoutSeconds Time in seconds to turn
     */
    private void turn(double power, double timeoutSeconds) {
        runtime.reset();
        
        while (opModeIsActive() && runtime.seconds() < timeoutSeconds) {
            robot.arcadeDrive(0, power);  // No forward movement, just rotation
            
            telemetry.addData("Time", "%.1f / %.1f", runtime.seconds(), timeoutSeconds);
            telemetry.addData("Status", "Turning");
            telemetry.update();
        }
        
        robot.stopAllMotors();
    }
    
    /**
     * Helper: Pause execution
     */
    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
