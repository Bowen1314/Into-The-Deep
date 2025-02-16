package org.firstinspires.ftc.teamcode;

//import section
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Servo_M_Tuning", group = "AAA-First")
public class Servo_M_Tuning extends OpMode {

    private ServoImplEx servo;


    @Override
    public void init() {
        servo = hardwareMap.get(ServoImplEx.class,"claw");
        ServoControllerEx servoControllerEx = (ServoControllerEx)  servo.getController();
        int port = servo.getPortNumber();
        servoControllerEx.setServoPwmRange(port,new PwmControl.PwmRange(500,2500));

    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            servo.setPosition(0);
            telemetry.addData("Position","0");
        }
        if (gamepad1.b) {
            servo.setPosition(0.5);
            telemetry.addData("Position","0.5");
        }
        if (gamepad1.y) {
            servo.setPosition(1);
            telemetry.addData("Position","1");

        }

        telemetry.update();

    }

}