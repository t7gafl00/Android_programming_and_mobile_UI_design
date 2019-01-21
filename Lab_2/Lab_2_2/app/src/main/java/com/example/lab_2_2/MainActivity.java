package com.example.lab_2_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int count;
    private Button gameButton;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences("", Context.MODE_PRIVATE);
        count = sharedPref.getInt("countKey", 0);
        editor = sharedPref.edit();

        gameButton = new Button(this);
        gameButton.setOnClickListener(this);
        //gameButton.setText("Hello, I'm the button");
        gameButton.setText(String.format("You have pressed the button %d times.", count));
        setContentView(gameButton);
    }

    @Override
    public void onClick(View v) {
        count++;
        gameButton.setText(String.format("You have pressed the button %d times.", count));

        editor.putInt("countKey", count);
        editor.apply();
    }
}
