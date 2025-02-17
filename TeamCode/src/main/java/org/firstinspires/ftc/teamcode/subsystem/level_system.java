package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;


public class level_system {
    public DcMotor leftLevel,rightLevel;


    public void level_system(DcMotor leftLevel, DcMotor rightLevel) {
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;

        leftLevel.setDirection(DcMotor.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
