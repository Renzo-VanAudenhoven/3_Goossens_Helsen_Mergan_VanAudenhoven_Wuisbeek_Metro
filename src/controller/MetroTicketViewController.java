package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroTicketView;

import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroFacade metroFacade;
    private MetroTicketView metroTicketView;

    public MetroTicketViewController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
    }

    public void setMetroTicketView(MetroTicketView metroTicketView) {
        this.metroTicketView = metroTicketView;
    }


    @Override
    public void update() {
        ArrayList<Integer> ids = metroFacade.getMetroCardIDList();
        metroTicketView.updateMetroCardIDList(ids);
    }

    public void newMetroCard() {
        metroFacade.newMetroCard();
    }
    public double getPrice(int id, int aantalRitten, boolean isStudent, boolean is64Plus) {
        return metroFacade.getPrice(id, aantalRitten, isStudent, is64Plus);
    }

    public String getPriceText(int id, boolean isStudent, boolean is64Plus) {
        return metroFacade.getPriceText(id, isStudent, is64Plus);
    }

}
