package com.example.galgelej;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import Controller.GagleController;

public class GagleSpil extends AppCompatActivity {

    EditText charInput;
    Button bGuess;
    GagleController spil;
    TextView word;
    TextView status;
    TextView guessesWords;
//Aktivitet
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        spil = new GagleController();

        charInput = findViewById(R.id.inputString);
        bGuess = findViewById(R.id.buttonGuess);
        word = findViewById(R.id.textWord);
        guessesWords = findViewById(R.id.textGuesses);
        status = findViewById(R.id.textStatus);

        spil.nulstil();
        word.setText(spil.getSynligtOrd());
        status.setText("Indtast dit første bogstav");

        bGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = charInput.getText().toString();
                status.setText(spil.guess(input, guessesWords));
                word.setText(spil.getSynligtOrd());

                int wrongAnswer = spil.getAntalForkerteBogstaver();
                ((ImageView)findViewById(R.id.imageView)).setImageResource(wrongAnswer);

                String toast = "";
                switch (spil.spilStatus()){
                    case 0:
                        toast = "Tilykke du har vundet";
                        nulstilSpil();
                        break;
                    case 1:
                        toast = "Du har tabt. Ordet var: " + spil.getOrdet();
                        nulstilSpil();
                        break;
                    case 2:
                        toast = "Rigtigt Svar!";
                        break;
                    case 3:
                        toast = "Forkert svar";
                        break;
                }
                Toast.makeText(GagleSpil.this, toast, Toast.LENGTH_LONG).show();
            }
        });




    }

    private void nulstilSpil() {
        spil.nulstil();
        guessesWords.setText("");
        status.setText("Indtast dit første bogstav");
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.galge);
        word.setText(spil.getSynligtOrd());
    }
}
