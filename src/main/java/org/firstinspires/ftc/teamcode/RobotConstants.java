package org.firstinspires.ftc.teamcode;

/**
 * Constants Class
 * 
 * Store all robot configuration constants here
 * Makes it easy to adjust values without changing code in multiple files
 */
public class RobotConstants {
    
    // ========== HARDWARE NAMES ==========
    // These must match the names in your FTC Robot Controller app configuration
    
    public static final String LEFT_FRONT_MOTOR = "left_front";
    public static final String LEFT_BACK_MOTOR = "left_back";
    public static final String RIGHT_FRONT_MOTOR = "right_front";
    public static final String RIGHT_BACK_MOTOR = "right_back";
    
    public static final String INTAKE_SERVO = "intake_servo";
    
    // ========== MOTOR DIRECTIONS ==========
    // Adjust based on your robot's motor orientations
    
    public static final boolean LEFT_FRONT_REVERSED = true;
    public static final boolean LEFT_BACK_REVERSED = true;
    public static final boolean RIGHT_FRONT_REVERSED = false;
    public static final boolean RIGHT_BACK_REVERSED = false;
    
    // ========== SERVO POSITIONS ==========
    
    public static final double INTAKE_SERVO_OPEN = 0.0;
    public static final double INTAKE_SERVO_CLOSED = 1.0;
    
    // ========== DRIVE SPEEDS ==========
    // Scale factors for different driving modes
    
    public static final double TELEOP_DRIVE_SPEED = 0.8;
    public static final double TELEOP_TURN_SPEED = 0.6;
    public static final double AUTO_DRIVE_SPEED = 0.5;
    
    // ========== AUTONOMOUS TIMINGS ==========
    
    public static final double AUTO_DRIVE_TIME = 2.0;  // seconds
    public static final double AUTO_TURN_TIME = 1.0;   // seconds
    
    // ========== SENSOR THRESHOLDS ==========
    
    public static final double DISTANCE_THRESHOLD = 10.0;  // cm
    
}
