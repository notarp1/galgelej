package DAL;

import java.io.IOException;
import java.util.ArrayList;

public interface IGalgeData {

    String hentUrl(String url) throws IOException;
    ArrayList<String> hentOrdFraDr() throws Exception;
    ArrayList<String> hentOrdFraRegneark(String svaerhedsgrader) throws Exception;

}
