package Controller;

import android.os.SystemClock;
import android.widget.TextView;

import com.example.galgelej.R;

import java.util.ArrayList;
import java.util.Random;

import DAL.GalgeDataALL;

public class GagleController implements IGalgeController{

    private  ArrayList<String> muligeOrd;
    private String ordet, synligtOrd;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt, spilletErVundet, spilletErTabt;

    int tries = 0;
    String val;




    private GagleController(GagleControllerBuilder builder){

        this.muligeOrd = builder.muligeOrd;

        while (muligeOrd.isEmpty()) SystemClock.sleep(1);

        nulstil();

    }





    public static class GagleControllerBuilder {

        GalgeDataALL data = new GalgeDataALL();;

        private ArrayList<String> muligeOrd = new ArrayList<>();


        public GagleControllerBuilder(){
            if(!muligeOrd.isEmpty()){
                muligeOrd = new ArrayList<>();
            }
        }

        public GagleControllerBuilder reset(){
            muligeOrd = new ArrayList<>();
            return this;
        }

        public GagleControllerBuilder HentOrdPredifineret(){

         muligeOrd = data.hentPredefineretOrd();
         return this;

        }

        public GagleControllerBuilder HentOrdFraDr(){

            Runnable r = new Runnable() {
                public void run() {
                    try {
                        muligeOrd = data.hentOrdFraDr();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(r).start();
            while (muligeOrd.isEmpty())SystemClock.sleep(1);

            return this;
        }

        public GagleControllerBuilder HentOrdRegneark(final String difficulty){
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        muligeOrd = data.hentOrdFraRegneark(difficulty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(r).start();

            while (muligeOrd.isEmpty())SystemClock.sleep(1);
            return this;
        }

        public  GagleController build(){
            GagleController  galgeControler = new GagleController(this);
            return galgeControler;
        }

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

    public void opdaterSynligtOrd() {
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

    public String getTries(){
        val = String.valueOf(tries);
        return val;
    }

    public void setTries(int tries) {
        this.tries = tries;
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
            tries += 1;
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

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
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





}
