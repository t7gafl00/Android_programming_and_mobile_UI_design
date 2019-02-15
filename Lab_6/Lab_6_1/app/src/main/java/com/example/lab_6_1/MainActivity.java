package com.example.lab_6_1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePicker timepicker;
    private EditText reminder_text_edittext;
    private Button done_button;

    private ReminderData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timepicker = findViewById(R.id.time_timepicker);
        timepicker.setIs24HourView(true);

        reminder_text_edittext = findViewById(R.id.reminder_text_edittext);

        done_button = findViewById(R.id.done_button);
        done_button.setOnClickListener(this);

        data = new ReminderData(this.getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.done_button) {
            saveData();
            sendBroadcast();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void loadData() {
        data.loadFromSharedPreferences();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timepicker.setHour(data.getHour());
            timepicker.setMinute(data.getMinute());
        }
        else {
            timepicker.setCurrentHour(data.getHour());
            timepicker.setCurrentMinute(data.getMinute());
        }
        reminder_text_edittext.setText(data.getReminder_text());
    }

    private void saveData() {
        int hour;
        int minute;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timepicker.getHour();
            minute = timepicker.getMinute();
        }
        else {
            hour = timepicker.getCurrentHour();
            minute = timepicker.getCurrentMinute();
        }
        String text = reminder_text_edittext.getText().toString();

        data.setHour(hour);
        data.setMinute(minute);
        data.setReminder_text(text);

        data.saveToSharedPreferences();
    }

    protected void sendBroadcast() {
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
        intent.putExtra("TEXT", data.getReminder_text());
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, data.getHour());
        calendar.set(Calendar.MINUTE, data.getMinute());

        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
    }
}
