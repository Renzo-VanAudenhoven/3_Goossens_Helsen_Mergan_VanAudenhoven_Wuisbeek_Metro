package model;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MetroCard {
    private int kaartID;
    private String aankoopdatum;
    private int rittenBeschikbaar;
    private int rittenVerbruikt;

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
        return rittenBeschikbaar;
    }

    public void setRittenBeschikbaar(int ritten_beschikbaar) {
        this.rittenBeschikbaar = ritten_beschikbaar;
    }

    public int getRittenVerbruikt() {
        return rittenVerbruikt;
    }

    public void setRittenVerbruikt(int ritten_verbruikt) {
        this.rittenVerbruikt = ritten_verbruikt;
    }

    public String toString(){
    	return "KaartID: " + kaartID + " Aankoopdatum: " + aankoopdatum + " Ritten beschikbaar: " + rittenBeschikbaar + " Ritten verbruikt: " + rittenVerbruikt;
    }

    public void addRitten(int aantalRitten) {
        rittenBeschikbaar += aantalRitten;
    }

    public boolean isExpired() {
        Calendar currentDate = Calendar.getInstance();
        Calendar aankoopDate = Calendar.getInstance();

        int month = Integer.parseInt(this.aankoopdatum.split("#")[0]);
        int year = Integer.parseInt(this.aankoopdatum.split("#")[1]);
        aankoopDate.set(Calendar.MONTH, month);
        aankoopDate.set(Calendar.YEAR, year);

        if (aankoopDate.before(currentDate)) {
            long diff = currentDate.getTimeInMillis() - aankoopDate.getTimeInMillis();
            long days = TimeUnit.MILLISECONDS.toDays(diff);
            if (days > 365) {
                throw new IllegalArgumentException("Card " + this.kaartID + " is expired!");
            }
        }
        return false;
    }

    public boolean hasRittenBeschikbaar(){
        if (rittenBeschikbaar<0){
            throw new IllegalArgumentException("Card " + this.kaartID + " has no rides left");
        }
        return rittenBeschikbaar > 0;
    }

}
