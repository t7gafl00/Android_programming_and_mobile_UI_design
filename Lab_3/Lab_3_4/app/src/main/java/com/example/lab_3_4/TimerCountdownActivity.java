package com.example.lab_3_4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class TimerCountdownActivity extends AppCompatActivity {

    TextView textView_timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_countdown);

        // Get duration of the timer set in MainActivity
        Intent intent = getIntent();
        int time_left = (int) intent.getIntExtra("DURATION", 0);

        textView_timer = findViewById(R.id.textView_timer);

        new CountDownTimer(time_left * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView_timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textView_timer.setText("");

                // Open an alert informing that time is out
                AlertDialog.Builder builder = new AlertDialog.Builder(TimerCountdownActivity.this);

                builder.setMessage("Your egg is cooked !")
                        .setTitle("Time out")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                TimerCountdownActivity.this.finish();
                            }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                // Play sound
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            }
        }.start();
    }
}
