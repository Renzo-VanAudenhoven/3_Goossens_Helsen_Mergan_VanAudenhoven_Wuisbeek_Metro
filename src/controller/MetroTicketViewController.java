package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroTicketView;

public class MetroTicketViewController implements Observer {
    private MetroFacade metroFacade;
    private MetroTicketView metroTicketView;

    public MetroTicketViewController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
    }


    @Override
    public void update() {
    }
}
