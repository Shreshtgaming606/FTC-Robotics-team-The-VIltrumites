# FTC Java Setup Checklist

## Before You Start Coding

- [ ] **Install Android Studio** (or use IntelliJ IDEA)
- [ ] **Install FTC SDK** - Follow official FTC setup guide
- [ ] **Connect to FTC Control Hub** - Ensure your development machine can reach the robot
- [ ] **Create hardware configuration** in FTC Robot Controller app on your robot

## Project Setup

- [ ] **Clone or create repo** with proper structure
- [ ] **Create directory structure**: `src/main/java/org/firstinspires/ftc/teamcode/`
- [ ] **Create `HardwareConfig.java`** - Hardware initialization
- [ ] **Create `RobotConstants.java`** - Centralized configuration
- [ ] **Create OpMode classes** - TeleOp and Autonomous

## Hardware Configuration (Critical!)

In your FTC Robot Controller app, define these devices:

```
Device Name          Type              Port
left_front          DcMotor           0
left_back           DcMotor           1
right_front         DcMotor           2
right_back          DcMotor           3
intake_servo        Servo             0
```

**Important**: The device names you enter in the FTC app MUST match the names in your `HardwareConfig.java`

```java
leftFrontMotor = hwMap.get(DcMotor.class, "left_front");
```

## Code Checklist

- [ ] **Check device names** - Match FTC app configuration exactly
- [ ] **Set motor directions** - Adjust REVERSE/FORWARD for each motor
- [ ] **Test motor directions** - Run BasicTeleOp and verify all motors spin correctly
- [ ] **Add telemetry** - Display debug info to verify hardware works
- [ ] **Test each component** separately before combining
- [ ] **Handle null values** - Add null checks for hardware devices

```java
if (leftFrontMotor == null) {
    telemetry.addData("Error", "left_front motor not found");
    return;
}
```

## Compilation Checklist

- [ ] **Check imports** - Ensure all `import` statements are present
- [ ] **Check package names** - Must be `org.firstinspires.ftc.teamcode`
- [ ] **Verify OpMode annotations** - `@TeleOp` or `@Autonomous` required
- [ ] **Check method signatures** - Override `runOpMode()` correctly
- [ ] **Build/compile project** - Ensure no syntax errors

## Deployment Checklist

- [ ] **Connect to FTC Control Hub** via USB
- [ ] **Build and deploy** APK to control hub
- [ ] **Select correct hardware configuration** on driver station
- [ ] **Select your OpMode** from available list
- [ ] **Press INIT button** - Watch for initialization errors in telemetry
- [ ] **Press PLAY button** - Start running

## Testing Checklist

- [ ] **Verify telemetry displays** - Should see debug information
- [ ] **Test driving** - Each motor should respond correctly
- [ ] **Test servos** - Move to expected positions
- [ ] **Test sensors** (if used) - Read correct values
- [ ] **Monitor temperature** - Watch for overheating
- [ ] **Test emergency stop** - Stopping should work immediately

## Common Issues & Solutions

### "Device Not Found" Error
**Problem**: Motor/servo not initializing  
**Solution**: 
1. Check device name in HardwareConfig matches FTC app exactly (case-sensitive)
2. Verify device is configured in FTC Robot Controller app
3. Restart Control Hub and try again

### Motor Runs Backwards
**Problem**: Motor spins opposite direction  
**Solution**: 
```java
motor.setDirection(DcMotor.Direction.REVERSE);
```

### Code Won't Compile
**Problem**: Compilation errors  
**Solution**:
1. Check imports at top of file
2. Verify package name is correct
3. Check for typos in class/method names
4. Ensure all classes have proper structure

### Telemetry Not Showing
**Problem**: Driver station shows no debug info  
**Solution**:
1. Call `telemetry.update()` at end of loop
2. Check OpMode is actually running (not stopped)
3. Verify telemetry data is being added

### Robot Not Responding to Controller
**Problem**: Gamepad input not working  
**Solution**:
1. Check gamepad is connected to driver station
2. Verify you're reading correct gamepad (gamepad1 vs gamepad2)
3. Add telemetry to display gamepad values
4. Restart driver station app

### Control Hub Connection Issues
**Problem**: Can't connect to robot  
**Solution**:
1. Check USB cable is securely connected
2. Ensure Control Hub is powered on
3. Check ADB drivers installed on computer
4. Try restarting Control Hub

## Performance Optimization

- [ ] **Remove unnecessary telemetry** in final code (slows down loop)
- [ ] **Use appropriate loop timing** - Don't run faster than needed
- [ ] **Batch hardware updates** - Don't redundantly set motor power
- [ ] **Use ElapsedTime for timing** instead of System.currentTimeMillis()
- [ ] **Limit telemetry updates** - Don't call update() multiple times per loop

## Documentation

- [ ] **Add comments** explaining complex code sections
- [ ] **Document method parameters** using JavaDoc style
- [ ] **Explain subsystem classes** with clear comments
- [ ] **Create README** with team-specific setup instructions

## Ready to Deploy!

Once you've completed all checkboxes:

1. Push code to GitHub
2. Deploy to Control Hub
3. Test on your robot
4. Document any issues or custom behaviors
5. Have fun competing! 🤖

