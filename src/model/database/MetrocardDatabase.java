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
    private LoadSaveStrategyFactory loadSaveFactory = new LoadSaveStrategyFactory();
    private File tekst = new File("src/bestanden/metrocards.txt");
    private File excel = new File("src/bestanden/metrocards.xls");

    public MetrocardDatabase(){
        load();
    }

    public void setLoadSaveStrategy(LoadSaveStrategyEnum strategy){
        /*
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/bestanden/settings.properties")) {
            prop.load(input);
            prop.setProperty("formaat", strategy.getStrategy());
            prop.store(new FileOutputStream("src/bestanden/settings.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public ArrayList<MetroCard> getMetroCardList(){
        ArrayList<MetroCard> metroCards = new ArrayList<MetroCard>();
        for (Map.Entry<Integer, MetroCard> entry : data.entrySet()) {
            metroCards.add(entry.getValue());
        }
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
        LoadSaveStrategy loadSaveStrategy = loadSaveFactory.createLoadSaveStrategy();
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
        LoadSaveStrategy loadSaveStrategy = loadSaveFactory.createLoadSaveStrategy();
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
}
