package model;

import controller.MetroCardOverviewPaneController;
import controller.MetroStationViewController;
import controller.MetroTicketViewController;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroFacade implements Subject{

    private LoadSaveStrategyFactory loadSaveStrategyFactory;
    private LoadSaveStrategy loadSaveStrategy;
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

        System.out.println("voor metrocarddatabase");
        metroCardDatabase = new MetrocardDatabase();
        System.out.println("voor loadsavefactory");
        loadSaveStrategyFactory = new LoadSaveStrategyFactory();
        //System.out.println("voor loadsavestrategy");
        //loadSaveStrategy = new MetrocardsTekstLoadSaveStrategy();
        //metroCardDatabase.setLoadSaveStrategy(loadSaveStrategy);
        System.out.println(MetroEventsEnum.values());
        for(MetroEventsEnum metroEventsEnum : MetroEventsEnum.values()){
            System.out.println(metroEventsEnum);
            observers.put(metroEventsEnum, new ArrayList<Observer>());
        }
        addHardCodedObservers();
        System.out.println("hardcoded");

    }

    @Override
    public void addObserver(MetroEventsEnum e, Observer o) {
        System.out.println(observers.keySet());
        System.out.println(e);
        System.out.println(o);
        observers.get(e).add(o);
    }

    public void addHardCodedObservers(){
        observers.get(MetroEventsEnum.OPEN_METROSTATION).add(new MetroStationViewController(this));
        observers.get(MetroEventsEnum.OPEN_METROSTATION).add(new MetroTicketViewController(this));
        observers.get(MetroEventsEnum.OPEN_METROSTATION).add(new MetroCardOverviewPaneController(this));
    }

    @Override
    public void removeObserver(MetroEventsEnum e, Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(MetroEventsEnum e) {
        System.out.println("In notifyObservers");
        for(Observer obs : observers.get(e)){
            System.out.println(obs.getClass().getName());
            obs.update();
        }
    }

    public void openMetroStation(){
        System.out.println("In openMetroStation in MetroFacade");
        loadSaveStrategy = loadSaveStrategyFactory.createLoadSaveStrategy();
        metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyEnum.METROCARDS_TEKST);
        metroCardDatabase.load();
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
