# FTC Robot Code Setup Guide - Java

## Overview
FTC (First Tech Challenge) robot code uses the FTC SDK which is built on top of Android development. Your robot is controlled through **OpMode** classes - Java classes that contain the logic for robot operations.

## Project Structure

```
FTC-Robotics-team-The-VIltrumites/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── firstinspires/
│   │               └── ftc/
│   │                   └── teamcode/
│   │                       ├── BasicOpMode.java
│   │                       ├── AutonomousOpMode.java
│   │                       ├── TeleOpMode.java
│   │                       └── (your team code)
│   └── test/
├── build.gradle
├── settings.gradle
└── README.md
```

## Key Components

### 1. **OpMode** - The main class for robot control
   - **TeleOp** - Manual control mode (driver controls)
   - **Autonomous** - Robot runs predefined instructions

### 2. **Hardware Configuration**
   - Maps hardware devices (motors, servos, sensors)
   - Must match your robot's physical setup

### 3. **Hardware Classes**
   - `DcMotor` - DC Motors
   - `Servo` - Servo motors
   - `Sensor` - Various sensors
   - `Gamepad` - Controller input

## Setup Steps

### Step 1: Create Directory Structure
```
src/main/java/org/firstinspires/ftc/teamcode/
```

### Step 2: Create Your First OpMode
Every OpMode has this basic structure:

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="My First OpMode", group="Linear Opmode")
public class MyFirstOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        waitForStart();
        
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
```

### Step 3: Annotations (@)
- `@TeleOp` - Manual control OpMode
- `@Autonomous` - Autonomous OpMode
- `name` - Name displayed on driver station
- `group` - Organization label

### Step 4: Key Methods
- `runOpMode()` - Main method, called when OpMode starts
- `waitForStart()` - Waits for driver to hit START
- `opModeIsActive()` - Loop continues while true
- `telemetry.addData()` - Display info on driver station
- `telemetry.update()` - Send data to driver station

## Common Hardware Setup Pattern

```java
// Declare hardware
private DcMotor leftMotor;
private DcMotor rightMotor;
private Servo clawServo;

@Override
public void runOpMode() {
    // Initialize hardware
    leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
    rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
    clawServo = hardwareMap.get(Servo.class, "claw");
    
    // Set direction
    leftMotor.setDirection(DcMotor.Direction.FORWARD);
    rightMotor.setDirection(DcMotor.Direction.REVERSE);
    
    telemetry.addData("Status", "Initialized");
    telemetry.update();
    
    waitForStart();
    
    while (opModeIsActive()) {
        // Your robot code here
    }
}
```

## Gamepad Input (TeleOp)

```java
while (opModeIsActive()) {
    // Buttons
    if (gamepad1.a) { /* action */ }
    if (gamepad1.b) { /* action */ }
    if (gamepad1.x) { /* action */ }
    if (gamepad1.y) { /* action */ }
    
    // Triggers (0.0 to 1.0)
    float leftTrigger = gamepad1.left_trigger;
    float rightTrigger = gamepad1.right_trigger;
    
    // Analog sticks (-1.0 to 1.0)
    float leftStickX = gamepad1.left_stick_x;
    float leftStickY = gamepad1.left_stick_y;
    float rightStickX = gamepad1.right_stick_x;
    float rightStickY = gamepad1.right_stick_y;
}
```

## Telemetry (Debugging)

```java
// Display debug info on driver station
telemetry.addData("Motor Power", leftMotor.getPower());
telemetry.addData("Sensor Value", distanceSensor.getDistance(DistanceUnit.CM));
telemetry.addData("Status", "Running");
telemetry.update();
```

## Best Practices

1. **Organize your code** - Create separate classes for hardware subsystems
2. **Use meaningful names** - Variables should clearly describe their purpose
3. **Comment your code** - Explain complex logic
4. **Test incrementally** - Test each component before integrating
5. **Use constants** - Define motor/servo names and power levels as constants
6. **Error handling** - Check for null hardware devices

## Next Steps

1. Create your hardware configuration (usually done in the FTC app on the robot)
2. Create your first TeleOp OpMode
3. Create an Autonomous OpMode
4. Test on your robot!

## Resources

- FTC SDK Documentation: https://ftcfirststechdev.org/
- Sample Code: Check the FTC SDK GitHub repository
- Driver Station App: Install on Android device for control

