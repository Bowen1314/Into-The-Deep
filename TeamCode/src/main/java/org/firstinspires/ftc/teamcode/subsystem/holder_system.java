package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class holder_system {
    public Servo holder;

    public holder_system(Servo holder) {
        this.holder = holder;
    }

    public void open() {
        holder.setPosition(0);
    }

    public void close() {
        holder.setPosition(1);
    }
}
