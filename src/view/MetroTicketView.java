package view;

import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();
	private ObservableList<Integer> ids;
	private ComboBox selectMetroCardComboBox;

	public MetroTicketView(MetroTicketViewController controller) {
		controller.setMetroTicketView(this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		Scene scene = new Scene(root, 650, 450);


		createInterface(root);


		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIDList(ArrayList<Integer> ids) {
		this.ids = FXCollections.observableArrayList(ids);
		selectMetroCardComboBox.setItems(this.ids);
	}

	public void createInterface(VBox root) {
		createNewMetroCardBox(root);
		createMetroDetailsMainBox(root);
	}

	public void createNewMetroCardBox(VBox root){
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(15, 12, 15, 12));
		vBox.setSpacing(10);
		vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));

		Button newMetroCardButton = new Button("New metro card");
		newMetroCardButton.setPrefSize(150, 20);
		newMetroCardButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		vBox.getChildren().add(newMetroCardButton);

		Label metroCardPriceTextField = new Label();
		metroCardPriceTextField.setText("Metro card price is 2.5 free rides included");
		metroCardPriceTextField.setStyle("-fx-text-fill: red;");
		vBox.getChildren().add(metroCardPriceTextField);


		root.getChildren().add(vBox);
	}

	public void createMetroDetailsMainBox(VBox root){
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(15, 12, 15, 12));
		vBox.setSpacing(10);
		vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));


		GridPane gridPane = new GridPane();
		Label selectMetroCardLabel = new Label("Select metro card");
		this.selectMetroCardComboBox = new ComboBox(ids);

		gridPane.add(selectMetroCardLabel, 0, 0);
		gridPane.add(selectMetroCardComboBox, 1, 0);

		Label numberOfRidesLabel = new Label("Number of rides");
		TextField numberOfRidesTextField = new TextField();
		numberOfRidesTextField.setText("4");
		gridPane.add(numberOfRidesLabel, 0, 1);
		gridPane.add(numberOfRidesTextField, 1, 1);

		vBox.getChildren().addAll(gridPane);

		VBox innerVBox = new VBox();
		innerVBox.setPadding(new Insets(15, 12, 15, 12));
		innerVBox.setSpacing(10);
		innerVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		innerVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));

		Button addExtraRideButton = new Button("Add extra rides to metro card");
		GridPane priceGridPane = new GridPane();
		Label priceLabel = new Label("Total price");
		TextField priceTextField = new TextField();
		priceTextField.setText("0");
		priceTextField.setEditable(false);
		priceGridPane.add(priceLabel, 0, 0);
		priceGridPane.add(priceTextField, 1, 0);

		Label priceCalculationLabel = new Label("Basic price of ride is INSERT CALCULATION HERE");
		priceCalculationLabel.setStyle("-fx-text-fill: red;");

		HBox requestButtons = new HBox();
		Button confirmRequestButton = new Button("Confirm request");
		Button cancelRequestButton = new Button("Cancel request");
		requestButtons.getChildren().addAll(confirmRequestButton, cancelRequestButton);

		innerVBox.getChildren().addAll(addExtraRideButton, priceGridPane, priceCalculationLabel, requestButtons);
		vBox.getChildren().add(innerVBox);
		root.getChildren().add(vBox);
	}
}
