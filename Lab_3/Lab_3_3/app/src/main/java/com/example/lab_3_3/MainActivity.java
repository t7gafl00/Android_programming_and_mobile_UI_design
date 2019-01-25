package com.example.lab_3_3;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView pic_1 = null;
    private ImageView pic_2 = null;
    private ImageView pic_3 = null;
    private ImageView pic_4 = null;

    private String animal_type = "mammals";

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pic_1 = findViewById(R.id.pic_1);
        pic_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_sound(animal_type, 1);
            }
        });

        pic_2 = findViewById(R.id.pic_2);
        pic_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_sound(animal_type, 2);
            }
        });

        pic_3 = findViewById(R.id.pic_3);
        pic_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_sound(animal_type, 3);
            }
        });

        pic_4 = findViewById(R.id.pic_4);
        pic_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_sound(animal_type, 4);
            }
        });

        show_animals(animal_type);
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
            case R.id.menu_item_mammals:
                animal_type = "mammals";
                show_animals(animal_type);
                return true;
            case R.id.menu_item_birds:
                animal_type = "birds";
                show_animals(animal_type);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_animals(String animal_type) {
        switch (animal_type) {
            case "mammals":
                pic_1.setImageDrawable(getResources().getDrawable(R.drawable.bear));
                pic_2.setImageDrawable(getResources().getDrawable(R.drawable.wolf));
                pic_3.setImageDrawable(getResources().getDrawable(R.drawable.elephant));
                pic_4.setImageDrawable(getResources().getDrawable(R.drawable.lamb));
                break;
            case "birds":
                pic_1.setImageDrawable(getResources().getDrawable(R.drawable.huuhkaja));
                pic_2.setImageDrawable(getResources().getDrawable(R.drawable.peippo));
                pic_3.setImageDrawable(getResources().getDrawable(R.drawable.peukaloinen));
                pic_4.setImageDrawable(getResources().getDrawable(R.drawable.punatulkku));
                break;
        }
    }

    private void play_sound(String animal_type, int picture_number) {
        if(mediaPlayer != null){
            mediaPlayer.stop();  //Stops playback after playback has been stopped or paused
            mediaPlayer.release(); //Releases resources associated with this MediaPlayer object
            mediaPlayer = null;
        }

        switch (animal_type) {
            case "mammals":
                switch (picture_number) {
                    case 1:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.bear);
                        break;
                    case 2:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.wolf);
                        break;
                    case 3:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.elephant);
                        break;
                    case 4:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.lamb);
                        break;
                }
                break;
            case "birds":
                switch (picture_number) {
                    case 1:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.huuhkaja_norther_eagle_owl);
                        break;
                    case 2:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.peippo_chaffinch);
                        break;
                    case 3:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.peukaloinen_wren);
                        break;
                    case 4:
                        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.punatulkku_northern_bullfinch);
                        break;
                }
                break;
        }
        mediaPlayer.start();
    }
}
