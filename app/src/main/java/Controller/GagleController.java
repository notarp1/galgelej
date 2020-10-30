package Controller;

import android.widget.TextView;

import com.example.galgelej.R;

import java.util.ArrayList;
import java.util.Random;

import DAL.GalgeData;

public class GagleController {

    GalgeData spil;
    int tries = 0;

    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    GalgeData data = new GalgeData();


    public GagleController(){
        hentPredefineretOrd();
    }

    public void nulstil(){
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        if (muligeOrd.isEmpty()) throw new IllegalStateException("Listen over ord er tom!");
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
        opdaterSynligtOrd();
    }

    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
    }


    public String guess(String character, TextView text){
        gaetBogstav(character);
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

    public void gaetBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 5) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public int getAntalForkerteBogstaver(){

        switch (antalForkerteBogstaver){
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

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
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

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public void hentPredefineretOrd(){
    muligeOrd.add("bil");
    muligeOrd.add("computer");
    muligeOrd.add("programmering");
    muligeOrd.add("motorvej");
    muligeOrd.add("busrute");
    muligeOrd.add("gangsti");
    muligeOrd.add("skovsnegl");
    muligeOrd.add("solsort");
    muligeOrd.add("nitten");
    }

    public void hentOrdFraDr() throws Exception {

        muligeOrd = data.hentOrdFraDr();
        nulstil();
    }

    public void  hentOrdFraRegneark(String difficulty) throws Exception {
        muligeOrd = data.hentOrdFraRegneark(difficulty);
        nulstil();

    }


}
