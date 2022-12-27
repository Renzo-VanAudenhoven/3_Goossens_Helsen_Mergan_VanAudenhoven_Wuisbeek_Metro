package view.panels;


import controller.ControlCenterPaneController;
import controller.MetroCardOverviewPaneController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Observer;
import view.panels.MetroCardOverviewPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(ControlCenterPaneController controlCenterPaneController){
	    TabPane tabPane = new TabPane(); 	    
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
	    //maak een controlCenterPane aan
        ControlCenterPane controlCenterPane = new ControlCenterPane(controlCenterPaneController);
	    //maak een setupPane aan
        SetupPane setupPane = new SetupPane();
        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);
        Tab setupTab = new Tab("Setup", setupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
