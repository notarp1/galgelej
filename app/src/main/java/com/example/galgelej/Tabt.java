package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tabt extends AppCompatActivity implements View.OnClickListener {

    TextView ordView;
    Button nytSpil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);

        Intent i = getIntent();
        String ord = i.getStringExtra("ord");

        ordView = findViewById(R.id.textView_ord);
        nytSpil = findViewById(R.id.nytSpil_btn);

        ordView.setText(ord);

        nytSpil.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        finish();

    }
}