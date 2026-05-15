package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.HardwareConfig;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

/**
 * Example TeleOp Using Subsystems
 * 
 * Shows how to use subsystem classes for cleaner code organization
 * This is a best practice for larger robot projects
 */
@TeleOp(name = "TeleOp With Subsystems", group = "Drive")
public class TeleOpWithSubsystems extends LinearOpMode {
    
    private HardwareConfig robot;
    private IntakeSubsystem intake;
    
    @Override
    public void runOpMode() {
        // Initialize hardware and subsystems
        robot = new HardwareConfig(hardwareMap);
        robot.init();
        
        intake = new IntakeSubsystem(hardwareMap);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        waitForStart();
        
        while (opModeIsActive()) {
            // ========== DRIVE CONTROLS ==========
            double forward = -gamepad1.left_stick_y;
            double rotate = gamepad1.right_stick_x;
            robot.arcadeDrive(forward * 0.8, rotate * 0.6);
            
            // ========== INTAKE CONTROLS ==========
            if (gamepad2.a) {
                intake.open();
            } else if (gamepad2.b) {
                intake.close();
            }
            
            // ========== TELEMETRY ==========
            telemetry.addData("Status", "Running");
            telemetry.addData("Intake Position", intake.getPosition());
            telemetry.update();
        }
        
        robot.stopAllMotors();
    }
}
