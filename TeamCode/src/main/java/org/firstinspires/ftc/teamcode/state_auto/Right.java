package org.firstinspires.ftc.teamcode.state_auto;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.subsystem.front_claw_system;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.level_system;

@Autonomous(name = "AAA - State Right", group = "A State RR")
public final class Right extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // 初始位姿
        Pose2d beginPose = new Pose2d(14, 62,0);

        // 初始化 MecanumDrive
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);



        waitForStart();
        front_claw_system front_claw = new front_claw_system(
                hardwareMap.get(Servo.class, "leftclaw"),
                hardwareMap.get(Servo.class, "rightclaw")
        );

        level_system level = new level_system(
                hardwareMap.get(DcMotor.class, "leftLevel"),
                hardwareMap.get(DcMotor.class, "rightLevel")
        );

        TrajectoryActionBuilder preload = drive.actionBuilder(beginPose)
            .splineToSplineHeading(new Pose2d(-35,32, Math.toRadians(45)), Math.toRadians(-90));
            level.chamber_high();



        Actions.runBlocking(
                new SequentialAction(
                        preload.build()
                )
        );
    }



    public class ClawAction implements Action{
        Servo leftclaw;
        Servo rightclaw;
        double position_L;
        double position_R;

        public ClawAction(Servo s1,Servo s2,double p_L,double p_R){
            this.leftclaw = s1;
            this.rightclaw = s2;
            this.position_L = p_L;
            this.position_R = p_R;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            leftclaw.setPosition(position_L);
            rightclaw.setPosition(position_R);
            return false;
        }
    }

    public class MotorAction implements Action{
        DcMotor leftLevel;
        DcMotor rightLevel;
        double position;

        public MotorAction(DcMotor s1,DcMotor s2,double p){
            this.leftLevel = s1;
            this.rightLevel = s2;
            this.position = p;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            leftLevel.setTargetPosition((int) position);
            rightLevel.setTargetPosition((int) position);
            leftLevel.setPower(1.0);
            rightLevel.setPower(1.0);
            return false;
        }
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


    public class HolderAction implements Action{
        Servo holder;
        double position;

        public HolderAction(Servo s1,double p){
            this.holder = s1;
            this.position = p;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            holder.setPosition((int) position);
            return false;
        }
    }

    public class SpinAction implements Action{
        Servo spin;
        double position;

        public SpinAction(Servo s1,double p){
            this.spin = s1;
            this.position = p;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            spin.setPosition((int) position);
            return false;
        }
    }


}
