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

package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.angle_system;
import org.firstinspires.ftc.teamcode.subsystem.back_arm_system;
import org.firstinspires.ftc.teamcode.subsystem.front_claw_system;
import org.firstinspires.ftc.teamcode.subsystem.holder_system;
import org.firstinspires.ftc.teamcode.subsystem.level_system;
import org.firstinspires.ftc.teamcode.subsystem.spin_system;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "AAAAA State New", group = "AAAAAAAAA-First")
public class state_new extends OpMode {
    // 定义所有硬件
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo leftslide;
    private Servo rightsilde;
    private Servo claw;
    private Servo claw_mouse;
    private Servo claw_spin;
    private Servo holder;
    private Servo leftholder;
    private Servo rightholder;
    private Servo spin;
    private Servo angle;
    private IMU imu;
    private DcMotor leftLevel;
    private DcMotor rightLevel;
    private double mutispeed = 0.7;
    private ElapsedTime slideTimer = new ElapsedTime();
    private boolean slideDelayStarted = false;
    boolean slide_extend = false;
    boolean claw_opened555 = false;

    boolean claw_down = false;
    boolean a_pressed = false;
    boolean b_pressed = false;

    // 控制 claw 只设置一次的位置操作变量
    private boolean clawTriggered = false;

    private float currentHeading;
    private float headingOffset = 0;

    @Override
    public void init() {
        Pose2d beginPose = new Pose2d(14, -62, Math.toRadians(90));

        // 初始化轮子
        frontLeft = hardwareMap.get(DcMotor.class, "LF");
        frontRight = hardwareMap.get(DcMotor.class, "RF");
        backLeft = hardwareMap.get(DcMotor.class, "LB");
        backRight = hardwareMap.get(DcMotor.class, "RB");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 初始化 claw 系统
        claw = hardwareMap.get(Servo.class, "claw");
        claw_mouse = hardwareMap.get(Servo.class, "claw_mouse");
        claw_spin = hardwareMap.get(Servo.class, "claw_spin");

        // 初始化 slide 系统（左右滑轨）
        rightsilde = hardwareMap.get(Servo.class, "rightslide");
        leftslide = hardwareMap.get(Servo.class, "leftslide");

        // 初始化 holder 系统
        holder = hardwareMap.get(Servo.class, "holder");
        leftholder = hardwareMap.get(Servo.class, "leftholder");
        rightholder = hardwareMap.get(Servo.class, "rightholder");
        spin = hardwareMap.get(Servo.class, "spin");
        angle = hardwareMap.get(Servo.class, "angle");

        // 初始化 level 系统
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        leftLevel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 初始化陀螺仪
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        // Mecanum 驱动，无头模式
        if (gamepad1.y) {
            headingOffset = (float) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        }
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

        telemetry.addData("Heading (deg) *Player 1 press Y to reset*", Math.toDegrees(currentHeading));
        telemetry.addData("Front Left Power", frontLeftPower);
        telemetry.addData("Front Right Power", frontRightPower);
        telemetry.addData("Back Left Power", backLeftPower);
        telemetry.addData("Back Right Power", backRightPower);
        telemetry.addData("Left Level", leftLevel.getCurrentPosition());
        telemetry.addData("Right Level", rightLevel.getCurrentPosition());
        telemetry.update();

        // 控制 claw 的位置：只在触发器第一次按下时设置一次位置
        if (gamepad1.right_trigger > 0.1 && !clawTriggered) {
            claw.setPosition(.5);
            clawTriggered = true;
        } else if (gamepad1.right_trigger <= 0.1) {
            claw.setPosition(1);
            clawTriggered = false;
            a_pressed = false;
        }



        // slide 控制：每次循环都会根据右侧触发器的状态设置左右滑轨的位置
        if (gamepad1.right_trigger > 0.1) {
            leftslide.setPosition(0.8);
            rightsilde.setPosition(0.2);
        } else {
            leftslide.setPosition(1);
            rightsilde.setPosition(0);
        }


        if (gamepad1.a && !a_pressed) {
            claw_down = !claw_down;
            a_pressed = true;
            if (claw_down) {
                claw.setPosition(.35);
                claw_mouse.setPosition(0);

            } else {
                claw.setPosition(.5);

            }
        }
        if (!gamepad1.a) {
            a_pressed = false;
        }

        if (gamepad1.b && !b_pressed) {
            claw_opened555 = !claw_opened555;
            b_pressed = true;
            if (claw_opened555) {
                claw_mouse.setPosition(0);

            } else {
                claw_mouse.setPosition(6);

            }
        }
        if (!gamepad1.b) {
            b_pressed = false;
        }
    }
}
