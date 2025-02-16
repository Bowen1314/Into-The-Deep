//************************************************************
//|  9121 Falcon Force 2024-2025 In To The Deep TeleOp Code  |
//************************************************************

//    ###     #     ###     #
//   #   #   ##    #   #   ##
//   #  ##  # #        #  # #
//    ## #    #      ##     #
//       #    #     #       #
//      #     #    #        #
//    ##    #####  #####  #####

package org.firstinspires.ftc.teamcode;

//import section
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "PID - Driving System", group = "AAA-First")
public class Headless_PID extends OpMode {
    // Declare all usage
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor RD;
    private DcMotor LD;
    private Servo leftclaw;
    private Servo rightclaw;
    private Servo leftslide;
    private Servo rightsilde;
    private Servo claw;
    private Servo holder;
    private Servo leftholder;
    private Servo rightholder;
    private Servo spin;
    private IMU imu;
    private DcMotor leftLevel;
    private DcMotor rightLevel;

    private double mutispeed = .6;
    boolean clawOpen = true;
    boolean aPressed = false;
    boolean bPressed = false;
    boolean dpad_leftPressed = false;
    boolean dpad_upPressed = false;
    private float currentHeading;
    private float headingOffset = 0;

    // PID control variables
    private double kp = 1; // Proportional
    private double ki = 0.01; // Integral
    private double kd = 0.05; // Derivative
    private double previousErrorRD = 0;
    private double previousErrorLD = 0;
    private double integralRD = 0;
    private double integralLD = 0;

    @Override
    public void init() {
        // Initialize wheels
        frontLeft = hardwareMap.get(DcMotor.class, "LF");
        frontRight = hardwareMap.get(DcMotor.class, "RF");
        backLeft = hardwareMap.get(DcMotor.class, "LB");
        backRight = hardwareMap.get(DcMotor.class, "RB");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Set brake behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // RD and LD initialization
        RD = hardwareMap.get(DcMotor.class, "RD");
        LD = hardwareMap.get(DcMotor.class, "LD");
        RD.setDirection(DcMotorSimple.Direction.REVERSE);
        RD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RD.setTargetPosition(0);
        LD.setTargetPosition(0);
        RD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize servos
        claw = hardwareMap.get(Servo.class, "claw");
        leftclaw = hardwareMap.get(Servo.class, "leftclaw");
        rightclaw = hardwareMap.get(Servo.class, "rightclaw");
        rightsilde = hardwareMap.get(Servo.class, "rightslide");
        leftslide = hardwareMap.get(Servo.class, "leftslide");
        holder = hardwareMap.get(Servo.class, "holder");
        leftholder = hardwareMap.get(Servo.class, "leftholder");
        rightholder = hardwareMap.get(Servo.class, "rightholder");
        spin = hardwareMap.get(Servo.class, "spin");

        // Initialize level motors
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        rightLevel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Initialize IMU
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        )));

        // Default positions
        rightholder.setPosition(0);
        leftholder.setPosition(1);
        leftclaw.setPosition(0);
        rightclaw.setPosition(1);
        holder.setPosition(0.6);
        spin.setPosition(1);
    }

    @Override
    public void loop() {
        // Mecanum drive with headless mode
        currentHeading = (float) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - headingOffset;
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;
        double rotatedX = x * Math.cos(currentHeading) + y * Math.sin(currentHeading);
        double rotatedY = -x * Math.sin(currentHeading) + y * Math.cos(currentHeading);
        double frontLeftPower = rotatedY + rotatedX + rx;
        double frontRightPower = rotatedY - rotatedX - rx;
        double backLeftPower = rotatedY - rotatedX + rx;
        double backRightPower = rotatedY + rotatedX - rx;
        frontLeft.setPower(mutispeed * Range.clip(frontLeftPower, -1.0, 1.0));
        frontRight.setPower(mutispeed * Range.clip(frontRightPower, -1.0, 1.0));
        backLeft.setPower(mutispeed * Range.clip(backLeftPower, -1.0, 1.0));
        backRight.setPower(mutispeed * Range.clip(backRightPower, -1.0, 1.0));

        // PID control for RD and LD
        int currentRDPosition = RD.getCurrentPosition();
        double errorRD = RD.getTargetPosition() - currentRDPosition;
        integralRD += errorRD;
        double derivativeRD = errorRD - previousErrorRD;
        double powerRD = kp * errorRD + ki * integralRD + kd * derivativeRD;
        previousErrorRD = errorRD;
        RD.setPower(Range.clip(powerRD, -1.0, 1.0));

        int currentLDPosition = LD.getCurrentPosition();
        double errorLD = LD.getTargetPosition() - currentLDPosition;
        integralLD += errorLD;
        double derivativeLD = errorLD - previousErrorLD;
        double powerLD = kp * errorLD + ki * integralLD + kd * derivativeLD;
        previousErrorLD = errorLD;
        LD.setPower(Range.clip(powerLD, -1.0, 1.0));

        // Telemetry for debugging
        telemetry.addData("Heading (deg)", Math.toDegrees(currentHeading));
        telemetry.addData("RD Target", RD.getTargetPosition());
        telemetry.addData("RD Current", currentRDPosition);
        telemetry.addData("LD Target", LD.getTargetPosition());
        telemetry.addData("LD Current", currentLDPosition);
        telemetry.update();
    }
}
