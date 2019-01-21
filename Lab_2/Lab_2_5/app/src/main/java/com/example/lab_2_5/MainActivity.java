package com.example.lab_2_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String hello_word = "Hi ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit_text = findViewById(R.id.edit_text);
        final TextView text_view = findViewById(R.id.text_view);

        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text_view.setText(hello_word + edit_text.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button button_english = findViewById(R.id.button_english);
        button_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello_word = "Hi ";
                text_view.setText(hello_word + edit_text.getText());
            }
        });

        Button button_sverige = findViewById(R.id.button_sverige);
        button_sverige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello_word = "Hejjsan ";
                text_view.setText(hello_word + edit_text.getText());
            }
        });

        Button button_suomeksi = findViewById(R.id.button_suomeksi);
        button_suomeksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello_word = "Terve ";
                text_view.setText(hello_word + edit_text.getText());
            }
        });

        Button button_surprise = findViewById(R.id.button_surprise);
        button_surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello_word = "Hola ";
                text_view.setText(hello_word + edit_text.getText());
            }
        });
    }
}
