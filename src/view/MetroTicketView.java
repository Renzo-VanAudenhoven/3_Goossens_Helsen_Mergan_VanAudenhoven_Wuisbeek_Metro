package view;

import controller.MetroTicketViewController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();

	public MetroTicketView(MetroTicketViewController controller) {
		controller.setMetroTicketView(this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 650, 350);


		createInterface(root);


		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIDList(ArrayList<Integer> ids) {

	}

	public void createInterface(BorderPane root) {
		// Create a grid pane to hold the rectangles and the controls
		GridPane gridPane = new GridPane();

		// Create rectangles for each section
		Rectangle rectangle1 = new Rectangle(600, 50, Color.LIGHTGRAY);
		rectangle1.setStroke(Color.BLACK);
		Rectangle rectangle2 = new Rectangle(600, 125, Color.LIGHTGRAY);
		rectangle2.setStroke(Color.BLACK);
		Rectangle rectangle3 = new Rectangle(600, 125, Color.LIGHTGRAY);
		rectangle3.setStroke(Color.BLACK);

		// Create a layout pane for the controls in the third section
		VBox vBox = new VBox();

		//section one
		// Create a grid pane to hold the button and the description text
		GridPane gridPane1 = new GridPane();

		// Create a button and a label
		Button newMetroCardButton = new Button("New Metro Card");
		Label descriptionLabel = new Label("Description text");

		// Add the button and the label to the grid pane
		gridPane1.add(newMetroCardButton, 0, 0);
		gridPane1.add(descriptionLabel, 0, 1);

		// Add the grid pane and the rectangle to the main grid pane
		gridPane.add(rectangle1, 0, 0);
		gridPane.add(gridPane1, 0, 0);


		//section two
		GridPane gridPane2 = new GridPane();

		Label selectMetroCardLabel = new Label("Select metro card");
		ComboBox<String> metroCardComboBox = new ComboBox<>();

		Label numberOfRidesLabel = new Label("Number of rides");
		TextField numberOfRidesField = new TextField();

		Label higherEducationLabel = new Label("Higher education student?");
		CheckBox higherEducationCheckBox = new CheckBox();

		Label optionsLabel = new Label("Options");
		RadioButton optionARadioButton = new RadioButton("A");
		RadioButton optionBRadioButton = new RadioButton("B");
		RadioButton optionCRadioButton = new RadioButton("C");
		ToggleGroup optionsToggleGroup = new ToggleGroup();
		optionARadioButton.setToggleGroup(optionsToggleGroup);
		optionBRadioButton.setToggleGroup(optionsToggleGroup);
		optionCRadioButton.setToggleGroup(optionsToggleGroup);

		gridPane2.add(selectMetroCardLabel, 0, 0);
		gridPane2.add(metroCardComboBox, 1, 0);
		gridPane2.add(numberOfRidesLabel, 0, 1);
		gridPane2.add(numberOfRidesField, 1, 1);
		gridPane2.add(higherEducationLabel, 0, 2);
		gridPane2.add(higherEducationCheckBox, 1, 2);
		gridPane2.add(optionsLabel, 0, 3);
		gridPane2.add(optionARadioButton, 1, 3);
		gridPane2.add(optionBRadioButton, 2, 3);
		gridPane2.add(optionCRadioButton, 3, 3);

		// Add the grid pane and the rectangle to the main grid pane
		gridPane.add(rectangle2, 0, 1);
		gridPane.add(gridPane2, 0, 1);

		//section three
		// Create a button, a label, a text field, and two buttons
		Button addExtraRidesButton = new Button("Add extra rides to metro card");
		Label totalPriceLabel = new Label("Total price:");
		TextField totalPriceField = new TextField();
		totalPriceField.setEditable(false);
		totalPriceField.setText("0");
		totalPriceField.setMaxWidth(100); // set the width of the field to 100 pixels
		Button confirmRequestButton = new Button("Confirm request");
		Button cancelRequestButton = new Button("Cancel request");

		// Create a grid pane to hold the total price label and field
		GridPane totalPricePane = new GridPane();

		// Add the label and the field to the grid pane
		totalPricePane.add(totalPriceLabel, 0, 0);
		totalPricePane.add(totalPriceField, 1, 0);

		// Add the label, the text field, and the buttons to the layout pane
		vBox.getChildren().addAll(addExtraRidesButton, totalPricePane, confirmRequestButton, cancelRequestButton);

		// Add the layout pane and the rectangle to the main grid pane
		gridPane.add(rectangle3, 0, 2);
		gridPane.add(vBox, 0, 2);

		root.setCenter(gridPane);
	}
}
