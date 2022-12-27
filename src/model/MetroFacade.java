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

    public MetroFacade(){
        metroCardDatabase = new MetrocardDatabase();
        loadSaveStrategyFactory = new LoadSaveStrategyFactory();
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
        observers.get(e).remove(o);
    }

    @Override
    public void notifyObservers(MetroEventsEnum e) {
        for(Observer obs : observers.get(e)){
            System.out.println(obs.getClass().getName());
            obs.update();
        }
    }

    public void openMetroStation(){
        loadSaveStrategyFactory.createLoadSaveStrategy();
    }

    public ArrayList<MetroCard> getMetroCardList(){
        return null;
    }

    public ArrayList<Integer> getMetroCardIDList(){
        return null;
    }

    public void load() {
        metroCardDatabase.load();
    }
}
