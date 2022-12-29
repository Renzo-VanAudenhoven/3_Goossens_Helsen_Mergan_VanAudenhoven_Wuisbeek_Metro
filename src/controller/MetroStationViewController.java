package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

import java.util.ArrayList;

public class MetroStationViewController implements Observer {
    private MetroFacade metroFacade;
    private MetroStationView metroStationView;

    public MetroStationViewController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
        metroFacade.addObserver(MetroEventsEnum.CONFIRM_REQUEST, this);
    }

    public void setMetroStationView(MetroStationView metroStationView) {
        this.metroStationView = metroStationView;
    }

    @Override
    public void update() {
        ArrayList<Integer> ids = metroFacade.getMetroCardIDList();
        metroStationView.updateMetroCardIDList(ids);
    }

    public void scanMetroGate(int metroCardID, int gateIndex) {
        metroFacade.scanMetroGate(metroCardID, gateIndex);
    }
}
