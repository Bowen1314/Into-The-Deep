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

package org.firstinspires.ftc.teamcode;

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
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Level_Test_3", group = "AAA-First")
public class Level_Test_3 extends OpMode {
    //declear all usage
    private DcMotor leftLevel;
    private DcMotor rightLevel;

    @Override
    public void init() {
        //level system
        leftLevel = hardwareMap.get(DcMotor.class, "leftlevel");
        rightLevel = hardwareMap.get(DcMotor.class, "rightlevel");
        //level system config
        rightLevel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLevel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }

    @Override
    public void loop() {
        //start of starting position & setup

        double levelpower = gamepad1.left_trigger - gamepad1.right_trigger;

        leftLevel.setPower(levelpower);
        rightLevel.setPower(levelpower);


        //start of claw open/close
        //
    }
}