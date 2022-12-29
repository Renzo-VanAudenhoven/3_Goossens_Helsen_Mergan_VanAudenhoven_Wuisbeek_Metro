package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;


public class ControlCenterPane extends VBox {

    private ControlCenterPaneController controlCenterPaneController;
    private ArrayList<VBox> gates = new ArrayList<>();


    public ControlCenterPane(ControlCenterPaneController controlCenterPaneController) {
        this.controlCenterPaneController = controlCenterPaneController;

        //width,height van de parent box (690, 885);
        VBox root = new VBox();
        root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        root.setPrefHeight(600);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);

        createInterface(root);
        HBox container = new HBox();
        container.setSpacing(10);
        container.setPadding(new Insets(10));
        Button buttonOpen = new Button("Open metrostation");
        Button buttonClose = new Button("Close metrostation");
        buttonOpen.setOnAction(event -> openMetroStation());
        buttonClose.setOnAction(event -> closeMetroStation());
        container.getChildren().addAll(buttonOpen, buttonClose);
        root.getChildren().add(container);
        this.getChildren().add(root);
    }

    public void openMetroStation() {
        controlCenterPaneController.openMetroStation();
    }



    public void closeMetroStation() {
        controlCenterPaneController.closeMetroStation();
    }

    public void createInterface(VBox root){
        createSoldTicketsBox(root);
        createGatesBox(root);
        createAlertsBox(root);
    }

    public void createSoldTicketsBox(VBox root){
        GridPane gridPane = new GridPane();
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        gridPane.setPrefHeight(100);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label numberOfSoldTicketsLabel = new Label("Number of sold tickets:");
        TextField numberOfSoldTicketsField = new TextField("55");
        numberOfSoldTicketsField.setEditable(false);

        Label totalAmountOfSoldTicketsLabel = new Label("Total amount of sold tickets:");
        TextField totalAmountOfSoldTicketsField = new TextField("97.55");
        totalAmountOfSoldTicketsField.setEditable(false);

        gridPane.add(numberOfSoldTicketsLabel, 0, 0);
        gridPane.add(numberOfSoldTicketsField, 1, 0);
        gridPane.add(totalAmountOfSoldTicketsLabel, 0, 1);
        gridPane.add(totalAmountOfSoldTicketsField, 1, 1);

        root.getChildren().add(gridPane);
    }

    public void createGatesBox(VBox root){
        HBox hbox = new HBox();
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));

        for (int i = 0; i < 3; i++) {
            gates.add(new VBox());
            createGateBox(hbox, (i + 1), gates.get(i), "inactive");
        }

//        createGateBox(hbox, "Gate 1", "inactive");
//        createGateBox(hbox, "Gate 2", "active");
//        createGateBox(hbox, "Gate 3", "inactive");

        root.getChildren().addAll(hbox);
    }

    public void createGateBox(HBox hbox, int gateid, VBox gate, String status){
        VBox vbox = new VBox();
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        vbox.setMaxWidth(200);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10));

        Label gateLabel = new Label("Gate " + gateid + " / " + status);
        Button activateButton = new Button("Activate");
        activateButton.setOnAction(event -> activateGate(gateid));
        Button deactivateButton = new Button("Deactivate");
        deactivateButton.setOnAction(event -> deactivateGate(gateid));
        Label scannedCardsLabel = new Label("# scanned cards");
        TextField scannedCardsField = new TextField();
        scannedCardsField.setEditable(false);

        vbox.getChildren().addAll(gateLabel, activateButton, deactivateButton, scannedCardsLabel, scannedCardsField);
        hbox.getChildren().add(vbox);
    }

    public void createAlertsBox(VBox root){
        Label alertsLabel = new Label("ALERTS");
        alertsLabel.setPadding(new Insets(10, 0, 0, 10));

        TextArea alertsTextField = new TextArea("06:22 UNAUTHORIZED PASSAGE GATE 1\n" +
                "06:22 UNAUTHORIZED PASSAGE GATE 2\n" +
                "06:22 UNAUTHORIZED PASSAGE GATE 3\n");
        alertsTextField.setEditable(false);
        alertsTextField.setStyle("-fx-text-fill: red;");
        ScrollPane scrollPane = new ScrollPane(alertsTextField);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        root.getChildren().addAll(alertsLabel,scrollPane);
    }

    public void activateGate(int gateid){
        controlCenterPaneController.activateGate(gateid);
    }

    public void deactivateGate(int gateid){
        controlCenterPaneController.deactivateGate(gateid);
    }
}
