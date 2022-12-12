package model.database;

import model.MetroCard;

import java.util.ArrayList;
import java.util.List;

public class MetrocardDatabase {
    private List<MetroCard> data;

    public MetrocardDatabase(){

        data = new ArrayList<MetroCard>();
        data.add(new MetroCard(1,"10#2022",10,5));
        data.add(new MetroCard(2,"11#2022",1,10));
        data.add(new MetroCard(3,"12#2022",100,900));


    }

    public List<MetroCard> getMetroCards(){
        return data;
    }

    public int getAantalMetroCards(){
        return data.size()-1;
    }

    public void addDummyMetroCard(){
        MetroCard metroCard = new MetroCard(5,"1#2022",5,5);
        data.add(metroCard);
    }
}
