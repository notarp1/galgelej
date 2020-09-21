package com.example.galgelej;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Controller.GagleController;

public class GagleSpil extends AppCompatActivity {

    EditText charInput;
    Button bGuess;
    GagleController spil;
    TextView word;
    TextView status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        spil = new GagleController();

        charInput = findViewById(R.id.inputString);
        bGuess = findViewById(R.id.buttonGuess);
        word = findViewById(R.id.textWord);
        status = findViewById(R.id.textStatus);

        spil.nulstil();
        word.setText(spil.getSynligtOrd());
        status.setText("Indtast dit første bogstav");


        bGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = charInput.getText().toString();
                status.setText(spil.guess(input));
                word.setText(spil.getSynligtOrd());
                System.out.println(spil.getAntalForkerteBogstaver());
            }
        });




    }
}
