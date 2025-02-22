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

    private Servo claw_mouse;



    @Override
    public void init() {


        //slide
        claw_mouse = hardwareMap.get(Servo.class, "claw_mouse");
        //holder sys
        // Initialize motors



        // Initialize IMU sensor

    }

    @Override
    public void loop() {
        // Reset heading when 'Y' button is pressed


        // Clip motor powers to be in range [-1.0, 1.0]

        if (gamepad1.x) {
            claw_mouse.setPosition(1);



        }

        if (gamepad1.y) {
            claw_mouse.setPosition(.5);
        }

        if (gamepad1.a) {
                claw_mouse.setPosition(0);

        }

    }
}