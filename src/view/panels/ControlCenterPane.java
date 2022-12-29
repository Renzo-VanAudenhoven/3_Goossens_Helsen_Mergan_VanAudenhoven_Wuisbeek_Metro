package view.panels;

import controller.ControlCenterPaneController;
import controller.MetroStationViewController;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class ControlCenterPane extends VBox {

    private ControlCenterPaneController controlCenterPaneController;
    private ArrayList<VBox> gates = new ArrayList<>();
    private TextField numberOfSoldTicketsField;
    private TextField totalAmountOfSoldTicketsField;
    private TextArea alertsTextField;
    private MetroStationViewController metroStationViewController;
    private Button buttonOpen;
    private Button buttonClose;


    public ControlCenterPane(ControlCenterPaneController controlCenterPaneController, MetroStationViewController metroStationViewController) {
        controlCenterPaneController.setControlCenterPane(this);
        this.controlCenterPaneController = controlCenterPaneController;
        this.metroStationViewController = metroStationViewController;

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
        buttonOpen = new Button("Open metrostation");
        buttonClose = new Button("Close metrostation");
        buttonOpen.setOnAction(event -> openMetroStation());
        buttonClose.setOnAction(event -> closeMetroStation());
        buttonClose.setDisable(true);
        container.getChildren().addAll(buttonOpen, buttonClose);
        root.getChildren().add(container);
        this.getChildren().add(root);
    }

    public void openMetroStation() {
        controlCenterPaneController.openMetroStation();
        buttonOpen.setDisable(true);
        buttonClose.setDisable(false);
    }



    public void closeMetroStation() {
        controlCenterPaneController.closeMetroStation();
        buttonOpen.setDisable(false);
        buttonClose.setDisable(true);
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
        numberOfSoldTicketsField = new TextField("");
        numberOfSoldTicketsField.setEditable(false);

        Label totalAmountOfSoldTicketsLabel = new Label("Total amount of sold tickets:");
        totalAmountOfSoldTicketsField = new TextField("100.30€");
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

    public void createGateBox(HBox hbox, int gateid, VBox gate, String status) {
        gate.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gate.setBackground(new Background(new BackgroundFill(Color.web("#FFD580"), new CornerRadii(10), Insets.EMPTY)));
        gate.setMaxWidth(200);
        gate.setSpacing(5);
        gate.setPadding(new Insets(10));

        Label gateLabel = new Label("Gate " + gateid + " / " + status);
        Button activateButton = new Button("Activate");
        activateButton.setOnAction(event -> activateGate(gateid-1));
        Button deactivateButton = new Button("Deactivate");
        deactivateButton.setOnAction(event -> deactivateGate(gateid-1));
        Label scannedCardsLabel = new Label("# scanned cards");
        TextField scannedCardsField = new TextField("0");
        scannedCardsField.setEditable(false);

        gate.getChildren().addAll(gateLabel, activateButton, deactivateButton, scannedCardsLabel, scannedCardsField);
        hbox.getChildren().add(gate);
    }

    public void createAlertsBox(VBox root){
        Label alertsLabel = new Label("ALERTS");
        alertsLabel.setPadding(new Insets(10, 0, 0, 10));

        alertsTextField = new TextArea("");
        alertsTextField.setEditable(false);
        alertsTextField.setStyle("-fx-text-fill: red;");

        root.getChildren().addAll(alertsLabel,alertsTextField);
    }

    public void activateGate(int gateid){
        try {
            controlCenterPaneController.activateGate(gateid);
            metroStationViewController.activateGate(gateid);
            gates.get(gateid).setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
            ((Label)gates.get(gateid).getChildren().get(0)).setText("Gate " + (gateid+1) + " / active");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deactivateGate(int gateid){
        try {
            controlCenterPaneController.deactivateGate(gateid);
            metroStationViewController.deactivateGate(gateid);
            gates.get(gateid).setBackground(new Background(new BackgroundFill(Color.web("#FFD580"), new CornerRadii(10), Insets.EMPTY)));
            ((Label)gates.get(gateid).getChildren().get(0)).setText("Gate " + (gateid+1) + " / inactive");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateAmountOfTickets(int amountOfTickets) {
        numberOfSoldTicketsField.setText(String.valueOf(amountOfTickets));
    }

    public void updateScannedCardsAmount(int gateIndex) {
        TextField scannedCardsAmount = (TextField) gates.get(gateIndex).getChildren().get(4);
        int amount = Integer.parseInt(scannedCardsAmount.getText());
        scannedCardsAmount.setText(String.valueOf(amount + 1));
    }

    public void updateAlerts(String message, int gateIndex) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat.format(calendar.getTime());
        int number = gateIndex + 1;
        alertsTextField.setText(time + " in gate " + number + " : " + message + "\n" + alertsTextField.getText());
    }
}
