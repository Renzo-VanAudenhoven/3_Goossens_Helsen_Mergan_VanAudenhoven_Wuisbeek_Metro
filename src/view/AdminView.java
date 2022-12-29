package view;

import controller.ControlCenterPaneController;
import controller.MetroCardOverviewPaneController;
import controller.MetroStationViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Observer;
import view.panels.AdminMainPane;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class AdminView {
	private Stage stage = new Stage();		
		
	public AdminView(ControlCenterPaneController controlCenterPaneController, MetroCardOverviewPaneController metroCardOverviewPaneController, MetroStationViewController metroStationViewController) {
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 905);
		BorderPane borderPane = new AdminMainPane(controlCenterPaneController, metroCardOverviewPaneController, metroStationViewController);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
