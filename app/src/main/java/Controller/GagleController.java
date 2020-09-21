package Controller;

import DTO.Galgelogik;

public class GagleController {

    Galgelogik spil;

    public GagleController(){
        spil = new Galgelogik();

    }

    public void nulstil(){
        spil.nulstil();
    }

    public String guess(String character){
        spil.gaetBogstav(character);
        if(erSidsteBogstavKorrekt()){
            return "Bogstavet var korrekt: " + character + "'";
        } else {
            return "Bogstavet var forkert: '" + character + "'";
        }
    }

    public String getSynligtOrd(){
        return spil.getSynligtOrd();
    }
    public String getAntalForkerteBogstaver(){

        switch (spil.getAntalForkerteBogstaver()){
            case 0:
                return  "galge.png";

            case 1:
                return "forkert1.png";
                break;
            case 2:
                return "forkert2.png";
                break;
            case 3:
                return "forkert3.png";
                break;
            case 4:
                return "forkert4.png";
                break;
            case 5:
                return "forkert5.png";
                break;
            case 6:
                return "forkert6.png";
                break;
            default:
                return "galge.png";
        }

    }
    public boolean erSidsteBogstavKorrekt(){
        return spil.erSidsteBogstavKorrekt();
    }


}
