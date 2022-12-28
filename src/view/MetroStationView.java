package view;

import controller.MetroStationViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MetroStationView {
	
	private Stage stage = new Stage();		
	
	public MetroStationView(MetroStationViewController controller){
		controller.setMetroStationView(this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 300);



		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
