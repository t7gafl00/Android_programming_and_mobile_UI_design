package com.example.myapplicationlab_4_2;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import model.WorkoutPartBase;

public class RunProgramTimerActivity extends AppCompatActivity {

    TextView activity_type_TextView = null;
    TextView activity_duration_TextView = null;

    ArrayList<WorkoutPartBase> workouts_array_list = new ArrayList<>();

    int total_duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program_timer);

        activity_type_TextView = findViewById(R.id.activity_type_TextView);
        activity_duration_TextView = findViewById(R.id.activity_duration_TextView);

        Intent intent = getIntent();

        // Get duration of the timer set in MainActivity
        workouts_array_list = (ArrayList<WorkoutPartBase>) intent.getSerializableExtra("ACTIVITIES_LIST");

        /*** Compute total duration ***/

        for(int i = 0; i < workouts_array_list.size(); i++)
        {
            total_duration += workouts_array_list.get(i).getDuration() + 1;
        }

        new CountDownTimer(total_duration * 1000, 1000) {

            int i = 0;
            int time_left = 0;
            boolean move_on_to_next_activity = true;

            public void onTick(long millisUntilFinished) {

                if (move_on_to_next_activity == true) {
                    time_left = workouts_array_list.get(i).getDuration();
                    move_on_to_next_activity = false;
                }

                activity_type_TextView.setText(workouts_array_list.get(i).getActivity_type());
                activity_duration_TextView.setText(Integer.toString(time_left));

                time_left--;

                if (time_left < 0) {
                    move_on_to_next_activity = true;
                    i++;
                }
            }

            public void onFinish() {
                activity_type_TextView.setText("Done!");
                activity_duration_TextView.setText("");
            }
        }.start();
    }
}
