package controller;


import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

import java.util.ArrayList;

public class MetroStationViewController implements Observer {
    private MetroFacade facade;
    private MetroStationView metroStationView = new MetroStationView();

    public MetroStationViewController(MetroFacade facade) {
        this.facade = facade;
        facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = facade.getMetroCardIDList();
        metroStationView.updateMetroCardIDList(ids);
    }


}
