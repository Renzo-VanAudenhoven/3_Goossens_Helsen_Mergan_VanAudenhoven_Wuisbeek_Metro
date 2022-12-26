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

        Button button = new Button("Open metrostation");
        button.setOnAction(event -> openMetroStation());
        getChildren().add(button);

    }

    public void openMetroStation() {
        controlCenterPaneController.openMetroStation();
    }
}
