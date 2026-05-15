# FTC Robot Code - Quick Start Guide

## What You Have

Your project now includes:

### 📁 Directory Structure
```
src/main/java/org/firstinspires/ftc/teamcode/
├── HardwareConfig.java       - Hardware initialization and control
├── RobotConstants.java       - All configuration constants
└── opmodes/
    ├── BasicTeleOp.java      - Manual control template
    └── BasicAuto.java        - Autonomous template
```

### 📄 What Each File Does

1. **HardwareConfig.java**
   - Initializes all robot hardware (motors, servos, sensors)
   - Provides methods to control motors (`tankDrive()`, `arcadeDrive()`)
   - Maps device names to logical variables

2. **BasicTeleOp.java**
   - Manual robot control using gamepads
   - Implements driving with analog sticks
   - Shows how to handle button inputs

3. **BasicAuto.java**
   - Robot executes predetermined movements
   - Includes helper methods for driving and turning
   - Uses timers for controlled movements

4. **RobotConstants.java**
   - Centralized configuration (device names, speeds, positions)
   - Change values here instead of in multiple files

## Getting Started

### Step 1: Configure Hardware Names
In **HardwareConfig.java**, update the device names to match your FTC Robot Controller app:

```java
leftFrontMotor = hwMap.get(DcMotor.class, "YOUR_DEVICE_NAME");
```

The names must **exactly match** what you named them in the FTC app on your robot!

### Step 2: Set Motor Directions
Adjust these in **HardwareConfig.java** if motors run backwards:

```java
leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
```

Test by running `BasicTeleOp` and see if motors spin the right direction.

### Step 3: Write Your First TeleOp
Edit **BasicTeleOp.java** to add your robot's controls:

```java
// Inside the while loop
if (gamepad1.a) {
    robot.intakeServo.setPosition(0.0);
}
```

### Step 4: Test & Debug
Deploy to your robot:
1. Connect to FTC app on your driver station
2. Select your OpMode
3. Press INIT → PLAY
4. Watch telemetry output for debug info

## Common Tasks

### Read Gamepad Input
```java
// Analog sticks (-1.0 to 1.0)
double stickValue = gamepad1.left_stick_y;

// Buttons (true/false)
if (gamepad1.a) { }
if (gamepad1.x) { }

// Triggers (0.0 to 1.0)
float trigger = gamepad1.left_trigger;
```

### Control a Motor
```java
robot.leftFrontMotor.setPower(0.5);  // Half speed forward
robot.leftFrontMotor.setPower(-0.5); // Half speed backward
robot.leftFrontMotor.setPower(0);    // Stop
```

### Control a Servo
```java
robot.intakeServo.setPosition(0.0);   // Full left
robot.intakeServo.setPosition(0.5);   // Center
robot.intakeServo.setPosition(1.0);   // Full right
```

### Display Info (Telemetry)
```java
telemetry.addData("Motor Power", robot.leftFrontMotor.getPower());
telemetry.addData("Status", "Running");
telemetry.update();  // Must call update() to send data!
```

### Drive with Encoders (Precise Movement)
```java
robot.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
robot.leftFrontMotor.setTargetPosition(500);  // Ticks
robot.leftFrontMotor.setPower(0.5);
while (robot.leftFrontMotor.isBusy() && opModeIsActive()) {
    // Wait for motor to reach position
}
```

## Debugging Tips

1. **Check telemetry output** - Add `telemetry.addData()` to display values
2. **Test each motor separately** - Make sure they spin the right direction
3. **Check device names** - Verify names match the FTC Robot Controller app exactly
4. **Use sleep()** for timing - Insert delays between movements

## OpMode Annotations

```java
@TeleOp(name = "Name", group = "Group")  // Manual control
@Autonomous(name = "Name", group = "Group")  // Autonomous
```

## File Organization Best Practices

As your code grows, organize it:

```
teamcode/
├── HardwareConfig.java
├── RobotConstants.java
├── subsystems/
│   ├── DriveSubsystem.java
│   ├── IntakeSubsystem.java
│   └── ArmSubsystem.java
└── opmodes/
    ├── teleop/
    │   ├── BasicTeleOp.java
    │   └── AdvancedTeleOp.java
    └── autonomous/
        ├── BasicAuto.java
        └── AdvancedAuto.java
```

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Motor won't spin | Check device name in HardwareConfig matches FTC app |
| Motor spins wrong way | Reverse direction with `DcMotor.Direction.REVERSE` |
| Servo doesn't move | Check servo position is 0.0-1.0 range |
| Code won't compile | Check imports at top of file |
| Telemetry not showing | Remember to call `telemetry.update()` |

## Next Steps

1. **Add more subsystems** - Create separate classes for arm, intake, etc.
2. **Implement sensors** - Add distance sensors, color sensors, IMU
3. **Create subsystem classes** - Organize code into logical components
4. **Add autonomous paths** - Implement complex movement sequences

Good luck with your FTC season! 🤖

