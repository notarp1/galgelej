
package com.example.galgelej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bSpil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = new Menu_Frag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentIndhold, fragment)  // tom container i layout
                    .commit();
        }

       /* bSpil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPlay();
            }
        });*/
    }


    private void gotoPlay(){
        Intent gaglespil = new Intent(this, GagleSpil.class);
        startActivity(gaglespil);
    }
}