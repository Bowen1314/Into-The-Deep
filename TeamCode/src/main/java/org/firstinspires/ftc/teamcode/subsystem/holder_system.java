package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import java.util.Timer;
import java.util.TimerTask;

public class holder_system {
    public Servo holder;

    public holder_system(Servo holder) {
        this.holder = holder;
    }

    // Immediate methods
    public void open() {
        holder.setPosition(1);
    }

    public void close() {
        holder.setPosition(0.4);
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
