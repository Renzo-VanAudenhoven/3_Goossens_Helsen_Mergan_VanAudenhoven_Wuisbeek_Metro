package model;

import model.TicketPriceDecorator.TicketPrice;
import model.TicketPriceDecorator.TicketPriceFactory;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class MetroFacade implements Subject{

    private LoadSaveStrategyFactory loadSaveStrategyFactory;
    private MetrocardDatabase metroCardDatabase;
    private Map<MetroEventsEnum, List<Observer>> observers = new HashMap<>();
    private TicketPriceFactory ticketPriceFactory;
    private MetroStation metroStation;

    public MetroFacade(){
        loadSaveStrategyFactory = new LoadSaveStrategyFactory();
        metroCardDatabase = new MetrocardDatabase(loadSaveStrategyFactory);
        ticketPriceFactory = new TicketPriceFactory();
        metroStation = new MetroStation();

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
            obs.update();
        }
    }

    public void openMetroStation(){
        metroCardDatabase.setLoadSaveStrategy(loadSaveStrategyFactory.createLoadSaveStrategy());
        metroCardDatabase.load();
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public void closeMetroStation() {
        metroCardDatabase.save();
        metroCardDatabase.clearData();
        notifyObservers(MetroEventsEnum.CLOSE_METROSTATION);
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

    public MetrocardDatabase getMetroCardDatabase() {
        return metroCardDatabase;
    }

    public void newMetroCard() {
        metroCardDatabase.newMetroCard();
        notifyObservers(MetroEventsEnum.BUY_METROCARD);
    }

    public double getPrice(int id, int aantalRitten, boolean isStudent, boolean is64Plus) {
        MetroCard metroCard = metroCardDatabase.getMetroCard(id);
        TicketPrice ticketPrice = ticketPriceFactory.createTicketPrice(is64Plus, isStudent, metroCard);
        return ticketPrice.getPrice() * aantalRitten;
    }

    public String getPriceText(int id, boolean isStudent, boolean is64Plus) {
        MetroCard metroCard = metroCardDatabase.getMetroCard(id);
        TicketPrice ticketPrice = ticketPriceFactory.createTicketPrice(is64Plus, isStudent, metroCard);
        return ticketPrice.getPriceText();
    }

    public void confirmRequest(int id, int aantalRitten) {
        metroCardDatabase.getMetroCard(id).addRitten(aantalRitten);
        notifyObservers(MetroEventsEnum.CONFIRM_REQUEST);
    }

    public void scanMetroGate(int metroCardID, int gateID){
        MetroCard metroCard = metroCardDatabase.getMetroCard(metroCardID);
            if (!metroCard.isExpired()){
                metroCard.useRit();
                metroStation.scanMetroGate(gateID);
                notifyObservers(MetroEventsEnum.SCAN_METROGATE);
            }
//        metroCard.isExpired();
//        metroCard.hasRittenBeschikbaar();
//        metroStation.scanMetroGate(gateID);
//        notifyObservers(MetroEventsEnum.SCAN_METROGATE);

    }

    public void activateGate(int gateid){
        metroStation.activateGate(gateid);
    }

    public void deactivateGate(int gateid) {
        metroStation.deactivateGate(gateid);
    }

    public void walkThroughGate(int gateIndex) {
        metroStation.walkThroughGate(gateIndex);
    }

    public int getAmountOfTickets() {
        return metroCardDatabase.getAmountOfTickets();
    }
}
