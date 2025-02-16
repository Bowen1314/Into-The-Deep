package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name ="Servo_Tuning", group = "TeleOp")
public class Servo_Tuning extends OpMode {

    private Servo servo, servo1;



    private double mutispeed = 1;

    private float currentHeading;
    private float headingOffset = 0;

    @Override
    public void init() {
        // Initialize motors

        servo = hardwareMap.get(Servo.class,"servo");
        servo1 = hardwareMap.get(Servo.class,"servo1");


        // Initialize IMU sensor

    }

    @Override
    public void loop() {
        // Reset heading when 'Y' button is pressed


        // Clip motor powers to be in range [-1.0, 1.0]

        if (gamepad1.x) {
            servo.setPosition(0);
            servo1.setPosition(1);

        }

        if (gamepad1.y) {
            //servo.setPosition(.5);
            servo1.setPosition(.5);
            servo.setPosition(.5);
        }

        if (gamepad1.b) {
            servo1.setPosition(0);
            servo.setPosition(1);
        }

        telemetry.addData("Servo Position", servo.getPosition());
        telemetry.update();





    }
}