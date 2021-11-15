package com.example.eggapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimerListener {

    TextView timerTextView;
    Button startButton;
    Button softBoiledButton;
    Button mediumBoiledButton;
    Button hardBoiledButton;
    boolean startButtonEnabled = false;
    Handler handler = new Handler();
    TimerClass timer = new TimerClass(handler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerTextView = findViewById(R.id.timerView);
        startButton = findViewById(R.id.startEggBoiling);
        softBoiledButton= findViewById(R.id.softBoiled);
        mediumBoiledButton = findViewById(R.id.mediumBoiled);
        hardBoiledButton = findViewById(R.id.hardBoiled);
    }
    public void onButtonEggSelectedClicked(View view) {
        switch(view.getId()){
            case R.id.softBoiled:
                timer.setTime(5, 0);
                timerTextView.setText(String.format("%d:%02d", 5, 0));
                startButton.setEnabled(true);
                startButtonEnabled = true;
                break;
            case R.id.mediumBoiled:
                timer.setTime(7, 0);
                timerTextView.setText(String.format("%d:%02d", 7, 0));
                startButton.setEnabled(true);
                startButtonEnabled = true;
                break;
            case R.id.hardBoiled:
                timer.setTime(10, 0);
                timerTextView.setText(String.format("%d:%02d", 10, 0));
                startButton.setEnabled(true);
                startButtonEnabled = true;
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }
    public void startButtonClicked(View view){
        if (startButtonEnabled) {
            timer.addListener(this);
            handler.post(timer);
            disableAllEggButtons();
            startButton.setText("Stop");
            startButtonEnabled = false;
        }
        else{
            stop();
        }
    }

    public void stop(){
        startButton.setText("Start");
        timer.removeListener(this);
        startButtonEnabled = true;
        enableAllEggButtons();
    }

    public void disableAllEggButtons(){
        softBoiledButton.setEnabled(false);
        mediumBoiledButton.setEnabled(false);
        hardBoiledButton.setEnabled(false);
    }
    public void enableAllEggButtons() {
        softBoiledButton.setEnabled(true);
        mediumBoiledButton.setEnabled(true);
        hardBoiledButton.setEnabled(true);
    }
    @Override
    public void onCountDown(int minutes, int seconds) {
        timerTextView.setText(String.format("%d:%02d", minutes, seconds));
    }

    @Override
    public void onTimerStopped() {
        stop();
        startButton.setEnabled(false);
    }
}