package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class back_arm_system {
    public Servo leftholder,rightholder;

    public back_arm_system(Servo leftholder, Servo rightholder) {
        this.leftholder = leftholder;
        this.rightholder = rightholder;
    }

    public void front() {
        leftholder.setPosition(0);
        rightholder.setPosition(1);
    }

    public void back() {
        leftholder.setPosition(1);
        rightholder.setPosition(0);
    }
}
