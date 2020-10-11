package com.example.galgelej;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Menu_Frag extends Fragment implements View.OnClickListener {

    Button spilKnap, indstillingerKnap;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.menu_knapper, container, false);

        spilKnap = rod.findViewById(R.id.knap1);
        spilKnap.setText("Spil");

        indstillingerKnap = rod.findViewById(R.id.knap2);
        indstillingerKnap.setText("Indstillinger");




        return rod;
    }

    @Override
    public void onClick(View view) {

    }
}
