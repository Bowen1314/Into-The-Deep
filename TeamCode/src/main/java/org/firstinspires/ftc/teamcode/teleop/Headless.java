//************************************************************
//|  9121 Falcon Force 2024-2025 In To The Deep TeleOp Code  |
//************************************************************

//    ###     #     ###     #
//   #   #   ##    #   #   ##
//   #  ##  # #        #  # #
//    ## #    #      ##     #
//       #    #     #       #
//      #     #    #        #
//    ##    #####  #####  #####

package org.firstinspires.ftc.teamcode.teleop;

//import section
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "AAA - Driving System Solo", group = "AAA-First")
public class Headless extends OpMode {
    //declear all usage
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo leftclaw;
    private Servo rightclaw;
    private Servo leftslide;
    private Servo rightsilde;
    private Servo claw;
    private Servo holder;
    private Servo leftholder;
    private Servo rightholder;
    private Servo spin;
    private IMU imu;
    private DcMotor leftLevel;
    private DcMotor rightLevel;
    private double mutispeed = .6;
    boolean clawOpen1 = true;
    boolean clawOpen = true;
    boolean aPressed = false;
    boolean bPressed = false;
    boolean xPressed = false;
    boolean dpad_leftPressed = false;
    boolean dpad_upPressed = false;
    boolean leftbumper_Pressed = false;
    boolean levelAt2000 = false;
    boolean levelAt4000 = false;
    boolean dk = false;
    private float currentHeading;
    private float headingOffset = 0;


    @Override
    public void init() {
        //wheels
        frontLeft = hardwareMap.get(DcMotor.class, "LF");
        frontRight = hardwareMap.get(DcMotor.class, "RF");
        backLeft = hardwareMap.get(DcMotor.class, "LB");
        backRight = hardwareMap.get(DcMotor.class, "RB");
        //wheels config
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);






        //claw system
        claw = hardwareMap.get(Servo.class, "claw");
        leftclaw = hardwareMap.get(Servo.class, "leftclaw");
        rightclaw = hardwareMap.get(Servo.class, "rightclaw");

        //slide
        rightsilde = hardwareMap.get(Servo.class, "rightslide");
        leftslide = hardwareMap.get(Servo.class, "leftslide");

        //holder system
        holder = hardwareMap.get(Servo.class,"holder");
        leftholder = hardwareMap.get(Servo.class,"leftholder");
        rightholder = hardwareMap.get(Servo.class,"rightholder");
        spin = hardwareMap.get(Servo.class,"spin");

        //level system
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        //level system config
        rightLevel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLevel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLevel.setTargetPosition(0);
        rightLevel.setTargetPosition(0);
        leftLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLevel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //gyro system
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.initialize(parameters);





    }

    @Override
    public void loop() {

        if (gamepad1.right_stick_button){

            rightholder.setPosition(0);
            leftholder.setPosition(1);
            leftclaw.setPosition(0);
            rightclaw.setPosition(1);
            holder.setPosition(0.6);
            spin.setPosition(1);
            //leftLevel.setTargetPosition(150);
            //rightLevel.setTargetPosition(150);
            //leftLevel.setPower(1.0);
            //rightLevel.setPower(1.0);

        }
        //start of starting position & setup

        //end of starting position & setup
        double second_left_trigger = gamepad2.left_trigger;
        double second_right_trigger = gamepad2.right_trigger;
        int increment = 50;
        int leftLevel_Target = leftLevel.getCurrentPosition() + (int) ((second_left_trigger-second_right_trigger)*increment);
        int rightLevel_Target = rightLevel.getCurrentPosition() + (int) ((second_left_trigger-second_right_trigger)*increment);

        //leftLevel.setTargetPosition(leftLevel_Target);
        //rightLevel.setTargetPosition(rightLevel_Target);
        //leftLevel.setPower(1);
        //rightLevel.setPower(1);


        //Start of MacanumDive using headless mode
        if (gamepad1.y) {
            headingOffset = (float) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        }




        if (gamepad1.left_stick_button) {
            mutispeed = .5;
        } else {
            mutispeed = 1;
        }


        currentHeading = (float) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - headingOffset;
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;
        double rotatedX = x * Math.cos(currentHeading) + y * Math.sin(currentHeading);
        double rotatedY = -x * Math.sin(currentHeading) + y * Math.cos(currentHeading);
        double frontLeftPower = rotatedY + rotatedX + rx;
        double frontRightPower = rotatedY - rotatedX - rx;
        double backLeftPower = rotatedY - rotatedX + rx;
        double backRightPower = rotatedY + rotatedX - rx;
        frontLeft.setPower(mutispeed * Range.clip(frontLeftPower, -1.0, 1.0));
        frontRight.setPower(mutispeed * Range.clip(frontRightPower, -1.0, 1.0));
        backLeft.setPower(mutispeed * Range.clip(backLeftPower, -1.0, 1.0));
        backRight.setPower(mutispeed * Range.clip(backRightPower, -1.0, 1.0));

        telemetry.addData("Heading (deg) *Player 1 press Y to reset*", Math.toDegrees(currentHeading));
        telemetry.addData("Front Left Power", frontLeftPower);
        telemetry.addData("Front Right Power", frontRightPower);
        telemetry.addData("Back Left Power", backLeftPower);
        telemetry.addData("Back Right Power", backRightPower);
        telemetry.addData("Left Level",leftLevel.getCurrentPosition());
        telemetry.addData("Right Level", rightLevel.getCurrentPosition());
        telemetry.update();
        //End of MacanumDive using headless mode



        //start of claw open/close
        if (gamepad1.a && !aPressed) {
            clawOpen1 = !clawOpen1;
            aPressed = true;
            if (clawOpen1) {
                leftclaw.setPosition(0);
                rightclaw.setPosition(1);

            } else {
                leftclaw.setPosition(.2);
                rightclaw.setPosition(.5);

            }
        }
        if (!gamepad1.a) {
            aPressed = false;
        }
        //end of claw open/close

        //start holder open/close
        if (gamepad1.b && !bPressed) {
            clawOpen = !clawOpen;
            bPressed = true;
            if (clawOpen) {
                holder.setPosition(0);
            } else {
                holder.setPosition(.7);
            }
        }
        if (!gamepad1.b) {
            bPressed = false;
        }
        //end holder open/close

        //start of link system
        if (gamepad1.right_trigger > 0.1) {
            mutispeed = .5;
            claw.setPosition(0);
            leftslide.setPosition(.3);
            rightsilde.setPosition(.7);
        } else {
            mutispeed = 1;
            claw.setPosition(1);
            leftslide.setPosition(0);
            rightsilde.setPosition(1);
        }
        //end of link system

        //start of level system

        if (gamepad1.dpad_left && !dpad_leftPressed) {
            levelAt2000 = !levelAt2000;
            if (levelAt2000) {
                leftLevel.setTargetPosition(900);
                rightLevel.setTargetPosition(900);
                rightholder.setPosition(1);
                leftholder.setPosition(0);
                spin.setPosition(0);
            } else {
                leftLevel.setTargetPosition(0);
                rightLevel.setTargetPosition(0);
                rightholder.setPosition(0);
                leftholder.setPosition(1);
                spin.setPosition(1);
            }
            leftLevel.setPower(1.0);
            rightLevel.setPower(1.0);
            dpad_leftPressed = true;
        }
        if (!gamepad1.dpad_left) {
            dpad_leftPressed = false;
        }

        if (gamepad1.dpad_down) {
            leftLevel.setTargetPosition(0);
            rightLevel.setTargetPosition(0);
            leftLevel.setPower(1.0);
            rightLevel.setPower(1.0);
        }

        if (gamepad1.dpad_up && !dpad_upPressed){
            levelAt4000 = !levelAt4000;
            if (levelAt4000) {
                leftLevel.setTargetPosition(4000);
                rightLevel.setTargetPosition(4000);
                spin.setPosition(1);
                rightholder.setPosition(1);
                leftholder.setPosition(0);
            } else {
                leftLevel.setTargetPosition(0);
                rightLevel.setTargetPosition(0);
                spin.setPosition(1);
                rightholder.setPosition(0);
                leftholder.setPosition(1);
            }
            leftLevel.setPower(1.0);
            rightLevel.setPower(1.0);
            dpad_upPressed = true;
        }
        if (!gamepad1.dpad_up) {
            dpad_upPressed = false;
        }


        //end of level system
    }
}