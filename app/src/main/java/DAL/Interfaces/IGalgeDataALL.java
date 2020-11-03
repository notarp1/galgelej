package DAL.Interfaces;

import java.io.IOException;
import java.util.ArrayList;

public interface IGalgeDataALL {

    String hentUrl(String url) throws IOException;
    ArrayList<String> hentOrdFraDr() throws Exception;
    ArrayList<String> hentOrdFraRegneark(String svaerhedsgrader) throws Exception;
    ArrayList<String> hentPredefineretOrd();

}
