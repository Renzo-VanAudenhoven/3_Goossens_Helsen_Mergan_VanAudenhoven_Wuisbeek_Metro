package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade metroFacade;
    private MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    public void setMetroCardOverviewPane(MetroCardOverviewPane metroCardOverviewPane) {
        this.metroCardOverviewPane = metroCardOverviewPane;
    }

    @Override
    public void update() {
        metroFacade.load();
        ArrayList<MetroCard> metrocards = metroFacade.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }

    public MetroFacade getMetroFacade() {
        return metroFacade;
    }
}
