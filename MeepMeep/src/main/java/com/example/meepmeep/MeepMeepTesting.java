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
                        .splineTo(new Vector2d(-8.53, -30.33), Math.toRadians(90.00))
                        .splineTo(new Vector2d(21.73, -40.56), Math.toRadians(18.43))
                        .splineTo(new Vector2d(34.48, -35.67), Math.toRadians(91.55))
                        .splineTo(new Vector2d(36.11, -22.32), Math.toRadians(84.29))












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