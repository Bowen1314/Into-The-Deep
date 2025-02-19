package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Timer;
import java.util.TimerTask;

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

    // Immediate method for setting the origin position.
    public void origin() {
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    // Non-blocking delayed version of origin.
    public void origin(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                origin();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Immediate method for chamber_high position.
    public void chamber_high() {
        leftLevel.setTargetPosition(400);
        rightLevel.setTargetPosition(-400);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    // Non-blocking delayed version of chamber_high.
    public void chamber_high(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                chamber_high();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Immediate method for basket_high position.
    public void basket_high() {
        leftLevel.setTargetPosition(2000);
        rightLevel.setTargetPosition(-2000);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    // Non-blocking delayed version of basket_high.
    public void basket_high(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                basket_high();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Immediate method for clip position.
    public void clip() {
        leftLevel.setTargetPosition(350);
        rightLevel.setTargetPosition(-350);
        leftLevel.setPower(1);
        rightLevel.setPower(1);
    }

    // Non-blocking delayed version of clip.
    public void clip(final long delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clip();
                timer.cancel();
            }
        }, delayMillis);
    }

    // Methods to get the current encoder positions.
    public int getCurrentPositionL() {
        return leftLevel.getCurrentPosition();
    }

    public int getCurrentPositionR() {
        return rightLevel.getCurrentPosition();
    }
}
