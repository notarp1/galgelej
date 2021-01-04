package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vundet extends AppCompatActivity implements View.OnClickListener {
    TextView ordView, highOrd;
    Button nytSpil;


    SharedPreferences prefs;
    Set<String> highscore = new HashSet<String>();
    ListView listView;
    ArrayAdapter adapter;
    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);

        Intent i = getIntent();
        String ord = i.getStringExtra("ord");

        mp=MediaPlayer.create(this,R.raw.win);
        mp.start();



        ordView = findViewById(R.id.textView_ord);
        nytSpil = findViewById(R.id.nytSpil_btn);
        highOrd = findViewById(R.id.text_high);
        listView = findViewById(R.id.list_highscores);

        highOrd.setText(ord);
        ordView.setText(ord);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);


        highscore = prefs.getStringSet("highscore"+ord, highscore);
        List<String> list = new ArrayList<String>(highscore);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);

        listView.setAdapter(adapter);
        nytSpil.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        finish();

    }
}