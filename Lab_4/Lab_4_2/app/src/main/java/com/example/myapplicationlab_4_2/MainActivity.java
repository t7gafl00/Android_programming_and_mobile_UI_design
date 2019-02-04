package com.example.myapplicationlab_4_2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.WorkoutPartBase;

public class MainActivity extends AppCompatActivity {

    static final int ADD_NEW_WORKOUT_REQ_ID = 123;

    ArrayList<WorkoutPartBase> workouts_array_list = new ArrayList<>();
    ListView activity_main_ListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main_ListView = findViewById(R.id.activity_main_ListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // Menu
        switch (item.getItemId()) {
            case R.id.item_new:
                Intent intent = new Intent(this, AddNewPartActivity.class);
                startActivityForResult(intent, ADD_NEW_WORKOUT_REQ_ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*** Display activities ***/
        WorkoutArrayAdapter adapter = new WorkoutArrayAdapter(this, workouts_array_list);
        activity_main_ListView.setAdapter(adapter);

        /*** Compute and display total duration ***/
        int sum = 0;
        for(int i = 0; i < workouts_array_list.size(); i++)
        {
            sum += workouts_array_list.get(i).getDuration();
        }
        TextView total_duration_TextView = findViewById(R.id.total_duration_TextView);
        total_duration_TextView.setText("Total duration: " + Integer.toString(sum));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_WORKOUT_REQ_ID && resultCode == RESULT_OK) {
            WorkoutPartBase workout = (WorkoutPartBase) data.getSerializableExtra("ACTIVITY");
            workouts_array_list.add(workout);
        }
    }

    public void startButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, RunProgramTimerActivity.class);
        intent.putExtra("ACTIVITIES_LIST", workouts_array_list);
        startActivity(intent);
    }
}
