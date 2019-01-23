package com.example.lab_3_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edittext_address = findViewById(R.id.edittext_address);

        Button button_open_map = findViewById(R.id.button_open_map);
        button_open_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Map point based on address
                Uri location = Uri.parse("geo:0,0?q=OAMK,+Kotkantie+1,+90250+Oulu,+Finland");
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

        Button button_create_call = findViewById(R.id.button_create_call);
        button_create_call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri number = Uri.parse("tel:+358206110200");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        Button button_go = findViewById(R.id.button_go);
        button_go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri webpage = Uri.parse(String.valueOf(edittext_address.getText()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });
    }
}
