package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Gojo.java - Main TeleOp OpMode
 * 
 * This is the main file where you'll add all your gamepad control logic
 * using if statements to check button presses and execute actions
 * 
 * Bot Name: Satoru Gojo
 * Team: The Viltrumites
 */
@TeleOp(name = "Gojo", group = "Main")
public class Gojo extends LinearOpMode {
    
    // ========== HARDWARE DECLARATIONS ==========
    
    // Drive Motors
    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;
    
    // Servos and Other Motors
    private Servo intakeServo;
    
    // ========== INITIALIZATION ==========
    
    @Override
    public void runOpMode() {
        // Initialize all hardware
        initializeHardware();
        
        telemetry.addData("Status", "Initialized - Ready to Play!");
        telemetry.update();
        
        // Wait for driver to press PLAY
        waitForStart();
        
        // ========== MAIN CONTROL LOOP ==========
        
        while (opModeIsActive()) {
            
            // ========== GAMEPAD 1 - DRIVER (MOVEMENT) ==========
            
            // Analog Sticks for Movement
            double forwardPower = -gamepad1.left_stick_y;   // Forward/Backward
            double turnPower = gamepad1.right_stick_x;      // Turn Left/Right
            
            // Drive the robot
            arcadeDrive(forwardPower * 0.8, turnPower * 0.6);
            
            // Button A - Example Action
            if (gamepad1.a) {
                telemetry.addData("Button A", "Pressed!");
                // Add your code here
            }
            
            // Button B - Example Action
            if (gamepad1.b) {
                telemetry.addData("Button B", "Pressed!");
                // Add your code here
            }
            
            // Button X - Example Action
            if (gamepad1.x) {
                telemetry.addData("Button X", "Pressed!");
                // Add your code here
            }
            
            // Button Y - Example Action
            if (gamepad1.y) {
                telemetry.addData("Button Y", "Pressed!");
                // Add your code here
            }
            
            // Left Bumper
            if (gamepad1.left_bumper) {
                telemetry.addData("Left Bumper", "Pressed!");
                // Add your code here
            }
            
            // Right Bumper
            if (gamepad1.right_bumper) {
                telemetry.addData("Right Bumper", "Pressed!");
                // Add your code here
            }
            
            // Left Trigger (0.0 to 1.0)
            if (gamepad1.left_trigger > 0.5) {
                telemetry.addData("Left Trigger", gamepad1.left_trigger);
                // Add your code here
            }
            
            // Right Trigger (0.0 to 1.0)
            if (gamepad1.right_trigger > 0.5) {
                telemetry.addData("Right Trigger", gamepad1.right_trigger);
                // Add your code here
            }
            
            // D-Pad Up
            if (gamepad1.dpad_up) {
                telemetry.addData("D-Pad", "UP");
                // Add your code here
            }
            
            // D-Pad Down
            if (gamepad1.dpad_down) {
                telemetry.addData("D-Pad", "DOWN");
                // Add your code here
            }
            
            // D-Pad Left
            if (gamepad1.dpad_left) {
                telemetry.addData("D-Pad", "LEFT");
                // Add your code here
            }
            
            // D-Pad Right
            if (gamepad1.dpad_right) {
                telemetry.addData("D-Pad", "RIGHT");
                // Add your code here
            }
            
            // ========== GAMEPAD 2 - MANIPULATOR (MECHANISMS) ==========
            
            // Button A - Open Intake
            if (gamepad2.a) {
                intakeServo.setPosition(0.0);
                telemetry.addData("Intake", "OPEN");
            }
            
            // Button B - Close Intake
            if (gamepad2.b) {
                intakeServo.setPosition(1.0);
                telemetry.addData("Intake", "CLOSED");
            }
            
            // Button X
            if (gamepad2.x) {
                telemetry.addData("Button X (P2)", "Pressed!");
                // Add your code here
            }
            
            // Button Y
            if (gamepad2.y) {
                telemetry.addData("Button Y (P2)", "Pressed!");
                // Add your code here
            }
            
            // Left Bumper
            if (gamepad2.left_bumper) {
                telemetry.addData("Left Bumper (P2)", "Pressed!");
                // Add your code here
            }
            
            // Right Bumper
            if (gamepad2.right_bumper) {
                telemetry.addData("Right Bumper (P2)", "Pressed!");
                // Add your code here
            }
            
            // Left Trigger
            if (gamepad2.left_trigger > 0.5) {
                telemetry.addData("Left Trigger (P2)", gamepad2.left_trigger);
                // Add your code here
            }
            
            // Right Trigger
            if (gamepad2.right_trigger > 0.5) {
                telemetry.addData("Right Trigger (P2)", gamepad2.right_trigger);
                // Add your code here
            }
            
            // ========== TELEMETRY - Debug Information ==========
            
            telemetry.addData("Status", "Running");
            telemetry.addData("Forward Power", forwardPower);
            telemetry.addData("Turn Power", turnPower);
            telemetry.addData("Left Front Motor", leftFrontMotor.getPower());
            telemetry.addData("Right Front Motor", rightFrontMotor.getPower());
            telemetry.addData("Gamepad 1 Connected", gamepad1_start);
            telemetry.addData("Gamepad 2 Connected", gamepad2_start);
            telemetry.update();
        }
        
        // Stop all motors when OpMode ends
        stopAllMotors();
    }
    
    // ========== HELPER METHODS ==========
    
    /**
     * Initialize all hardware devices
     * Update device names to match your FTC Robot Controller app configuration
     */
    private void initializeHardware() {
        // Motors
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_front");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_back");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_front");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_back");
        
        // Servos
        intakeServo = hardwareMap.get(Servo.class, "intake_servo");
        
        // Set motor directions
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        
        // Set motor modes
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        // Stop all motors
        stopAllMotors();
    }
    
    /**
     * Stop all motors
     */
    private void stopAllMotors() {
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
    }
    
    /**
     * Arcade drive: forward/backward + rotation
     * @param forward Forward power (-1.0 to 1.0)
     * @param rotate Rotation power (-1.0 to 1.0)
     */
    private void arcadeDrive(double forward, double rotate) {
        double left = forward + rotate;
        double right = forward - rotate;
        
        // Normalize to [-1, 1]
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1) {
            left /= max;
            right /= max;
        }
        
        leftFrontMotor.setPower(left);
        leftBackMotor.setPower(left);
        rightFrontMotor.setPower(right);
        rightBackMotor.setPower(right);
    }
    
    /**
     * Tank drive: separate left/right power
     * @param leftPower Left side power (-1.0 to 1.0)
     * @param rightPower Right side power (-1.0 to 1.0)
     */
    private void tankDrive(double leftPower, double rightPower) {
        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);
    }
}
