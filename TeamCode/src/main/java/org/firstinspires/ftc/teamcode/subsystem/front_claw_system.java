package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class front_claw_system {
    public Servo leftclaw,rightclaw;

    public front_claw_system(Servo leftclaw, Servo rightclaw) {
        this.leftclaw = leftclaw;
        this.rightclaw = rightclaw;
    }

    public void open() {
        leftclaw.setPosition(.5);
        rightclaw.setPosition(.5);
    }

    public void close() {
        leftclaw.setPosition(0);
        rightclaw.setPosition(1);
    }
}
