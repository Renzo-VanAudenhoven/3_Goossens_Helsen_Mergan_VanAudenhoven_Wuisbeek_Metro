package model;

public class MetroCard {
    private int kaartID;
    private String aankoopdatum;
    private int ritten_beschikbaar;
    private int ritten_verbruikt;

    public MetroCard(int kaartID, String aankoopdatum, int ritten_beschikbaar, int ritten_verbruikt){
        setKaartID(kaartID);
        setAankoopdatum(aankoopdatum);
        setRittenBeschikbaar(ritten_beschikbaar);
        setRittenVerbruikt(ritten_verbruikt);
    }

    public int getKaartID() {
        return kaartID;
    }

    public void setKaartID(int kaartID) {
        this.kaartID = kaartID;
    }

    public String getAankoopdatum() {
        return aankoopdatum;
    }

    public void setAankoopdatum(String aankoopdatum) {
        this.aankoopdatum = aankoopdatum;
    }

    public int getRittenBeschikbaar() {
        return ritten_beschikbaar;
    }

    public void setRittenBeschikbaar(int ritten_beschikbaar) {
        this.ritten_beschikbaar = ritten_beschikbaar;
    }

    public int getRittenVerbruikt() {
        return ritten_verbruikt;
    }

    public void setRittenVerbruikt(int ritten_verbruikt) {
        this.ritten_verbruikt = ritten_verbruikt;
    }
}
