package com.example.lab_5_2.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.lab_5_2.R;
import com.example.lab_5_2.model.ToDoItem;
import com.example.lab_5_2.model.ToDoItemArrayAdapter;
import com.example.lab_5_2.model.ToDoModel;
import com.example.lab_5_2.model.db.ToDoItemContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ToDoItem> toDoItems_ArrayList = new ArrayList<>();
    ListView listView = null;

    ToDoModel model = null;
    ToDoItem toDoItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = new ToDoModel((this));
        listView = findViewById(R.id.main_list_view);
        findViewById(R.id.add_FloatingActionButton).setOnClickListener(this);

        // Long click on item in ListView to remove
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you really want to remove this item ?")
                        .setTitle("Remove item")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                toDoItem = (ToDoItem) listView.getItemAtPosition(position);
                                model.deleteToDoItemFromDb(toDoItem);
                                refreshList(0, 0);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Create new item
            case R.id.add_FloatingActionButton:
                Intent addNewToDoItem = new Intent(this, AddNewItemActivity.class);
                startActivity(addNewToDoItem);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Sort according to date
            case R.id.sort_item:
                refreshList(1, 0);
                return true;
            case R.id.today_item:
                refreshList(0, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList(0, 0);
    }

    private void refreshList(int sort_value, int today) {
        // Update ArrayList with items from database
        // sort_value = 0: Sort according to ID / 1: Sort according to item date
        toDoItems_ArrayList.clear();
        Cursor cursor = null;

        if (today == 1) {
            cursor = model.getTodayToDoItemsList();
        }
        else {
            cursor = model.getToDoItemsList(sort_value);
        }

        while(cursor.moveToNext()) {

            String name = (cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItem.COLUMN_NAME_NAME)));
            String description = (cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION)));
            String date = (cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItem.COLUMN_NAME_DATE)));

            ToDoItem toDoItem = new ToDoItem(name, description, date);
            toDoItems_ArrayList.add(toDoItem);
        }
        cursor.close();

        // Update ArrayAdapter
        ToDoItemArrayAdapter adapter = new ToDoItemArrayAdapter(this, toDoItems_ArrayList);
        listView.setAdapter(adapter);
    }
}
