package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

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
                        .splineToConstantHeading(new Vector2d(-4.82, -31.37), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(12.98, -41.15), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(35.52, -28.84), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(38.78, -12.53), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(49.16, -14.01), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(48.27, -65.77), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(51.09, -12.09), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(59.99, -11.79), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(60.43, -66.22), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(66.22, -12.09), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(66.07, -65.77), Math.toRadians(270.00))












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