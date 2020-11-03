package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView header;
    Button play, options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        play = findViewById(R.id.btn_play);
        options = findViewById(R.id.btn_opt);



        play.setOnClickListener(this);
        options.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view == play) {
            Intent ordvalg = new Intent(this, Ordvalg.class);
            startActivity(ordvalg);
        } else if (view == options){
            Intent options = new Intent(this, Indstillinger.class);
            startActivity(options);
        }
    }
}