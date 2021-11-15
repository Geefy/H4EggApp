package com.example.eggapplication;

import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;


public class TimerClass implements Runnable {
    private int minutes = 0;
    private int seconds;
    private Handler handler;

    public ArrayList<TimerListener> listeners = new ArrayList<>();

    TimerClass(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run()
    {
        if (!listeners.isEmpty()) {
            if (minutes > 0 || seconds > 0) {
                calculateMinutes();
                for (TimerListener l : listeners) {
                    l.onCountDown(minutes, seconds);
                }
                handler.postDelayed(this, 1000);
            } else {
                for (TimerListener l : listeners) {
                    l.onTimerStopped();
                }
            }
        }
        }

        public void setTime(int minutes, int seconds)
        {
            this.minutes = minutes;
            this.seconds = seconds;
        }
        private void calculateMinutes(){
            seconds--;
            if (seconds < 0) {
                if (minutes > 0){
                    minutes--;
                }
                seconds = 59;
            }
        }
    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }


    public void removeListener(TimerListener listener) {
        listeners.remove(listener);
    }

}
