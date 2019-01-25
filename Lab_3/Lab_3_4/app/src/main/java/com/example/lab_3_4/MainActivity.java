package com.example.lab_3_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText_duration = null;
    Button button_start = null;

    private int duration = 0;
    Editable str_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_duration = findViewById(R.id.editText_duration);
        button_start = findViewById(R.id.button_start);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_duration = editText_duration.getText();
                if (!str_duration.toString().isEmpty() && android.text.TextUtils.isDigitsOnly(str_duration)) {
                    duration = Integer.parseInt(String.valueOf(editText_duration.getText()));

                    // Send duration to TimerCountdownActivity and start that activity
                    Intent intent = new Intent(MainActivity.this, TimerCountdownActivity.class);
                    intent.putExtra("DURATION", duration);
                    startActivity(intent);
                }
            }
        });
    }
}
