package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TwoLevelMotorControl", group = "TeleOp")
public class Level_Test_2 extends LinearOpMode {

    private DcMotor leftLevel; // 声明电机

    @Override
    public void runOpMode() {
        // 初始化电机
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel"); // 确保名称与配置匹配
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart(); // 等待比赛开始

        while (opModeIsActive()) {
            // 如果 X 按钮被按下，移动到 0 位置
            if (gamepad1.x) {
                moveMotorToPosition(0);
            }

            // 如果 Y 按钮被按下，移动到 500 位置
            if (gamepad1.y) {
                moveMotorToPosition(500);
            }

            telemetry.addData("Motor Position", leftLevel.getCurrentPosition());
            telemetry.update();
        }
    }

    private void moveMotorToPosition(int targetPosition) {
        leftLevel.setTargetPosition(targetPosition);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLevel.setPower(1.0); // 设定电机功率
        while (opModeIsActive() && leftLevel.isBusy()) {
            telemetry.addData("Moving to", targetPosition);
            telemetry.addData("Current Position", leftLevel.getCurrentPosition());
            telemetry.update();
        }
        leftLevel.setPower(0); // 运动完成后停止电机
        leftLevel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
