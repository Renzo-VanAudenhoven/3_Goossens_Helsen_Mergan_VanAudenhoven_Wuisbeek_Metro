package view;

import controller.MetroStationViewController;
import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class MetroStationView {
	
	private Stage stage = new Stage();
	private ObservableList<Integer> ids;
	private MetroStationViewController controller;
	private ComboBox gate1ComboBox;
	private ComboBox gate2ComboBox;
	private ComboBox gate3ComboBox;
	
	public MetroStationView(MetroStationViewController controller){
		controller.setMetroStationView(this);
		this.controller = controller;
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

		this.gate1ComboBox = new ComboBox(this.ids);
		createGate(root,"Gate 1",gate1ComboBox);
		this.gate2ComboBox = new ComboBox(this.ids);
		createGate(root,"Gate 2",gate2ComboBox);
		this.gate3ComboBox = new ComboBox(this.ids);
		createGate(root, "Gate 3",gate3ComboBox);


		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void createGate(HBox root, String name, ComboBox gateComboBox) {
		VBox gate = new VBox();
		gate.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		gate.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
		gate.setMaxHeight(300);
		gate.setPrefWidth(200);
		gate.setSpacing(5);
		gate.setPadding(new Insets(10));

		// Add a label at the top
		Label label = new Label(name);
		gate.getChildren().add(label);

		// Add a label and a combo box for the metro card ID
		Label metroCardIdLabel = new Label("Metrocard ID");
		gate.getChildren().addAll(metroCardIdLabel, gateComboBox);

		// Add a button to scan the metro card
		Button scanButton = new Button("Scan metrocard");
		scanButton.setOnAction(event -> scanMetroGate());
		gate.getChildren().add(scanButton);

		// Add a button to walk through the gate
		Button walkThroughButton = new Button("Walk through gate");
		gate.getChildren().add(walkThroughButton);

		// Add a text field to show the scanned card info
		TextField infoField = new TextField("Card 3 is scanned");
		infoField.setEditable(false);
		gate.getChildren().add(infoField);

		root.getChildren().add(gate);
	}

	public void updateMetroCardIDList(ArrayList<Integer> ids) {
		this.ids = FXCollections.observableArrayList(ids);
		gate1ComboBox.setItems(this.ids);
		gate2ComboBox.setItems(this.ids);
		gate3ComboBox.setItems(this.ids);
	}

	public void scanMetroGate(){
		//int metroCardID =
		controller.scanMetroGate();
	}

}
