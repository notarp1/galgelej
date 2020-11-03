package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Ordvalg extends AppCompatActivity implements View.OnClickListener {

    Button pre, dr, ark;
    EditText diff;
    String input;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordvalg);

        pre = findViewById(R.id.button_pre);
        ark = findViewById(R.id.button_ark);
        dr = findViewById(R.id.button_dr);
        diff = findViewById(R.id.edit_diff);
        back = findViewById(R.id.back_img2);


        back.setOnClickListener(this);
        pre.setOnClickListener(this);
        ark.setOnClickListener(this);
        dr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent gaglespil = new Intent(this, GagleSpil.class);
        boolean range = true;




        if(v == back){
            finish();
        } else if(v == pre){
            gaglespil.putExtra("selection", "1");

        }else if (v == dr){
            gaglespil.putExtra("selection", "2");
            if(isOnline()){
                Toast.makeText(Ordvalg.this, "Du er ikke forbundet til internettet!", Toast.LENGTH_SHORT).show();
                range = false;
            }
        }else if (v == ark){ //Google Ark
            int i = 5;
            input = diff.getText().toString();

            try {
                i = Integer.parseInt(input);
                if(i<1 || i>4){
                    range = false;
                    Toast.makeText(Ordvalg.this, "Sværhedsgrad går kun fra 1-4!", Toast.LENGTH_SHORT).show(); }
                gaglespil.putExtra("selection", "3");

            }catch (NumberFormatException e){
                range = false;
            }if(isOnline()){
                Toast.makeText(Ordvalg.this, "Du er ikke forbundet til internettet!", Toast.LENGTH_SHORT).show();
                range = false;
            }
        }

       if(range) {
           gaglespil.putExtra("diff", input);
           startActivity(gaglespil);
       }
    }

    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return false;
        }
        else{
            return true;
        }
    }
}