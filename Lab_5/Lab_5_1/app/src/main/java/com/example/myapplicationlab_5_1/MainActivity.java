package com.example.myapplicationlab_5_1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.WorkoutPartBase;

public class MainActivity extends AppCompatActivity {

    static final int ADD_NEW_WORKOUT_REQ_ID = 123;

    ArrayList<WorkoutPartBase> workouts_array_list = null;
    ListView activity_main_ListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workouts_array_list = new ArrayList<>();
        activity_main_ListView = findViewById(R.id.activity_main_ListView);

        // Fetch previous workout from file
        try {
            workouts_array_list = readFromFile(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            case R.id.item_clear:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Do you really want to clear workouts ?")
                        .setTitle("Clear workouts")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                workouts_array_list.clear();
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
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

        try {
            saveToFile(this, workouts_array_list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_WORKOUT_REQ_ID && resultCode == RESULT_OK) {
            WorkoutPartBase workout = (WorkoutPartBase) data.getSerializableExtra("ACTIVITY");
            workouts_array_list.add(workout);
        }
    }

    public void startButtonClicked(View view) throws IOException {
        Intent intent = new Intent(MainActivity.this, RunProgramTimerActivity.class);
        intent.putExtra("ACTIVITIES_LIST", workouts_array_list);
        startActivity(intent);
    }

    private ArrayList<WorkoutPartBase> readFromFile(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("myFile");
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<WorkoutPartBase> workouts_array_list = (ArrayList<WorkoutPartBase>) is.readObject();
        is.close();
        fis.close();
        return workouts_array_list;
    }

    private void saveToFile(Context context, ArrayList<WorkoutPartBase> workouts_array_list) throws IOException, ClassNotFoundException {
        FileOutputStream fos = context.openFileOutput("myFile", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(workouts_array_list);
        os.close();
        fos.close();
    }
}