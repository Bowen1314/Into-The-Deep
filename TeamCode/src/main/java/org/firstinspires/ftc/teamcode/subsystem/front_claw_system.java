package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import java.util.Timer;
import java.util.TimerTask;

public class front_claw_system {
    public Servo leftClaw, rightClaw;

    public front_claw_system(Servo leftClaw, Servo rightClaw) {
        this.leftClaw = leftClaw;
        this.rightClaw = rightClaw;
    }

    // Immediate methods
    public void open() {
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.5);
    }

    public void close() {
        leftClaw.setPosition(0);
        rightClaw.setPosition(1);
    }

    // Non-blocking delayed open method
    public void open(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                open();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Non-blocking delayed close method
    public void close(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                close();
                timer.cancel();
            }
        }, delayMillis);
    }
}
