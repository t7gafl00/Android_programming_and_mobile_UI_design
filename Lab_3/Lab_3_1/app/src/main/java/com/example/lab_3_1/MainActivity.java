package com.example.lab_3_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview_color = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_color = findViewById(R.id.textview_color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.red_menu_item:
                textview_color.setText("Red");
                textview_color.setBackgroundColor(0xffff0000);
                return true;
            case R.id.green_menu_item:
                textview_color.setText("Green");
                textview_color.setBackgroundColor(0xff008000);
                return true;
            case R.id.blue_menu_item:
                textview_color.setText("Blue");
                textview_color.setBackgroundColor(0xff0000ff);
                return true;
            case R.id.yellow_menu_item:
                textview_color.setText("Yellow");
                textview_color.setBackgroundColor(0xffffff00);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
