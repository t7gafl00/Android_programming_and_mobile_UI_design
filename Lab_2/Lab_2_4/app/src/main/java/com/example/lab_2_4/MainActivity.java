package com.example.lab_2_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> countries = new ArrayList<>();
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("American Samoa");
        countries.add("Andorra");
        countries.add("Angola");
        countries.add("Anguilla");
        countries.add("Antarctica");
        countries.add("Antigua and Barbuda");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Aruba");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");

        final EditText editTextInput = findViewById(R.id.edit_text_input);

        final ListView listViewCountries = findViewById(R.id.list_view_countries);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        listViewCountries.setAdapter(aa);

        // Display item in editTextInput when clicking on item in list
        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listViewCountries.getItemAtPosition(position);
                editTextInput.setText(o.toString());
            }
        });

        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countries.add(String.valueOf(editTextInput.getText()));
                Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
                aa.notifyDataSetChanged();
                editTextInput.setText("");
            }
        });

        Button buttonRemove = findViewById(R.id.button_remove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countries.remove(String.valueOf(editTextInput.getText()));
                aa.notifyDataSetChanged();
                editTextInput.setText("");
            }
        });
    }
}
