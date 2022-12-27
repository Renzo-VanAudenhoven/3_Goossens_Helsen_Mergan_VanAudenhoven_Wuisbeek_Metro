package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.awt.*;

public class ControlCenterPane extends VBox {
    private ControlCenterPaneController controlCenterPaneController;
    public ControlCenterPane() {
        //VBox layout = new VBox();
        controlCenterPaneController = new ControlCenterPaneController();
        Button button = new Button("Open metrostation");
        System.out.println("voor lambda");
        button.setOnAction(event -> openMetroStation());
        System.out.println("na lambda");
        getChildren().add(button);

    }

    public void openMetroStation() {
        System.out.println("In openMetroStation van ControlCenterPane");
        controlCenterPaneController.openMetroStation();
    }
}
