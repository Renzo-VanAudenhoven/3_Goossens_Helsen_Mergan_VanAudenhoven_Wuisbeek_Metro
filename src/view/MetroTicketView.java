package view;

import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();
	private ObservableList<Integer> ids;
	private ComboBox selectMetroCardComboBox;
	private MetroTicketViewController controller;
	private TextField priceTextField;
	private ToggleGroup optionsToggleGroup;
	private TextField numberOfRidesTextField;
	private CheckBox checkBox;
	private Label priceCalculationLabel;

	public MetroTicketView(MetroTicketViewController controller) {
		controller.setMetroTicketView(this);
		this.controller = controller;
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		Scene scene = new Scene(root, 650, 470);

		createInterface(root);

		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIDList(ArrayList<Integer> ids) {
		this.ids = FXCollections.observableArrayList(ids);
		selectMetroCardComboBox.setItems(this.ids);
	}

	private void newMetroCard() {
		controller.newMetroCard();
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
		newMetroCardButton.setOnAction(event -> newMetroCard());
		newMetroCardButton.setPrefSize(150, 20);
		vBox.getChildren().add(newMetroCardButton);

		Label metroCardPriceTextField = new Label();
		metroCardPriceTextField.setText("Metro card price is 15€ - 2 free rides included");
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
		numberOfRidesTextField = new TextField();
		numberOfRidesTextField.setText("");
		gridPane.add(numberOfRidesLabel, 0, 1);
		gridPane.add(numberOfRidesTextField, 1, 1);

		vBox.getChildren().addAll(gridPane);

		checkBox = new CheckBox("higher education student?");

		RadioButton option1RadioButton = new RadioButton("younger than 24 years");
		RadioButton option2RadioButton = new RadioButton("older than 64 years");
		RadioButton option3RadioButton = new RadioButton("between 24 and 64 years");

		optionsToggleGroup = new ToggleGroup();
		HBox optionsHbox = new HBox();
		optionsHbox.setSpacing(10);

		option1RadioButton.setToggleGroup(optionsToggleGroup);
		option2RadioButton.setToggleGroup(optionsToggleGroup);
		option3RadioButton.setToggleGroup(optionsToggleGroup);

		optionsToggleGroup.selectToggle(option3RadioButton);

		optionsHbox.getChildren().addAll(option1RadioButton, option2RadioButton, option3RadioButton);
		vBox.getChildren().addAll(checkBox, optionsHbox);

		VBox innerVBox = new VBox();
		innerVBox.setPadding(new Insets(15, 12, 15, 12));
		innerVBox.setSpacing(10);
		innerVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		innerVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));

		Button addExtraRideButton = new Button("Add extra rides to metro card");
		addExtraRideButton.setOnAction(event -> calculateExtraRidesPrice());

		GridPane priceGridPane = new GridPane();

		Label priceLabel = new Label("Total price");

		priceTextField = new TextField();
		priceTextField.setText("0€");
		priceTextField.setEditable(false);

		priceGridPane.add(priceLabel, 0, 0);
		priceGridPane.add(priceTextField, 1, 0);

		TextFlow textFlow = new TextFlow();
		textFlow.setMaxWidth(550);
		priceCalculationLabel = new Label("No extra rides added yet");
		priceCalculationLabel.setMaxWidth(550);
		priceCalculationLabel.setStyle("-fx-text-fill: red;");
		priceCalculationLabel.setWrapText(true);
		textFlow.getChildren().add(priceCalculationLabel);

		HBox requestButtons = new HBox();
		Button confirmRequestButton = new Button("Confirm request");
		confirmRequestButton.setOnAction(event -> confirmRequest());
		Button cancelRequestButton = new Button("Cancel request");
		cancelRequestButton.setOnAction(event -> cancelRequest());
		requestButtons.getChildren().addAll(confirmRequestButton, cancelRequestButton);

		innerVBox.getChildren().addAll(addExtraRideButton, priceGridPane, textFlow, requestButtons);
		vBox.getChildren().add(innerVBox);
		root.getChildren().add(vBox);
	}

	public void calculateExtraRidesPrice() {
		int id = Integer.parseInt(String.valueOf(selectMetroCardComboBox.getValue()));
		int aantalRitten = Integer.parseInt(numberOfRidesTextField.getText());
		boolean isStudent = checkBox.isSelected();
		String age = ((RadioButton) optionsToggleGroup.getSelectedToggle()).getText();
		boolean is64Plus = age.equals("older than 64 years");
		double price = controller.getPrice(id, aantalRitten, isStudent, is64Plus);
		String text = controller.getPriceText(id, isStudent, is64Plus);


		priceTextField.setText(Double.toString((double) Math.round(price*100) / 100) + "€");
		priceCalculationLabel.setText(text);
	}

	public void confirmRequest(){
		int id = Integer.parseInt(String.valueOf(selectMetroCardComboBox.getValue()));
		int aantalRitten = Integer.parseInt(numberOfRidesTextField.getText());
		controller.confirmRequest(id, aantalRitten);
		cancelRequest();
	}

	public void cancelRequest(){
		selectMetroCardComboBox.setValue(null);
		numberOfRidesTextField.setText("");
		priceTextField.setText("0");
		priceCalculationLabel.setText("No extra rides added yet");
	}
}
