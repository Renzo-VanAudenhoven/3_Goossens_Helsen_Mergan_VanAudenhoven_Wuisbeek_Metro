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
        //metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION,this);
    }

    @Override
    public void update() {
    }

    public void openMetroStation(){
        metroFacade.openMetroStation();
    }
}
