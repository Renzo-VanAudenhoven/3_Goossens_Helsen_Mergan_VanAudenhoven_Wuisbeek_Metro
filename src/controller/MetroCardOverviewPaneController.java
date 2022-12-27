package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade metroFacade;
    private MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();

    public MetroCardOverviewPaneController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
        ArrayList<MetroCard> metrocards = metroFacade.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }
}
