package com.example.galgelej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import Controller.GagleController;

public class GagleSpil extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener{


    TextView word, status, guessesWords;
    EditText charInput;
    Button bGuess;
    ImageView back;
    SharedPreferences prefs;
    Set<String> highscore;
    GagleController spil;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        Intent i = getIntent();

        String ord = i.getStringExtra("selection");
        System.out.println(ord);
        int selection = Integer.parseInt(ord);



        switch (selection){
            case 1:
                spil = new GagleController.GagleControllerBuilder().HentOrdPredifineret().build();
                break;
            case 2:
                spil = new GagleController.GagleControllerBuilder().HentOrdFraDr().build();
                break;
            case 3:
                String difficulty = i.getStringExtra("diff");
                spil = new GagleController.GagleControllerBuilder().HentOrdRegneark(difficulty).build();
                break;
        }

        highscore = new HashSet<String>();


        charInput = findViewById(R.id.inputString);
        bGuess = findViewById(R.id.buttonGuess);
        word = findViewById(R.id.textWord);
        guessesWords = findViewById(R.id.textGuesses);
        status = findViewById(R.id.textStatus);
        back = findViewById(R.id.back_img);

        spil.nulstil();
        word.setText(spil.getSynligtOrd());

        back.setOnClickListener(this);
        charInput.setOnEditorActionListener(this);
        bGuess.setOnClickListener(this);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        highscore = prefs.getStringSet("highscore"+spil.getOrdet(), highscore);




    }

    @Override
    public void onClick(View v) {

        if (v == bGuess) {
            String input = charInput.getText().toString();
            charInput.setText(null);

            status.setText(spil.guess(input, guessesWords));
            word.setText(spil.getSynligtOrd());

            int wrongAnswer = spil.getAntalForkerteBogstaver();
            ((ImageView) findViewById(R.id.imageView)).setImageResource(wrongAnswer);

            String toast = "";
            switch (spil.spilStatus()) {
                case 0:
                    highscore.add(spil.getTries());
                    spil.setTries(0);
                    prefs.edit().putStringSet("highscore"+spil.getOrdet(), highscore).apply();
                    gotoVundet();
                    nulstilSpil();
                    break;
                case 1:
                    gotoTabt();
                    spil.setTries(0);
                    nulstilSpil();
                    break;
                case 2:
                    toast = "Rigtigt Svar!";
                    Toast.makeText(GagleSpil.this, toast, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    toast = "Forkert svar";
                    Toast.makeText(GagleSpil.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }

        } else if (v == back){
            finish();
        }
    }

    private void nulstilSpil() {
        spil.nulstil();
        guessesWords.setText("");
        status.setText("Indtast dit første bogstav");
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.galge);
        word.setText(spil.getSynligtOrd());
    }

    private void gotoVundet(){
        Intent i = new Intent(this, Vundet.class);
        i.putExtra("ord", spil.getOrdet());
        startActivity(i);
    }

    private void gotoTabt(){
        Intent i = new Intent(this, Tabt.class);
        i.putExtra("ord", spil.getOrdet());
        startActivity(i);
    }




    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

            bGuess.performClick();
        }
        return false;
    }
}
