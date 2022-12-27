package model;

import controller.MetroCardOverviewPaneController;
import controller.MetroStationViewController;
import controller.MetroTicketViewController;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroFacade implements Subject{

    private LoadSaveStrategyFactory loadSaveStrategyFactory;
    private MetrocardDatabase metroCardDatabase;
    private Map<MetroEventsEnum, List<Observer>> observers = new HashMap<>();
    /*
    public MetroFacade(MetrocardDatabase metroCardDatabase){
        this.metroCardDatabase = metroCardDatabase;
        metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyEnum.METROCARDS_TEKST);
        metroCardDatabase.load();
        for(MetroEventsEnum metroEventsEnum : MetroEventsEnum.values()){
            observers.put(metroEventsEnum, new ArrayList<Observer>());
        }
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }*/

    /*ik ben even met chatgpt iets aan het proberen, maar ik wil de oude code nog niet wegdoen*/
    public MetroFacade(){
        metroCardDatabase = new MetrocardDatabase();
        metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyEnum.METROCARDS_TEKST);
        metroCardDatabase.load();
        for(MetroEventsEnum metroEventsEnum : MetroEventsEnum.values()){
            observers.put(metroEventsEnum, new ArrayList<Observer>());
        }
    }

    @Override
    public void addObserver(MetroEventsEnum e, Observer o) {
        observers.get(e).add(o);
    }

    @Override
    public void removeObserver(MetroEventsEnum e, Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(MetroEventsEnum e) {
        for(Observer obs : observers.get(e)){
            obs.update();
        }
    }

    public void openMetroStation(){
        loadSaveStrategyFactory.createLoadSaveStrategy();
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public ArrayList<MetroCard> getMetroCardList(){
        return metroCardDatabase.getMetroCardList();
    }

    public ArrayList<Integer> getMetroCardIDList(){
        return metroCardDatabase.getMetroCardIDList();
    }

    public void load() {
        metroCardDatabase.load();
    }
}
