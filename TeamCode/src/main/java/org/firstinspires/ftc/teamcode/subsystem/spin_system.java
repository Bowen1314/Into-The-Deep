package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import java.util.Timer;
import java.util.TimerTask;

public class spin_system {
    public Servo spin;

    public spin_system(Servo spin) {
        this.spin = spin;
    }

    // Immediate methods
    public void atfront() {
        spin.setPosition(0.7);
    }

    public void atback() {
        spin.setPosition(0);
    }

    // Non-blocking delayed atfront method
    public void atfront(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                atfront();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Non-blocking delayed atback method
    public void atback(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                atback();
                timer.cancel();
            }
        }, delayMillis);
    }
}
