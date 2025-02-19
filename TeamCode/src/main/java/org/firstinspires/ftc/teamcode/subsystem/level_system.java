package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class level_system {
    public DcMotor leftLevel, rightLevel;


    public level_system(DcMotor leftLevel, DcMotor rightLevel) {
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;

        leftLevel.setDirection(DcMotor.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void origin() {
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    public void chamber_high() {
        leftLevel.setTargetPosition(1000);
        rightLevel.setTargetPosition(-1000);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    public void basket_high() {
        leftLevel.setTargetPosition(2000);
        rightLevel.setTargetPosition(-2000);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    public void clip() {
        leftLevel.setTargetPosition(900);
        rightLevel.setTargetPosition(-900);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    public int getCurrentPositionL() {
        return leftLevel.getCurrentPosition();
    }
    public int getCurrentPositionR() {
        return leftLevel.getCurrentPosition();
    }
}
