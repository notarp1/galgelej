package Controller;

import java.util.ArrayList;

public interface IGalgeController {


    void nulstil();
    void opdaterSynligtOrd();
    void gaetBogstav(String bogstav);
    int getAntalForkerteBogstaver();
    int spilStatus();
    String getSynligtOrd();
    String getOrdet();
    ArrayList<String> getBrugteBogstaver();
    boolean erSidsteBogstavKorrekt();
    boolean erSpilletSlut();
    boolean erSpilletVundet();
    boolean erSpilletTabt();




}
