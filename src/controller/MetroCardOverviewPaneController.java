package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade metroFacade;
    private MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
    }
}
