package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade metroFacade;
    private MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
        metroFacade.addObserver(MetroEventsEnum.CONFIRM_REQUEST, this);
        metroFacade.addObserver(MetroEventsEnum.SCAN_METROGATE, this);
        metroFacade.addObserver(MetroEventsEnum.CLOSE_METROSTATION, this);
    }

    public void setMetroCardOverviewPane(MetroCardOverviewPane metroCardOverviewPane) {
        this.metroCardOverviewPane = metroCardOverviewPane;
    }

    @Override
    public void update() {
        ArrayList<MetroCard> metrocards = metroFacade.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(metrocards);
    }
}
