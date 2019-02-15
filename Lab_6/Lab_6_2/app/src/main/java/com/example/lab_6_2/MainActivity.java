package com.example.lab_6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String SHARED_PREF_EDITOR_TEXT_KEY = "text";

    private String saved_text;
    EditText saved_text_edittext = null;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saved_text_edittext = findViewById(R.id.saved_text_edittext);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPref = getSharedPreferences("", Context.MODE_PRIVATE);
        saved_text = sharedPref.getString(SHARED_PREF_EDITOR_TEXT_KEY, "");
        saved_text_edittext.setText(saved_text);
    }

    @Override
    protected void onStop() {
        super.onStop();

        saved_text = saved_text_edittext.getText().toString();

        editor = sharedPref.edit();
        editor.putString(SHARED_PREF_EDITOR_TEXT_KEY, saved_text);
        editor.apply();
    }
}
