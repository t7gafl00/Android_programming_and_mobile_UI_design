package com.example.lab_3_4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TimerCountdownActivity extends AppCompatActivity {

    TextView textView_timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_countdown);

        Intent intent = getIntent();
        int time_left = (int) intent.getIntExtra("DURATION", 0);

        textView_timer = findViewById(R.id.textView_timer);


        //textView_timer.setText(Integer.toString(time_left));
        new CountDownTimer(time_left * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView_timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView_timer.setText("done!");
            }
        }.start();
    }
}
