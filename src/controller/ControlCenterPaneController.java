package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private MetroFacade metroFacade;
    private ControlCenterPane controlCenterPane;

    public ControlCenterPaneController(MetroFacade facade){
        this.metroFacade = facade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARD,this);
    }

    public void setControlCenterPane(ControlCenterPane controlCenterPane) {
        this.controlCenterPane = controlCenterPane;
    }

    @Override
    public void update() {
        int amountOfTickets = metroFacade.getAmountOfTickets();
        controlCenterPane.updateAmountOfTickets(amountOfTickets);
    }

    public void openMetroStation(){
        metroFacade.openMetroStation();
    }

    public void closeMetroStation(){
        metroFacade.closeMetroStation();
    }

    public void activateGate(int gateid) {
        metroFacade.activateGate(gateid);
    }

    public void deactivateGate(int gateid) {
        metroFacade.deactivateGate(gateid);
    }
}
