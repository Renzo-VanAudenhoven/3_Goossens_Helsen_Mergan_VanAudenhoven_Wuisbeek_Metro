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
    private Map<MetroEventsEnum, List<Observer>> observersMap = new HashMap<>();

    public MetroFacade(MetrocardDatabase metroCardDatabase){
        this.metroCardDatabase = metroCardDatabase;
        metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyEnum.METROCARDS_TEKST);
        metroCardDatabase.load();
        for(MetroEventsEnum metroEventsEnum : MetroEventsEnum.values()){
            observersMap.put(metroEventsEnum, new ArrayList<Observer>());
        }
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
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
