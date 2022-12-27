package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade facade;
    private MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade facade) {
        metroCardOverviewPane = new MetroCardOverviewPane();
        this.facade = facade;
        facade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
    }

    @Override
    public void update() {
        ArrayList<MetroCard> metrocards = facade.getMetroCardList();
        System.out.println("in de update van metrocardoverviewpanecontroller");
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }
}