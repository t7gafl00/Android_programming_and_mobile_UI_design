package com.example.lab_6_1;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class ReminderData {

    static final String SHARED_PREF_FILE = "MyApp";
    static final String SHARED_PREF_HOUR_KEY = "hour";
    static final String SHARED_PREF_MINUTE_KEY = "minute";
    static final String SHARED_PREF_TEXT_KEY = "text";

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private int hour = 0;
    private int minute = 0;
    private String reminder_text = "";

    public ReminderData(Context context) {
        this.context = context;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getReminder_text() {
        return reminder_text;
    }

    public void setReminder_text(String reminder_text) {
        this.reminder_text = reminder_text;
    }

    public void loadFromSharedPreferences() {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);

        hour = sharedPreferences.getInt(SHARED_PREF_HOUR_KEY,0);
        minute = sharedPreferences.getInt(SHARED_PREF_MINUTE_KEY, 0);
        reminder_text = sharedPreferences.getString(SHARED_PREF_TEXT_KEY, null);
    }

    public void saveToSharedPreferences() {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREF_HOUR_KEY, hour);
        editor.putInt(SHARED_PREF_MINUTE_KEY, minute);
        editor.putString(SHARED_PREF_TEXT_KEY, reminder_text);

        editor.commit();
    }
}
