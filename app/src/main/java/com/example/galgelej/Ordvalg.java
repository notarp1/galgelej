package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Ordvalg extends AppCompatActivity implements View.OnClickListener {

    Button pre, dr, ark;
    EditText diff;
    String input;
    ImageView back;
    Spinner dropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordvalg);

        pre = findViewById(R.id.button_pre);
        ark = findViewById(R.id.button_ark);
        dr = findViewById(R.id.button_dr);
        dropDown = findViewById(R.id.edit_diff);
        back = findViewById(R.id.back_img22);


        ArrayAdapter<CharSequence> dropDownAdapter = ArrayAdapter.createFromResource(this, R.array.createDropDown, R.layout.support_simple_spinner_dropdown_item);
        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(dropDownAdapter);
        dropDown.setPrompt("Vælg sværhedsgrad");

        back.setOnClickListener(this);
        pre.setOnClickListener(this);
        ark.setOnClickListener(this);
        dr.setOnClickListener(this);


        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                System.out.println(parent.getItemAtPosition(position));
                //set the newly selected type to local string
                input = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    public void onClick(View v) {

        boolean range = true;
        if(v == back){
            finish();
            range = false;
        }

        Intent gaglespil = new Intent(this, GagleSpil.class);


        if(v == pre){
            gaglespil.putExtra("selection", "1");

        }else if (v == dr){
            gaglespil.putExtra("selection", "2");
            if(isOnline()){
                Toast.makeText(Ordvalg.this, "Du er ikke forbundet til internettet!", Toast.LENGTH_SHORT).show();
                range = false;
            }

        }else if (v == ark){ //Google Ark
            gaglespil.putExtra("selection", "3");

            if(isOnline()){
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