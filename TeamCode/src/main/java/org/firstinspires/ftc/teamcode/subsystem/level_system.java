package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;


public class level_system {
    public DcMotor leftLevel,rightLevel;

    public level_system(DcMotor leftLevel, DcMotor rightLevel) {
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;

        leftLevel.setDirection(DcMotor.Direction.REVERSE);

        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void origin(){
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setPower(1);
        rightLevel.setPower(1);

    }

    public void chamber_high() {
        leftLevel.setTargetPosition(500);
        rightLevel.setTargetPosition(500);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    public void basket_high() {
        leftLevel.setTargetPosition(4000);
        rightLevel.setTargetPosition(4000);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }
}
