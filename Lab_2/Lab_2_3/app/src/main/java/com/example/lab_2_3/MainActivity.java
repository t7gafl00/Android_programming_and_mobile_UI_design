package com.example.lab_2_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout verticalLayout = new LinearLayout(this);
        verticalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        // first parameter: width, second parameter: height
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setGravity(Gravity.CENTER);
        setContentView(verticalLayout);

        LinearLayout btnLayout = new LinearLayout(this);
        btnLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        btnLayout.setGravity(Gravity.CENTER);
        verticalLayout.addView(btnLayout);

        Button addBtn = new Button(this);
        addBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        addBtn.setText("Add");
        btnLayout.addView(addBtn);

        Button editBtn = new Button(this);
        editBtn.setText("Edit");
        btnLayout.addView(editBtn);

        Button removeBtn = new Button(this);
        removeBtn.setText("Remove");
        btnLayout.addView(removeBtn);

        EditText text_editor = new EditText(this);
        verticalLayout.addView(text_editor);

        ListView country_list_view = new ListView(this);
        verticalLayout.addView(country_list_view);

        final String[] COUNTRIES = new String[]{
                "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
                "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
                "Aremnia", "Aruba", "Australia", "Australia", "Azerbaijan"
        };

        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, COUNTRIES);
        country_list_view.setAdapter(aa);
    }

    @Override
    public void onClick(View v) {

    }
}
