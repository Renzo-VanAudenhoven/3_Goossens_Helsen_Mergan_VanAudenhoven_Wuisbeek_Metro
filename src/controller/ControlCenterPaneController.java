package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private MetroFacade metroFacade;
    private ControlCenterPane controlCenterPane;

    public ControlCenterPaneController () {
        System.out.println("ControlCenterPaneController");
        metroFacade = new MetroFacade();
    }

    @Override
    public void update() {
    }

    public void openMetroStation(){
        System.out.println("In openMetroStation van ControlCenterPaneController");
        metroFacade.openMetroStation();
    }
}
