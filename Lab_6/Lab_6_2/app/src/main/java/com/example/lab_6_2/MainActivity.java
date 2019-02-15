package com.example.lab_6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String saved_text;
    EditText saved_text_edittext = null;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("", Context.MODE_PRIVATE);
        saved_text = sharedPref.getString("textKey", "");
        editor = sharedPref.edit();

        saved_text_edittext = findViewById(R.id.saved_text_edittext);
        saved_text_edittext.setText(saved_text);
    }

    @Override
    protected void onStop() {
        super.onStop();

        saved_text = saved_text_edittext.getText().toString();

        editor.putString("textKey", saved_text);
        editor.apply();
    }
}
