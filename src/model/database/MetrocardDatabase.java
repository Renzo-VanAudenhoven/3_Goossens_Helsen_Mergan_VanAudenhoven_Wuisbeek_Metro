package model.database;

import jxl.read.biff.BiffException;
import model.MetroCard;
import excel.ExcelPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;
import model.database.utilities.*;

public class MetrocardDatabase {
    private List<MetroCard> data1;
    private Map<Integer, MetroCard> data;
    private LoadSaveStrategyFactory loadSaveFactory = new LoadSaveStrategyFactory();
    private File tekst = new File("src/bestanden/metrocards.txt");
    private File excel = new File("src/bestanden/metrocards.xls");

    public MetrocardDatabase(){

        //data = new ArrayList<MetroCard>();
        //data.add(new MetroCard(1,"10#2022",10,5));
        //data.add(new MetroCard(2,"11#2022",1,10));
        //data.add(new MetroCard(3,"12#2022",100,900));
        try {

            data = loadSaveFactory.createLoadSaveStrategy(LoadSaveStrategyEnum.METROCARDS_TEKST).load(tekst);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            data = loadSaveFactory.createLoadSaveStrategy().load(tekst);
//        } catch (IOException | BiffException e) {
//            e.printStackTrace();
//        }
    }

    public ArrayList<MetroCard> getMetroCards(){
        ArrayList<MetroCard> metroCards = new ArrayList<MetroCard>();
        for (Map.Entry<Integer, MetroCard> entry : data.entrySet()) {
            metroCards.add(entry.getValue());
        }
        return metroCards;
    }

    public int getAantalMetroCards(){
        return data.size()-1;
    }

    public void addDummyMetroCard(){
        MetroCard metroCard = new MetroCard(5,"1#2022",5,5);
        //data.add(metroCard);
    }
}
