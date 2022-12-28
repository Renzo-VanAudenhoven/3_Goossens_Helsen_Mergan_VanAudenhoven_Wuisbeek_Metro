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
    }

    public void setMetroTicketView(MetroTicketView metroTicketView) {
        this.metroTicketView = metroTicketView;
    }


    @Override
    public void update() {
        ArrayList<Integer> ids = metroFacade.getMetroCardIDList();
        metroTicketView.updateMetroCardIDList(ids);
    }
}
