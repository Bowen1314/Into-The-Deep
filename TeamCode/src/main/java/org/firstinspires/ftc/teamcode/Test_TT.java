package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Trajectory;


import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



@Autonomous(name = "Roadrunner Trajectory Example", group = "Examples")
public class Test_TT extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(14, 62,0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        drive.setPoseEstimate(beginPose);

        waitForStart();

        if (isStopRequested()) return;

        TrajectoryActionBuilder test = drive.actionBuilder(beginPose)
            .splineToSplineHeading(new Pose2d(-35,32, Math.toRadians(45)), Math.toRadians(-90));


        Actions.runBlocking(
                new SequentialAction(
                        test.build()
                )
        );



    }
}
