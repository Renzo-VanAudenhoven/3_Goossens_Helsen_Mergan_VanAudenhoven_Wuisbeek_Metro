package view;

import controller.ControlCenterPaneController;
import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class MetroStationView {
	
	private Stage stage = new Stage();
	private ObservableList<Integer> ids;
	private MetroStationViewController metroStationViewController;
	private ControlCenterPaneController controlCenterPaneController;
	private ArrayList<VBox> gates = new ArrayList<>();
	
	public MetroStationView(MetroStationViewController metroStationViewController, ControlCenterPaneController controlCenterPaneController){
		metroStationViewController.setMetroStationView(this);
		this.metroStationViewController = metroStationViewController;
		this.controlCenterPaneController = controlCenterPaneController;

		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(510);
		HBox root = new HBox();
		Scene scene = new Scene(root, 650, 400);
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

//		this.gate1 = new VBox();
//		createGate(root,"Gate 1",gate1,1);
//		this.gate2 = new VBox();
//		createGate(root,"Gate 2",gate2,2);
//		this.gate3 = new VBox();
//		createGate(root, "Gate 3",gate3,3);

		for (int i = 0; i < 3; i++) {
			gates.add(new VBox());
			createGate(root, "Gate " + (i + 1), gates.get(i), i);
		}

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void createGate(HBox root, String name, VBox gate, int gateIndex) {
		gate.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		gate.setBackground(new Background(new BackgroundFill(Color.web("#FFD580"), new CornerRadii(10), Insets.EMPTY)));
		gate.setMaxHeight(300);
		gate.setPrefWidth(200);
		gate.setSpacing(5);
		gate.setPadding(new Insets(10));

		// Add a label at the top
		Label label = new Label(name);
		gate.getChildren().add(label);

		// Add a label and a combo box for the metro card ID
		ComboBox gateComboBox = new ComboBox(this.ids);
		Label metroCardIdLabel = new Label("Metrocard ID");
		gate.getChildren().addAll(metroCardIdLabel, gateComboBox);

		// Add a button to scan the metro card
		Button scanButton = new Button("Scan metrocard");
//		scanButton.setDisable(true);
		scanButton.setOnAction(event -> scanMetroGate(gateIndex));
		gate.getChildren().add(scanButton);

		// Add a button to walk through the gate
		Button walkThroughButton = new Button("Walk through gate");
//		walkThroughButton.setDisable(true);
		walkThroughButton.setOnAction(event -> walkThroughGate(gateIndex));
		gate.getChildren().add(walkThroughButton);

		// Add a text field to show the scanned card info
		TextField infoField = new TextField("Card 3 is scanned");
		infoField.setEditable(false);
		gate.getChildren().add(infoField);

		root.getChildren().add(gate);
	}

	public void updateMetroCardIDList(ArrayList<Integer> ids) {
		this.ids = FXCollections.observableArrayList(ids);
		for (VBox gate : gates) {
			ComboBox gateComboBox = (ComboBox) gate.getChildren().get(2);
			gateComboBox.setItems(this.ids);
		}
	}

	public void scanMetroGate(int gateIndex) {
		VBox currentGate = new VBox();
		for (int i = 0; i < gates.size(); i++) {
			if (i == gateIndex) {
				currentGate = gates.get(i);
			}
		}
		ComboBox gateComboBox = (ComboBox) currentGate.getChildren().get(2);
		int metroCardID = Integer.parseInt(gateComboBox.getValue().toString());
		TextField infoField = (TextField) currentGate.getChildren().get(5);
		try{
			metroStationViewController.scanMetroGate(metroCardID, gateIndex);
			controlCenterPaneController.updateScannedCardsAmount(gateIndex);
			infoField.setText("Card " + metroCardID + " is scanned");
		} catch (Exception e) {
			infoField.setText(e.getMessage());
			controlCenterPaneController.updateAlerts(e.getMessage(), gateIndex);
		}
	}

	public void walkThroughGate(int gateIndex){
		VBox currentGate = new VBox();
		for (int i = 0; i < gates.size(); i++) {
			if (i == gateIndex) {
				currentGate = gates.get(i);
			}
		}
		TextField infoField = (TextField) currentGate.getChildren().get(5);
		try {
			metroStationViewController.walkThroughGate(gateIndex);
		}
		catch (Exception e){
			infoField.setText(e.getMessage());
			controlCenterPaneController.updateAlerts(e.getMessage(), gateIndex);
		}
	}


	public void activateGate(int gateid) {
		VBox currentGate = gates.get(gateid);
		currentGate.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
//		currentGate.getChildren().get(3).setDisable(false);
//		currentGate.getChildren().get(4).setDisable(false);
	}

	public void deactivateGate(int gateid) {
		VBox currentGate = gates.get(gateid);
		currentGate.setBackground(new Background(new BackgroundFill(Color.web("#FFD580"), new CornerRadii(10), Insets.EMPTY)));
//		currentGate.getChildren().get(3).setDisable(true);
//		currentGate.getChildren().get(4).setDisable(true);
	}
}
