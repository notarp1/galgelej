package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class Vundet extends AppCompatActivity implements View.OnClickListener {
    TextView test;
    TextView ordView;
    Button nytSpil;
    String noob;

    SharedPreferences prefs;
    Set<String> highscore = new HashSet<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);

        Intent i = getIntent();
        String ord = i.getStringExtra("ord");

        ordView = findViewById(R.id.textView_ord);
        nytSpil = findViewById(R.id.nytSpil_btn);
        test = findViewById(R.id.textView);

        ordView.setText(ord);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        noob = prefs.getString("ord", "null");

        nytSpil.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        test.setText(noob);

    }
}