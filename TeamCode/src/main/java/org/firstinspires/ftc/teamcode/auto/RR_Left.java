package org.firstinspires.ftc.teamcode.auto;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.tools.timer;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name = "AAA - LiftSide Auto", group = "A-RR")
public final class RR_Left extends LinearOpMode {
    private static timer timer;

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
    private IMU imu;
    private DcMotor leftLevel;
    private DcMotor rightLevel;
    @Override
    public void runOpMode() throws InterruptedException {
        // 初始位姿
        Pose2d beginPose = new Pose2d(14, 62,0);

        // 初始化 MecanumDrive
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        // 初始化电机
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        rightLevel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        claw = hardwareMap.get(Servo.class, "claw");
        leftclaw = hardwareMap.get(Servo.class, "leftclaw");
        rightclaw = hardwareMap.get(Servo.class, "rightclaw");
        spin = hardwareMap.get(Servo.class,"spin");

        //slide
        rightsilde = hardwareMap.get(Servo.class, "rightslide");
        leftslide = hardwareMap.get(Servo.class, "leftslide");

        //holder system
        holder = hardwareMap.get(Servo.class,"holder");
        leftholder = hardwareMap.get(Servo.class,"leftholder");
        rightholder = hardwareMap.get(Servo.class,"rightholder");

        RD = hardwareMap.get(DcMotor.class,"RD");
        LD = hardwareMap.get(DcMotor.class,"LD");
        RD.setDirection(DcMotorSimple.Direction.REVERSE);
        RD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RD.setTargetPosition(0);
        LD.setTargetPosition(0);
        RD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        claw.setPosition(1);
        rightholder.setPosition(0);
        leftholder.setPosition(1);
        leftclaw.setPosition(0.2);
        rightclaw.setPosition(.5);
        holder.setPosition(0);
        spin.setPosition(1);
        RD.setTargetPosition(0);
        LD.setTargetPosition(0);
        RD.setPower(1);
        LD.setPower(1);
        leftslide.setPosition(0);
        rightsilde.setPosition(1);


        // 等待比赛开始
        waitForStart();
        timer = new timer();

        // 执行动作序列：前进到X=32，然后执行升降电机动作，再回到X=0
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        //.afterTime(0,new ArmAction(leftholder,rightholder,1,0))
                        //.afterTime(0,new MotorAction(leftLevel,rightLevel,500))
                        .afterTime(0,new ArmAction(leftholder,rightholder,1,0))
                        .strafeToSplineHeading(new Vector2d(5,35), Math.toRadians(90))
                        //.afterTime(0,new ArmAction(leftholder,rightholder,1,0))
                        //.afterTime(0,new SpinAction(spin,0))
                        .waitSeconds(2)
                        //.afterTime(0,new MotorAction(leftLevel,rightLevel,700))



                        //.waitSeconds(6)
                        .lineToY(41)
                        //.afterTime(0,new MotorAction(leftLevel,rightLevel,0))
                        .strafeToSplineHeading(new Vector2d(41, 40), Math.toRadians(90))
                        //.splineTo(new Vector2d(-37, 20), Math.toRadians(-90))
                        .strafeToSplineHeading(new Vector2d(41, 12), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(45, 12), Math.toRadians(90))                        //.lineToX(-48)

                        .strafeToSplineHeading(new Vector2d(45, 55), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(45, 15), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(55, 15), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(55, 55), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(55, 15), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(62, 15), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(62, 55), Math.toRadians(90))
                        .strafeToSplineHeading(new Vector2d(62, 40), Math.toRadians(90))



                        //.lineToX(-48)

                        //.lineToX(-48)

                        //.lineToX(-48)

                        //.lineToX(-48)

                        //.lineToX(-48)
                        //.lineToY(62)


                        .strafeToSplineHeading(new Vector2d(24, 0), Math.toRadians(179))



                        //.stopAndAdd(new MotorAction(leftLevel,rightLevel,1000))
                        //.afterTime(0,new MotorAction(leftLevel,rightLevel,3500))
                        //.lineToX(50)

                        //.lineToX(0)
                        //.lineToX(5)
                        //.lineToX(10)
                        //.lineToX(0)
                        //.afterTime(0,new MotorAction(leftLevel,rightLevel,0))
                        .build()
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
