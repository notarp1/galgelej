package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Indstillinger extends AppCompatActivity implements View.OnClickListener {

    Button clear, credits;
    ImageView goBack;
    SharedPreferences prefs;
    String toastHigh = "Alle highscores er nulstillet!";
    String toastCred = "Lavet af Christian Pone s195465";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout options = new LinearLayout(this);
        options.setOrientation(LinearLayout.VERTICAL);
        options.setBackgroundColor(Color.DKGRAY);

        goBack = new ImageView(this);
        goBack.setImageResource(R.drawable.abc_vector_test);
        goBack.setLayoutParams(new FrameLayout.LayoutParams(80, 80, Gravity.LEFT));

        clear = new Button(this);
        clear.setText("Ryd alle highscores");
        clear.setGravity(Gravity.CENTER);

        credits = new Button(this);
        credits.setText("credits");
        credits.setGravity(Gravity.CENTER);




        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        clear.setOnClickListener(this);
        credits.setOnClickListener(this);
        goBack.setOnClickListener(this);

        options.addView(goBack);
        options.addView(clear);
        options.addView(credits);

        setContentView(options);

    }

    @Override
    public void onClick(View v) {
        if(v == clear){
            prefs.edit().clear().apply();
            Toast.makeText(this, toastHigh, Toast.LENGTH_SHORT).show();
        } else if (v == credits){
            Toast.makeText(this, toastCred, Toast.LENGTH_LONG).show();
        } else if (v == goBack){
            finish();
        }

    }
}