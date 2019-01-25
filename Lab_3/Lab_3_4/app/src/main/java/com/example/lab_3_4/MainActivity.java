package com.example.lab_3_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_duration = null;
    Button button_start = null;

    private int duration = 0;
    Editable user_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_duration = findViewById(R.id.editText_duration);
        button_start = findViewById(R.id.button_start);

        Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_input = editText_duration.getText();
                if (!user_input.toString().isEmpty() && android.text.TextUtils.isDigitsOnly(user_input)) {
                    // Toast.makeText(MainActivity.this, Integer.toString(duration), Toast.LENGTH_SHORT).show();

                    duration = Integer.parseInt(String.valueOf(editText_duration.getText()));
                    Intent intent = new Intent(MainActivity.this, TimerCountdownActivity.class);
                    intent.putExtra("DURATION", duration);
                    startActivity(intent);
                }
            }
        });
    }
}
