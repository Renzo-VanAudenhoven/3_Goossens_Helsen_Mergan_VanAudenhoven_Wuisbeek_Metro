package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetupPane extends VBox {
    public SetupPane(){
        Button button = new Button("Save");
        ObservableList<String> options = FXCollections.observableArrayList("Tekst", "Excel");
        ComboBox comboBox = new ComboBox(options);
        button.setOnAction(event -> saveSetting(comboBox.getValue()));
        getChildren().addAll(comboBox ,button);
    }

    public void saveSetting(Object setting){
        String settingString;
        //create a switch case if setting.tostring is equal to tekst or excel settingString = METROCARD_TEKST or METROCARD_EXCEL
        switch (setting.toString()){
            case "Tekst":
                settingString = "METROCARD_TEKST";
                break;
            case "Excel":
                settingString = "METROCARD_EXCEL";
                break;
            default:
                settingString = "METROCARD_TEKST";
                break;
        }

        System.out.println(settingString);
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/bestanden/settings.properties")) {
            prop.load(input);
            prop.setProperty("formaat", settingString);
            prop.store(new FileOutputStream("src/bestanden/settings.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
