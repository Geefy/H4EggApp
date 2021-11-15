package com.example.eggapplication;

public interface TimerListener {
    public void onCountDown(int minutes, int seconds);

    public void onTimerStopped();

}
