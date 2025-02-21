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

public class Right_Auto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        meepMeep.setDarkMode(true);
        meepMeep.getWindowFrame().setVisible(true);
        RoadRunnerBotEntity Blue = new DefaultBotBuilder(meepMeep)
                .setConstraints(80, 80, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(14, -62, Math.toRadians(90)))
                        .strafeTo(new Vector2d(-6,-23))
                        .strafeTo(new Vector2d(15,-33))
                        .splineToConstantHeading(new Vector2d(33, -15), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(45, -15), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(45.00, -58), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(45.00, -10), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(60, -10), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(60, -58), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(60, 0), Math.toRadians(90.00))
                        .splineToConstantHeading(new Vector2d(70, 0), Math.toRadians(270.00))
                        .splineToConstantHeading(new Vector2d(64, -58), Math.toRadians(270.00))

                        .splineToConstantHeading(new Vector2d(38,-70), Math.toRadians(270.00))
                        .strafeTo(new Vector2d(-6,-25))
                        .strafeTo(new Vector2d(38,-70))
                        .strafeTo(new Vector2d(-4,-25))
                        .strafeTo(new Vector2d(38,-70))
                        .strafeTo(new Vector2d(-2,-25))
                        .strafeTo(new Vector2d(38,-70))
                        .strafeTo(new Vector2d(0,-25))










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