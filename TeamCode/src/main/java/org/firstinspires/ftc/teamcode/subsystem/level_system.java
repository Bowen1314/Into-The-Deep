package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class level_system {
    public DcMotor leftLevel, rightLevel;

    int targetPosition = 0;

    private final double FEED_FORWARD = 0.1;
    private final double kP = 0.005;
    private final int THRESHOLD = 10;

    public level_system(DcMotor leftLevel, DcMotor rightLevel) {
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;

        leftLevel.setDirection(DcMotor.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // 使用 RUN_USING_ENCODER 模式，以便我们用自定义算法更新电机功率
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void update() {
        int currentLeft = leftLevel.getCurrentPosition();
        int currentRight = rightLevel.getCurrentPosition();

        int errorLeft = targetPosition - currentLeft;
        int errorRight = targetPosition - currentRight;

        double powerLeft, powerRight;

        // 当误差较大时，根据比例控制计算功率，并加上前馈功率
        if (Math.abs(errorLeft) > THRESHOLD) {
            powerLeft = kP * errorLeft + FEED_FORWARD;
        } else {
            powerLeft = FEED_FORWARD;
        }

        if (Math.abs(errorRight) > THRESHOLD) {
            powerRight = kP * errorRight + FEED_FORWARD;
        } else {
            powerRight = FEED_FORWARD;
        }

        // 限制功率范围在 -1.0 到 1.0 之间
        powerLeft = Range.clip(powerLeft, -1.0, 1.0);
        powerRight = Range.clip(powerRight, -1.0, 1.0);

        leftLevel.setPower(powerLeft);
        rightLevel.setPower(powerRight);
    }

    public void origin() {
        targetPosition = 0;
    }

    public void chamber_high() {
        targetPosition = 350;
    }

    public void basket_high() {
        targetPosition = 4000;
    }

    public void clip() {
        targetPosition = 300;
    }

    public int getCurrentPosition() {
        return leftLevel.getCurrentPosition();
    }
}
