package DAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import DAL.Interfaces.IGalgeDataALL;

public class GalgeData implements IGalgeDataALL {
  /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
  ArrayList<String> muligeOrd = new ArrayList<String>();


  public GalgeData() {}


  public String hentUrl(String url) throws IOException {
    System.out.println("Henter data fra " + url);
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String linje = br.readLine();
    while (linje != null) {
      sb.append(linje + "\n");
      linje = br.readLine();
    }
    return sb.toString();
  }


  /**
   * Hent ord fra DRs forside (https://dr.dk)
   */
  public ArrayList<String> hentOrdFraDr() throws Exception {
    String data = hentUrl("https://dr.dk");
    //System.out.println("data = " + data);

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("&#198;", "æ"). // erstat HTML-tegn
            replaceAll("&#230;", "æ"). // erstat HTML-tegn
            replaceAll("&#216;", "ø"). // erstat HTML-tegn
            replaceAll("&#248;", "ø"). // erstat HTML-tegn
            replaceAll("&oslash;", "ø"). // erstat HTML-tegn
            replaceAll("&#229;", "å"). // erstat HTML-tegn
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    System.out.println("data = " + Arrays.asList(data.split("\\s+")));
    muligeOrd.clear();
    muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("muligeOrd = " + muligeOrd);
    return muligeOrd;

  }


  /**
   * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
   * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
   * @param svaerhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
   * @throws Exception
   */

  public ArrayList<String> hentOrdFraRegneark(String svaerhedsgrader) throws Exception {
    String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";

    // System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/"+id+"/edit?usp=sharing");

    String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
    int linjeNr = 0;

    muligeOrd.clear();
    for (String linje : data.split("\n")) {
      //if (linjeNr<20) System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
      if (linjeNr++ < 1 ) continue; // Spring første linje med kolonnenavnene over
      String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
      String svaerhedsgrad = felter[0].trim();
      String ordet = felter[1].trim().toLowerCase();
      if (svaerhedsgrad.isEmpty() || ordet.isEmpty()) continue; // spring over linjer med tomme ord
      if (!svaerhedsgrader.contains(svaerhedsgrad)) continue; // filtrér på sværhedsgrader
      System.out.println("Tilføjer "+ordet+", der har sværhedsgrad "+svaerhedsgrad);
      muligeOrd.add(ordet);
    }

    System.out.println("muligeOrd = " + muligeOrd);
    return muligeOrd;
  }

  public ArrayList<String> hentPredefineretOrd(){

    muligeOrd.add("bil");
    muligeOrd.add("computer");
    muligeOrd.add("programmering");
    muligeOrd.add("motorvej");
    muligeOrd.add("busrute");
    muligeOrd.add("gangsti");
    muligeOrd.add("skovsnegl");
    muligeOrd.add("solsort");
    muligeOrd.add("nitten");
    System.out.println("hej");
    return muligeOrd;
  }
}
