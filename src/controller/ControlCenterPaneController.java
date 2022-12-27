package controller;

import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private MetroFacade metroFacade = new MetroFacade();
    private ControlCenterPane controlCenterPane = new ControlCenterPane();

    @Override
    public void update() {
    }

    public void openMetroStation(){
        metroFacade.openMetroStation();
    }
}
