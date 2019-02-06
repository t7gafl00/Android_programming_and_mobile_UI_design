package com.example.myapplicationlab_5_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import model.PausePart;
import model.WorkoutPart;
import model.WorkoutPartBase;

public class AddNewPartActivity extends AppCompatActivity {

    private String activity_type = "Workout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_part);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.workout_RadioButton:
                if (checked)
                    activity_type = "Workout";
                    break;
            case R.id.pause_RadioButton:
                if (checked)
                    activity_type = "Pause";
                    break;
        }
    }

    public void onAddButtonClicked(View view) {
        int duration = Integer.parseInt(String.valueOf(((EditText)findViewById(R.id.duration_EditText)).getText()));
        switch (activity_type) {
            case "Workout":
                WorkoutPart workout = new WorkoutPart(duration);
                returnData(workout);
                break;
            case "Pause":
                PausePart pause = new PausePart(duration);
                returnData(pause);
                break;
        }
        // Toast.makeText(AddNewPartActivity.this, Integer.toString(duration), Toast.LENGTH_SHORT).show();
    }

    private void returnData(WorkoutPartBase data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("ACTIVITY", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
