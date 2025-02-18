package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "EncoderButtonOpMode", group = "Linear Opmode")
public class Level_Test_3 extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;

    // Variables to track the previous state of the buttons
    private boolean prevA = false;
    private boolean prevB = false;

    @Override
    public void runOpMode() {
        // Initialize the motors from the hardware map
        motorLeft = hardwareMap.get(DcMotor.class, "leftlevel");
        motorRight = hardwareMap.get(DcMotor.class, "rightlevel");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);


        // Reset and initialize encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Detect a rising edge on button A (pressed now but not in the previous loop)
            if (gamepad1.a) {
                motorLeft.setTargetPosition(500);
                motorRight.setTargetPosition(500);
                motorLeft.setPower(1);
                motorRight.setPower(1);
            }

            // Detect a rising edge on button B
            if (gamepad1.b) {
                motorLeft.setTargetPosition(0);
                motorRight.setTargetPosition(0);
                motorLeft.setPower(1);
                motorRight.setPower(1);

            }

            // Update previous button states for the next loop iteration

            // Display current encoder positions
            telemetry.addData("Left Motor", motorLeft.getCurrentPosition());
            telemetry.addData("Right Motor", motorRight.getCurrentPosition());
            telemetry.update();
        }
    }

    /**
     * Moves both motors to the specified encoder target position.
     *
     * @param targetPosition The target encoder count (0 or 500).
     */

}
