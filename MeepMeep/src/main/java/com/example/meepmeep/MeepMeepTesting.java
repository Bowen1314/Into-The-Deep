package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        meepMeep.setDarkMode(true);
        meepMeep.getWindowFrame().setVisible(true);
        RoadRunnerBotEntity Blue = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(14, -62, Math.toRadians(90.00)))
                        .strafeTo(new Vector2d(-5,-32))
                        .strafeTo(new Vector2d(30,-37))
                        .splineToConstantHeading(new Vector2d(35, -11), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(48, -11), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(48.00, -55), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(48.00, -11.00), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(60.00, -11.00), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(60.00, -55.00), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(60.00, -11.00), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(65.00, -11.00), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(65.00, -55), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(37,-60), Math.toRadians(270.00))
                        .strafeTo(new Vector2d(-2,-32))
                        .strafeTo(new Vector2d(37,-60))
                        .strafeTo(new Vector2d(1,-32))
                        .strafeTo(new Vector2d(37,-60))
                        .strafeTo(new Vector2d(4,-32))

                        .strafeTo(new Vector2d(37,-60))
                        .strafeTo(new Vector2d(7,-32))










                        .build()
                );

        Image img = null;

        try {
            img = ImageIO.read(new File(ImageConst.FIELD_CENTERSTAGE_DARK));

        } catch (IOException e) {
        }

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Blue)
                .start();
    }
}