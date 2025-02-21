package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import java.util.Timer;
import java.util.TimerTask;

public class angle_system {
    public Servo angle;

    public angle_system(Servo angle) {
        this.angle = angle;
    }

    // Immediate methods
    public void middle() {
        angle.setPosition(.5);
    }
    public void grab() {
        angle.setPosition(.7);
    }


    public void up() {
        angle.setPosition(1);
    }
    public void down() {
        angle.setPosition(0.2);
    }

    // Non-blocking delayed open method
    public void middle(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                middle();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Non-blocking delayed close method
    public void up(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                up();
                timer.cancel();
            }
        }, delayMillis);
    }

    public void down(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                down();
                timer.cancel();
            }
        }, delayMillis);
    }
    public void grab(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                grab();
                timer.cancel();
            }
        }, delayMillis);
    }
}
