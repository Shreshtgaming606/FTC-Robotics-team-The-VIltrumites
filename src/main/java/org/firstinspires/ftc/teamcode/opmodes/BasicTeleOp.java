package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * TeleOp OpMode Template
 * 
 * This is a basic template for manual robot control
 * The robot will respond to gamepad input while this OpMode is running
 * 
 * Gamepad1: Driver controls (movement)
 * Gamepad2: Manipulator controls (intake, arm, etc)
 */
@TeleOp(name = "Basic TeleOp", group = "Drive")
public class BasicTeleOp extends LinearOpMode {
    
    // Hardware configuration
    private HardwareConfig robot;
    
    @Override
    public void runOpMode() {
        // Initialize hardware
        robot = new HardwareConfig(hardwareMap);
        robot.init();
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // Main control loop
        while (opModeIsActive()) {
            // ========== DRIVER CONTROLS (Gamepad 1) ==========
            
            // Arcade drive: left_stick_y = forward/backward, right_stick_x = turn
            double forward = -gamepad1.left_stick_y;  // Invert Y axis
            double rotate = gamepad1.right_stick_x;
            
            robot.arcadeDrive(forward * 0.8, rotate * 0.6);  // Scale down for safety
            
            // Alternative: Tank drive (left stick left/right, right stick for turning)
            // double leftPower = -gamepad1.left_stick_y;
            // double rightPower = -gamepad1.right_stick_y;
            // robot.tankDrive(leftPower * 0.8, rightPower * 0.8);
            
            // ========== MANIPULATOR CONTROLS (Gamepad 2) ==========
            
            // Intake servo example
            if (gamepad2.a) {
                robot.intakeServo.setPosition(0.0);  // Open
            } else if (gamepad2.b) {
                robot.intakeServo.setPosition(1.0);  // Close
            }
            
            // ========== TELEMETRY (Display Debug Info) ==========
            
            telemetry.addData("Status", "Running");
            telemetry.addData("Forward", forward);
            telemetry.addData("Rotate", rotate);
            telemetry.addData("Left Front Motor", robot.leftFrontMotor.getPower());
            telemetry.addData("Right Front Motor", robot.rightFrontMotor.getPower());
            telemetry.addData("Gamepad 1 Connected", gamepad1_start);
            telemetry.addData("Gamepad 2 Connected", gamepad2_start);
            telemetry.update();
        }
        
        // Stop all motors when OpMode ends
        robot.stopAllMotors();
    }
}
