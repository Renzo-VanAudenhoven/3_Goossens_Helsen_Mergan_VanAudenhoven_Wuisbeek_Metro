package model.database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;
import excel.ExcelPlugin;


import java.io.*;
import java.util.*;

import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;
import model.database.utilities.*;

public class MetrocardDatabase {
    private Map<Integer, MetroCard> data;
    private LoadSaveStrategyFactory loadSaveFactory;
    private LoadSaveStrategy loadSaveStrategy;
    private File tekst = new File("src/bestanden/metrocards.txt");
    private File excel = new File("src/bestanden/metrocards.xls");

    public MetrocardDatabase(LoadSaveStrategyFactory loadSaveFactory){
        this.loadSaveFactory = loadSaveFactory;
    }

    public void setLoadSaveStrategy(LoadSaveStrategy strategy){
        loadSaveStrategy = strategy;
    }

    public ArrayList<MetroCard> getMetroCardList(){
        ArrayList<MetroCard> metroCards = new ArrayList<MetroCard>();
        for (Map.Entry<Integer, MetroCard> entry : data.entrySet()) {
            metroCards.add(entry.getValue());
        }
        Collections.sort(metroCards, new Comparator<MetroCard>() {
            @Override
            public int compare(MetroCard o1, MetroCard o2) {
                return o1.getKaartID() - o2.getKaartID();
            }
        });

        return metroCards;
    }

    public ArrayList<Integer> getMetroCardIDList(){
        ArrayList<Integer> metroCardIDs = new ArrayList<Integer>();
        for (Map.Entry<Integer, MetroCard> entry : data.entrySet()) {
            metroCardIDs.add(entry.getKey());
        }
        return metroCardIDs;
    }

    public void load(){
        if (loadSaveStrategy.getClass().getSimpleName().equals("MetrocardsTekstLoadSaveStrategy")){
            try {
                data = loadSaveStrategy.load(tekst);
            } catch (IOException | BiffException e) {
                e.printStackTrace();
            }
        } else if (loadSaveStrategy.getClass().getSimpleName().equals("MetrocardsExcelLoadSaveStrategy")){
            try {
                data = loadSaveStrategy.load(excel);
            } catch (IOException | BiffException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(){
        if (loadSaveStrategy.getClass().getSimpleName().equals("MetrocardsTekstLoadSaveStrategy")){
            try {
                loadSaveStrategy.save(data,tekst);
            } catch (IOException | BiffException | WriteException e) {
                e.printStackTrace();
            }
        } else if (loadSaveStrategy.getClass().getSimpleName().equals("MetrocardsExcelLoadSaveStrategy")){
            try {
                loadSaveStrategy.save(data,excel);
            } catch (IOException | BiffException | WriteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getHighestID(){
        int highestID = 0;
        Object[] list = data.keySet().toArray();
        for (Object id : list){
            int intId = Integer.parseInt(id.toString());
            if (intId > highestID) {
                highestID = intId;
            }
        }
        return highestID;
    }

    public String getTodayFormattedDate(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        return month + "#" + year;
    }


    public void newMetroCard(){
        MetroCard metroCard = new MetroCard(getHighestID()+1, getTodayFormattedDate(), 2, 0);
        data.put(metroCard.getKaartID(), metroCard);
    }

    public MetroCard getMetroCard(int id){
        ArrayList<MetroCard> metroCards = getMetroCardList();
        for (MetroCard c : metroCards){
            if (c.getKaartID() == id){
                return c;
            }
        }
        return null;
    }



}
