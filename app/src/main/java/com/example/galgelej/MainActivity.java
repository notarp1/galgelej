package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView header;
    Button play, options;
    View filler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout mainMenu = new LinearLayout(this);
        mainMenu.setOrientation(LinearLayout.VERTICAL);


        mainMenu.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(11111);
        drawable.setSize(10, 10);
        mainMenu.setDividerDrawable(drawable);

        mainMenu.setBackgroundColor(Color.DKGRAY);


        header = new TextView(this);
        header.setText("Velkommen til gagleleg!");
        header.setGravity(Gravity.CENTER);
        header.setTextSize(40);



        play = new Button(this);
        play.setGravity(Gravity.CENTER);
        play.setText("Spil");



        options = new Button(this);
        options.setText("Indstillinger");
        options.setGravity(Gravity.CENTER);

        mainMenu.addView(header);
        mainMenu.addView(play);
        mainMenu.addView(options);





        play.setOnClickListener(this);
        options.setOnClickListener(this);

        setContentView(mainMenu);
    }

    public void onClick(View view) {
        if(view == play) {
            Intent gaglespil = new Intent(this, GagleSpil.class);
            startActivity(gaglespil);
        } else if (view == options){
            Intent options = new Intent(this, Indstillinger.class);
            startActivity(options);
        }
    }
}