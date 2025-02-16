package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="RR_Test")
public class RR_Test extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-43.08, 41.32, 0))
                            .splineTo(new Vector2d(-43.08, 41.32), Math.toRadians(19.65))
                            .splineTo(new Vector2d(-10.94, 48.81), Math.toRadians(0.00))
                            .splineTo(new Vector2d(50.28, 31.19), Math.toRadians(-82.87))
                            .splineTo(new Vector2d(51.30, -14.75), Math.toRadians(-82.57))
                            .waitSeconds(1000000)
                            .build());
        }


    }
}