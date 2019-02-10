package com.example.lab_5_2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.lab_5_2.R;
import com.example.lab_5_2.model.ToDoItem;
import com.example.lab_5_2.model.ToDoModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewItemActivity extends AppCompatActivity{

    TextView name_EditText = null;
    TextView description_EditText = null;
    TextView date_EditText = null;
    CalendarView date_calendarView = null;

    ToDoModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        model = new ToDoModel((this));

        name_EditText = findViewById(R.id.add_item_name_EditText);
        description_EditText = findViewById(R.id.add_item_description_EditText);
        date_EditText = findViewById(R.id.add_item_date_EditText);
        date_calendarView = findViewById(R.id.add_item_date_CalendarView);

        // Update date text field when clicking on calendar
        date_calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(new Date(date_calendarView.getDate()));
                date_EditText.setText(date);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addnewitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                // Create item from text fields and Save item to db
                String name = String.valueOf(name_EditText.getText());
                String description = String.valueOf(description_EditText.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date(date_calendarView.getDate()));
                ToDoItem toDoItem = new ToDoItem(name, description, date);
                model.addToDoItemToDb(toDoItem);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
