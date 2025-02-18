package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class spin_system {
    public Servo spin;

    public spin_system(Servo spin) {
        this.spin = spin;
    }

    public void atfront() {
        spin.setPosition(0);
    }

    public void atback() {
        spin.setPosition(.7);
    }
}
