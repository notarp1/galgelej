package Controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.galgelej.R;

import java.util.ArrayList;

import DTO.Galgelogik;

public class GagleController {


    Galgelogik spil;



    private ArrayList<String> brugteBogstaver;

    public GagleController(){
        spil = new Galgelogik();

    }

    public void nulstil(){
        spil.nulstil();

    }

    public String guess(String character, TextView text){
        spil.gaetBogstav(character);
        brugteBogstaver = getBrugteBogstaver();
        String usedChars = " ";
        for(int i = 0; i<brugteBogstaver.size(); i++){
            usedChars += brugteBogstaver.get(i) + "-";
        }
        text.setText(usedChars);

        if(erSidsteBogstavKorrekt()){
            return "Bogstavet var korrekt: '" + character + "'";
        } else {
            return "Bogstavet var forkert: '" + character + "'";
        }
    }

    public String getSynligtOrd(){
        return spil.getSynligtOrd();
    }
    public int getAntalForkerteBogstaver(){

        switch (spil.getAntalForkerteBogstaver()){
            case 1:
                return R.drawable.forkert1;
            case 2:
                return R.drawable.forkert2;
            case 3:
                return R.drawable.forkert3;
            case 4:
                return R.drawable.forkert4;
            case 5:
                return R.drawable.forkert5;
            case 6:
                return R.drawable.forkert6;
            default:
                return R.drawable.galge;
        }

    }

    public boolean erSidsteBogstavKorrekt(){
        return spil.erSidsteBogstavKorrekt();
    }

    public int spilStatus(){
        if(erSpilletSlut()){
            if(erSpilletVundet()){
                return 0;
            } else {
                return 1;}
        }else if(erSidsteBogstavKorrekt()) {
            return 2;
        } else return 3;
    }

    public boolean erSpilletSlut(){
        return spil.erSpilletSlut();
    }

    public boolean erSpilletVundet() {
        return spil.erSpilletVundet();
    }

    public boolean erSpilletTabt() {
        return spil.erSpilletTabt();
    }

    public ArrayList<String> getBrugteBogstaver() {
        return spil.getBrugteBogstaver();
    }

    public String getOrdet(){

        return spil.getOrdet();
    }

}
