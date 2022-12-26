package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.awt.*;

public class ControlCenterPane extends VBox {
    private LoadSaveStrategyFactory loadSaveFactory = new LoadSaveStrategyFactory();
    public ControlCenterPane() {
        //VBox layout = new VBox();

        Button button = new Button("Open metrostation");
        button.setOnAction(event -> load());
        getChildren().add(button);

    }

    public void load() {
        System.out.println("ControlCenterPane loaded");
    }
}
