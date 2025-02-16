package org.firstinspires.ftc.teamcode.tools;

public class timer {
    private long startTime;
    private long waitMS;

    public void start(long waitMS){
        startTime = System.nanoTime() / 1000000;
        this.waitMS = waitMS;
    }

    public boolean isDone(){ return ((System.nanoTime() / 1000000) > (startTime + waitMS )); }
}