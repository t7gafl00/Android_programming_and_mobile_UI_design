package com.example.lab_2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int count = 0;
    private Button gameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameButton = new Button(this);
        gameButton.setOnClickListener(this);
        gameButton.setText("Hello, I'm the button");
        setContentView(gameButton);
    }

    @Override
    public void onClick(View v) {
        count++;
        gameButton.setText(String.format("You have pressed the button %d times.", count));
    }
}
