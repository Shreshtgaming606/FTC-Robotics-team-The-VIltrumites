package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Gojo.java - Main TeleOp OpMode
 * 
 * Bot Name: Satoru Gojo
 * Team: The Viltrumites
 */
@TeleOp(name = "Gojo", group = "Main")
public class Gojo extends LinearOpMode {
    
    // ========== HARDWARE DECLARATIONS ==========
    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;
    private Servo intakeServo;
    
    @Override
    public void runOpMode() {
        initializeHardware();
        
        telemetry.addData("Status", "Initialized - Ready to Play!");
        telemetry.update();
        
        waitForStart();
        
        while (opModeIsActive()) {
            
            // ========== GAMEPAD 1 - DRIVER (MOVEMENT) ==========
            
            // 1. Determine Speed Multiplier
            double speedMultiplier = 0.8; // Default
            if (gamepad1.right_bumper) {
                speedMultiplier = 0.4; // Slow mode
                telemetry.addData("Drive Mode", "SLOW");
            } else if (gamepad1.left_bumper) {
                speedMultiplier = 1.0; // Turbo
                telemetry.addData("Drive Mode", "TURBO");
            } else {
                telemetry.addData("Drive Mode", "NORMAL");
            }

            // 2. Calculate Power from Sticks
            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            
            // 3. Trigger Overrides (Forward/Backward only)
            if (gamepad1.right_trigger > 0.1) {
                forward = gamepad1.right_trigger;
            } else if (gamepad1.left_trigger > 0.1) {
                forward = -gamepad1.left_trigger;
            }

            // 4. Apply Mecanum Drive logic
            moveRobot(forward * speedMultiplier, strafe * speedMultiplier, rotate * speedMultiplier);
            
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
            
            // ========== TELEMETRY - Debug Information ==========
            telemetry.addData("Input", "Fwd:%.2f Str:%.2f Rot:%.2f", forward, strafe, rotate);
            telemetry.addData("Motor Powers", "LF:%.2f RF:%.2f LB:%.2f RB:%.2f", 
                leftFrontMotor.getPower(), rightFrontMotor.getPower(), 
                leftBackMotor.getPower(), rightBackMotor.getPower());
            telemetry.update();
        }
        
        stopAllMotors();
    }
    
    private void initializeHardware() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_front");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_back");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_front");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_back");
        intakeServo = hardwareMap.get(Servo.class, "intake_servo");
        
        // Reverse left motors so positive power moves forward
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to brake when power is zero
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        stopAllMotors();
    }

    private void moveRobot(double forward, double strafe, double rotate) {
        double lf = forward + strafe + rotate;
        double lb = forward - strafe + rotate;
        double rf = forward - strafe - rotate;
        double rb = forward + strafe - rotate;

        // Normalize powers to [-1.0, 1.0]
        double max = Math.max(Math.abs(lf), Math.max(Math.abs(lb), Math.max(Math.abs(rf), Math.abs(rb))));
        if (max > 1.0) {
            lf /= max; lb /= max; rf /= max; rb /= max;
        }

        leftFrontMotor.setPower(lf);
        leftBackMotor.setPower(lb);
        rightFrontMotor.setPower(rf);
        rightBackMotor.setPower(rb);
    }
    
    private void stopAllMotors() {
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
    }
}
