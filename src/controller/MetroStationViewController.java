package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

public class MetroStationViewController implements Observer {
    private MetroFacade metroFacade;
    private MetroStationView metroStationView;

    public MetroStationViewController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
    }


    @Override
    public void update() {
    }
}
