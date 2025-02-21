package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name ="state servo", group = "TeleOp")
public class state_servo extends OpMode {
    Pose2d beginPose = new Pose2d(0, 0,Math.toRadians(0));

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor LD;
    private DcMotor RD;
    private Servo leftclaw;
    private Servo rightclaw;
    private Servo leftslide;
    private Servo rightsilde;
    private Servo claw;
    private Servo holder;
    private Servo leftholder;
    private Servo rightholder;
    private Servo spin;
    private Servo angle;



    @Override
    public void init() {
        claw = hardwareMap.get(Servo.class, "claw");
        leftclaw = hardwareMap.get(Servo.class, "leftclaw");
        rightclaw = hardwareMap.get(Servo.class, "rightclaw");

        //slide
        rightsilde = hardwareMap.get(Servo.class, "rightslide");
        leftslide = hardwareMap.get(Servo.class, "leftslide");

        //holder system
        holder = hardwareMap.get(Servo.class,"holder");
        leftholder = hardwareMap.get(Servo.class,"leftholder");
        rightholder = hardwareMap.get(Servo.class,"rightholder");
        spin = hardwareMap.get(Servo.class,"spin");
        angle = hardwareMap.get(Servo.class,"angle");
        // Initialize motors



        // Initialize IMU sensor

    }

    @Override
    public void loop() {
        // Reset heading when 'Y' button is pressed


        // Clip motor powers to be in range [-1.0, 1.0]

        if (gamepad1.x) {
            leftslide.setPosition(1);
            rightsilde.setPosition(0);


        }

        if (gamepad1.y) {
            leftslide.setPosition(.5);
            rightsilde.setPosition(.5);

        }

        if (gamepad1.a) {
            leftholder.setPosition(0.2);
            rightholder.setPosition(0.8);
        }

        if (gamepad1.b) {
            //leftholder.setPosition(1);
            //rightholder.setPosition(0);
            angle.setPosition(0);
            //spin.setPosition(.7);
        }

    }
}