package org.firstinspires.ftc.teamcode.tools;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.auto.RR_Left;
import org.firstinspires.ftc.teamcode.subsystem.angle_system;
import org.firstinspires.ftc.teamcode.subsystem.front_claw_system;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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
import org.firstinspires.ftc.teamcode.subsystem.spin_system;

@Autonomous(name = "Auto_tune", group = "A State RR")
public final class Auto_tune extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // 初始位姿
        Pose2d beginPose = new Pose2d(14, -62,Math.toRadians(90));


        // 初始化 MecanumDrive
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        Pose2d currentPose = new Pose2d(drive.pose.position.x, drive.pose.position.y, drive.pose.heading.toDouble());


        Pose2d initialPose = new Pose2d(14, -62, Math.toRadians(90));
        Pose2d preloadSamplePose = new Pose2d(-5,-32, Math.toRadians(90));
        Pose2d thirdPose = new Pose2d(65.00, -55, Math.toRadians(90));
        Pose2d humanPlayerPose = new Pose2d(37,-60, Math.toRadians(90));
        Pose2d secondSamplePose = new Pose2d(-2,-32, Math.toRadians(90));
        Pose2d thirdSamplePose = new Pose2d(1,-32, Math.toRadians(90));
        Pose2d fourthSamplePose = new Pose2d(4,-32, Math.toRadians(90));
        Pose2d fifthSamplePose = new Pose2d(7,-32, Math.toRadians(90));
        front_claw_system front_claw = new front_claw_system(
                hardwareMap.get(Servo.class, "leftclaw"),
                hardwareMap.get(Servo.class, "rightclaw")
        );

        level_system level = new level_system(
                hardwareMap.get(DcMotor.class, "leftlevel"),
                hardwareMap.get(DcMotor.class, "rightlevel")
        );
        back_arm_system back_arm = new back_arm_system(
                hardwareMap.get(Servo.class, "leftholder"),
                hardwareMap.get(Servo.class, "rightholder")
        );
        holder_system holder = new holder_system(
                hardwareMap.get(Servo.class, "holder")
        );
        angle_system angle = new angle_system(
                hardwareMap.get(Servo.class, "angle")
        );

        spin_system spin = new spin_system(
                hardwareMap.get(Servo.class, "spin")
        );

        TrajectoryActionBuilder wait_sec = drive.actionBuilder(currentPose)
                .waitSeconds(1);

        TrajectoryActionBuilder wait_sec_5 = drive.actionBuilder(currentPose)
                .waitSeconds(.5);

        TrajectoryActionBuilder wait_sec_2 = drive.actionBuilder(currentPose)
                .waitSeconds(.2);


        TrajectoryActionBuilder preload = drive.actionBuilder(initialPose)
                //.afterTime(0,new RR_Left.ArmAction(leftholder,rightholder,1,0))
                .strafeTo(new Vector2d(-6,-25));

        TrajectoryActionBuilder push = drive.actionBuilder(preloadSamplePose)
                .strafeTo(new Vector2d(31,-37))
                .splineToConstantHeading(new Vector2d(33, -10), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(45, -10), Math.toRadians(270.00))
                .splineToConstantHeading(new Vector2d(45.00, -58), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(45.00, -10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(58.00, -10), Math.toRadians(270.00))
                .splineToConstantHeading(new Vector2d(58.00, -58), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(58.00, -4), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(65.00, -6), Math.toRadians(270.00))
                .splineToConstantHeading(new Vector2d(65.00, -58), Math.toRadians(270.00));

        TrajectoryActionBuilder human = drive.actionBuilder(thirdPose)
                .splineToConstantHeading(new Vector2d(38,-67), Math.toRadians(270.00));

        TrajectoryActionBuilder Second_Sample = drive.actionBuilder(humanPlayerPose)
                .strafeTo(new Vector2d(-5,-30));

        TrajectoryActionBuilder tohuman = drive.actionBuilder(currentPose)
                .strafeTo(new Vector2d(38,-67));

        TrajectoryActionBuilder Third_Sample = drive.actionBuilder(humanPlayerPose)
                .strafeTo(new Vector2d(-2,-30));

        TrajectoryActionBuilder fourth_sample = drive.actionBuilder(humanPlayerPose)
                .strafeTo(new Vector2d(1,-30));

        TrajectoryActionBuilder fifth_sample = drive.actionBuilder(humanPlayerPose)
                .strafeTo(new Vector2d(4,-30));





        angle.grab();

        waitForStart();

    }

    public class ArmAction implements Action{
        Servo leftholder;
        Servo rightholder;
        double position_L;
        double position_R;

        public ArmAction(Servo s1,Servo s2,double p_L,double p_R){
            this.leftholder = s1;
            this.rightholder = s2;
            this.position_L = p_L;
            this.position_R = p_R;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            leftholder.setPosition((int) position_L);
            rightholder.setPosition((int) position_R);

            //rightholder.setPosition(1);
            //leftholder.setPosition(0);
            return false;
        }
    }
}
