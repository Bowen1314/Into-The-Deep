package org.firstinspires.ftc.teamcode.tools;// VibrateHelper.java

import com.qualcomm.robotcore.hardware.Gamepad;

public class vib {
    public static void vibrate(Gamepad gamepad, int durationMs) {
        // Use the built-in rumble() method; full power on the first motor and zero on the second.
        gamepad.rumble(durationMs);
    }
}
