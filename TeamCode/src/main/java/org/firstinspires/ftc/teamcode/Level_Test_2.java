package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Config
@TeleOp(name = "TwoLevelMotorControlWithPID", group = "TeleOp")
public class Level_Test_2 extends LinearOpMode {

    private DcMotor leftLevel,rightLevel; // 声明电机

    @Override
    public void runOpMode() {
        // 初始化电机，使用编码器进行测速
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart(); // 等待比赛开始

        while (opModeIsActive()) {
            // 如果按下 X 键，移动到 0 位置
            if (gamepad1.x) {
                moveMotorToPositionPID(0);
            }
            // 如果按下 Y 键，移动到 500 位置
            if (gamepad1.y) {
                moveMotorToPositionPID(500);
            }

            telemetry.addData("Motor Position", leftLevel.getCurrentPosition());
            telemetry.update();
        }
    }

    /**
     * 使用自定义 PID 算法控制电机移动到目标位置
     * @param targetPosition 目标编码器位置
     */
    private void moveMotorToPositionPID(int targetPosition) {
        // PID 参数（需要根据实际情况进行调试）
        double kP = 0.01;
        double kI = 0.0001;
        double kD = 0.001;

        int threshold = 10; // 允许的误差范围

        double errorL;
        double errorR;
        double integralL = 0;
        double integralR = 0;
        double derivativeL;
        double derivativeR;
        double lastErrorL = 0;
        double lastErrorR = 0;
        double motorPowerL;
        double motorPowerR;

        // 使用计时器计算时间差 dt
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        // 切换到手动控制模式
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive()) {
            // 计算当前误差
            errorL = targetPosition - leftLevel.getCurrentPosition();
            errorR = targetPosition - rightLevel.getCurrentPosition();

            // 当误差在允许范围内时退出循环
            if (Math.abs(errorL) <= threshold) {
                break;
            }
            if (Math.abs(errorR) <= threshold) {
                break;
            }

            double dt = timer.seconds();
            timer.reset();

            // 防止 dt 为0
            if (dt <= 0) {
                dt = 0.01;
            }

            // 累计积分与计算微分
            integralL += errorL * dt;
            derivativeL = (errorL - lastErrorL) / dt;
            integralR += errorR * dt;
            derivativeR = (errorR - lastErrorR) / dt;

            // 计算 PID 输出
            motorPowerL = kP * errorL + kI * integralL + kD * derivativeL;
            motorPowerL = Range.clip(motorPowerL, -1.0, 1.0);

            motorPowerR = kP * errorR + kI * integralR + kD * derivativeR;
            motorPowerR = Range.clip(motorPowerR, -1.0, 1.0);

            leftLevel.setPower(motorPowerL);
            rightLevel.setPower(motorPowerR);

            lastErrorL = errorL;
            lastErrorR = errorR;

            // 显示调试信息
            telemetry.addData("Target", targetPosition);
            telemetry.addData("Error", errorL);
            telemetry.addData("Motor Power", motorPowerL);
            telemetry.addData("Current Position", leftLevel.getCurrentPosition());
            telemetry.update();
        }
        // 运动完成后停止电机
        leftLevel.setPower(0);
        rightLevel.setPower(0);
    }
}
