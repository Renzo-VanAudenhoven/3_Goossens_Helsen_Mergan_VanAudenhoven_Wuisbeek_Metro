package view.panels;


import controller.MetroCardOverviewPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MetroCard;
import model.Observer;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import java.util.ArrayList;


public class MetroCardOverviewPane extends GridPane{
	private TableView<MetroCard> table;
	private ObservableList<MetroCard> metrocards;
	private VBox root;
	
	public 	MetroCardOverviewPane(MetroCardOverviewPaneController controller){
		controller.setMetroCardOverviewPane(this);
		root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("List of Metro cards:"), 0, 0, 1, 1);

		table = new TableView<MetroCard>();
		//refresh();

		TableColumn<MetroCard, Integer> colID = new TableColumn<MetroCard, Integer>("MetroCard ID");
		colID.setMinWidth(100);
		colID.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("kaartID"));

		TableColumn<MetroCard, String> colAankoopDatum = new TableColumn<MetroCard, String>("Aankoopdatum");
		colAankoopDatum.setMinWidth(300);
		colAankoopDatum.setCellValueFactory(new PropertyValueFactory<MetroCard, String>("aankoopdatum"));

		TableColumn<MetroCard, Integer> colRittenBeschikbaar = new TableColumn<MetroCard, Integer>("Ritten Beschikbaar");
		colRittenBeschikbaar.setMinWidth(100);
		colRittenBeschikbaar.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("rittenBeschikbaar"));

		TableColumn<MetroCard, Integer> colRittenVerbruikt = new TableColumn<MetroCard, Integer>("Ritten Verbruikt");
		colRittenVerbruikt.setMinWidth(100);
		colRittenVerbruikt.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("rittenVerbruikt"));

		table.getColumns().addAll(colID, colAankoopDatum, colRittenBeschikbaar,colRittenVerbruikt);

		//refresh();

		root.getChildren().addAll(table);
		this.add(root, 0, 2);
	}
	public void updateMetroCardList(ArrayList<MetroCard> metrocards){
		this.metrocards = FXCollections.observableArrayList(metrocards);
		table.setItems(this.metrocards);
		table.refresh();
	}

	public void displayMessage(String message){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information ");
		alert.setContentText(message);
		alert.show();
	}
/*
	public void refresh(){
		metrocards = FXCollections.observableArrayList(database.getMetroCardList());
		table.setItems(metrocards);
		table.refresh();
	}*/

}
