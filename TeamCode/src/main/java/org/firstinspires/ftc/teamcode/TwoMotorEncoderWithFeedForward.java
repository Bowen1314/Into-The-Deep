package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TwoMotorEncoderWithFeedForward", group = "TeleOp")
public class TwoMotorEncoderWithFeedForward extends LinearOpMode {

    private DcMotor leftLevel, rightLevel;

    // 恒定前馈功率（始终提供以对抗重力）
    private final double FEED_FORWARD = 0.1;
    // 比例控制常数，根据实际情况调节
    private final double kP = 0.005;
    // 允许的误差范围
    private final int THRESHOLD = 10;

    @Override
    public void runOpMode() throws InterruptedException {
        // 获取硬件映射中的两个电机
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");

        // 重置编码器并设置电机模式
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setDirection(DcMotor.Direction.REVERSE);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // 初始目标位置设为 0
        int targetPosition = 0;

        waitForStart();

        while (opModeIsActive()) {
            // 根据游戏手柄输入设置目标位置
            if (gamepad1.y) {
                targetPosition = 500;
            }
            if (gamepad1.x) {
                targetPosition = 0;
            }

            // 获取当前编码器值
            int currentLeft = leftLevel.getCurrentPosition();
            int currentRight = rightLevel.getCurrentPosition();

            // 计算误差（目标值 - 当前值）
            double errorLeft = targetPosition - currentLeft;
            double errorRight = targetPosition - currentRight;

            double powerLeft, powerRight;

            // 如果误差大于允许范围，使用比例控制计算功率，并叠加前馈功率
            if (Math.abs(errorLeft) > THRESHOLD) {
                powerLeft = kP * errorLeft + FEED_FORWARD;
            } else {
                // 如果误差较小，仅使用前馈功率保持对抗重力
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

            // 设置电机功率
            leftLevel.setPower(powerLeft);
            rightLevel.setPower(powerRight);

            // 通过 telemetry 输出调试信息
            telemetry.addData("目标位置", targetPosition);
            telemetry.addData("左电机编码器", currentLeft);
            telemetry.addData("右电机编码器", currentRight);
            telemetry.addData("左电机功率", powerLeft);
            telemetry.addData("右电机功率", powerRight);
            telemetry.update();
        }
    }
}
