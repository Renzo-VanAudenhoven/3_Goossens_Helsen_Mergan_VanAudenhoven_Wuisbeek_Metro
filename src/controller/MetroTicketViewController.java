package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroTicketView;

import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroFacade facade;
    private MetroTicketView metroTicketView = new MetroTicketView();

    public MetroTicketViewController() {
        facade = new MetroFacade();
        facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = facade.getMetroCardIDList();
        metroTicketView.updateMetroCardIDList(ids);
    }
}
