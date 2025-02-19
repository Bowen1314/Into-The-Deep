package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import java.util.Timer;
import java.util.TimerTask;

public class back_arm_system {
    public Servo leftholder, rightholder;

    public back_arm_system(Servo leftholder, Servo rightholder) {
        this.leftholder = leftholder;
        this.rightholder = rightholder;
    }

    // Immediate methods
    public void front() {
        leftholder.setPosition(0);
        rightholder.setPosition(1);
    }

    public void middle() {
        leftholder.setPosition(0.5);
        rightholder.setPosition(0.5);
    }

    public void clip() {
        leftholder.setPosition(0.25);
        rightholder.setPosition(0.75);
    }

    public void back() {
        leftholder.setPosition(1);
        rightholder.setPosition(0);
    }

    // Non-blocking delayed methods
    public void front(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                front();
                timer.cancel();
            }
        }, delayMillis);
    }

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

    public void clip(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clip();
                timer.cancel();
            }
        }, delayMillis);
    }

    public void back(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                back();
                timer.cancel();
            }
        }, delayMillis);
    }
}
