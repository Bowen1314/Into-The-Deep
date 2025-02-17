package org.firstinspires.ftc.teamcode.state_auto;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.subsystem.front_claw_system;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.level_system;
import org.firstinspires.ftc.teamcode.subsystem.back_arm_system;
import org.firstinspires.ftc.teamcode.subsystem.holder_system;

@Autonomous(name = "AAA - State Right", group = "A State RR")
public final class Right extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // 初始位姿
        Pose2d beginPose = new Pose2d(0, 0,0);


        // 初始化 MecanumDrive
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        Pose2d currentPose = new Pose2d(drive.pose.position.x, drive.pose.position.y, drive.pose.heading.toDouble());


        Pose2d initialPose = new Pose2d(14, -62, Math.toRadians(90));
        Pose2d preloadSamplePose = new Pose2d(-5,-32, Math.toRadians(90));
        Pose2d thirdPose = new Pose2d(65.00, -55, Math.toRadians(90));
        Pose2d humanPlayerPose = new Pose2d(37,60, Math.toRadians(90));
        Pose2d secondSamplePose = new Pose2d(-2,-32, Math.toRadians(90));
        Pose2d thirdSamplePose = new Pose2d(1,-32, Math.toRadians(90));
        Pose2d fourthSamplePose = new Pose2d(4,-32, Math.toRadians(90));
        Pose2d fifthSamplePose = new Pose2d(7,-32, Math.toRadians(90));




        waitForStart();
        front_claw_system front_claw = new front_claw_system(
                hardwareMap.get(Servo.class, "leftclaw"),
                hardwareMap.get(Servo.class, "rightclaw")
        );

        level_system level = new level_system(
                hardwareMap.get(DcMotor.class, "leftLevel"),
                hardwareMap.get(DcMotor.class, "rightLevel")
        );
        back_arm_system back_arm = new back_arm_system(
                hardwareMap.get(Servo.class, "leftholder"),
                hardwareMap.get(Servo.class, "rightholder")
        );
        holder_system holder = new holder_system(
                hardwareMap.get(Servo.class, "holder")
        );



        TrajectoryActionBuilder wait_sec = drive.actionBuilder(currentPose)
            .waitSeconds(0.2);


        TrajectoryActionBuilder preload = drive.actionBuilder(initialPose)
            .strafeTo(new Vector2d(-5,-32));

        TrajectoryActionBuilder push = drive.actionBuilder(preloadSamplePose)
            .strafeTo(new Vector2d(30,-37))
            .splineToConstantHeading(new Vector2d(35, -11), Math.toRadians(90.00))
            .splineToConstantHeading(new Vector2d(48, -11), Math.toRadians(270.00))
            .splineToConstantHeading(new Vector2d(48.00, -55), Math.toRadians(90.00))
            .splineToConstantHeading(new Vector2d(48.00, -11.00), Math.toRadians(90))
            .splineToConstantHeading(new Vector2d(60.00, -11.00), Math.toRadians(270.00))
            .splineToConstantHeading(new Vector2d(60.00, -55.00), Math.toRadians(90.00))
            .splineToConstantHeading(new Vector2d(60.00, -11.00), Math.toRadians(90.00))
            .splineToConstantHeading(new Vector2d(65.00, -11.00), Math.toRadians(270.00))
            .splineToConstantHeading(new Vector2d(65.00, -55), Math.toRadians(270.00));

        TrajectoryActionBuilder human = drive.actionBuilder(thirdPose)
            .splineToConstantHeading(new Vector2d(37,-60), Math.toRadians(270.00));

        TrajectoryActionBuilder Second_Sample = drive.actionBuilder(humanPlayerPose)
            .strafeTo(new Vector2d(-2,-32));

        TrajectoryActionBuilder tohuman = drive.actionBuilder(currentPose)
            .strafeTo(new Vector2d(37,-60));

        TrajectoryActionBuilder Third_Sample = drive.actionBuilder(humanPlayerPose)
            .strafeTo(new Vector2d(1,-32));

        TrajectoryActionBuilder fourth_sample = drive.actionBuilder(humanPlayerPose)
            .strafeTo(new Vector2d(4,-32));

        TrajectoryActionBuilder fifth_sample = drive.actionBuilder(humanPlayerPose)
            .strafeTo(new Vector2d(7,-32));










        holder.close();
        level.chamber_high();
        back_arm.back();

        Actions.runBlocking(
            new SequentialAction(
                preload.build()
            )
        );

        level.clip();

        Actions.runBlocking(
            new SequentialAction(
                wait_sec.build()
            )
        );
        holder.open();
        level.origin();

        Actions.runBlocking(
            new SequentialAction(
                push.build()
            )
        );
        back_arm.back();
        holder.open();


        Actions.runBlocking(
            new SequentialAction(
                human.build()
            )
        );

        holder.close();
        level.chamber_high();
        back_arm.middle();

        Actions.runBlocking(
            new SequentialAction(
                Second_Sample.build()
            )
        );

        level.clip();
        holder.open();










    }
}
