package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetupPane extends VBox {
    public SetupPane(){
        VBox root = new VBox();
        root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);

        createInterface(root);

        getChildren().addAll(root);
    }

    public void saveSetting(Object setting){
        String settingString;
        //create a switch case if setting.tostring is equal to tekst or excel settingString = METROCARD_TEKST or METROCARD_EXCEL
        switch (setting.toString()){
            case "Tekst":
                settingString = "METROCARDS_TEKST";
                break;
            case "Excel":
                settingString = "METROCARDS_EXCEL";
                break;
            default:
                settingString = "METROCARDS_TEKST";
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

    public void createInterface(VBox root){
        ObservableList<String> options = FXCollections.observableArrayList("Tekst", "Excel");
        ComboBox comboBox = new ComboBox(options);
        Button button = new Button("Save");
        button.setOnAction(event -> saveSetting(comboBox.getValue()));

        VBox kortingen = new VBox();
        kortingen.setSpacing(10);
        Label kortingenLabel = new Label("Kortingen");
        kortingen.getChildren().add(kortingenLabel);
        createKorting(kortingen,"Korting 1");
        createKorting(kortingen,"Korting 2");
        createKorting(kortingen,"Korting 3");

        root.getChildren().addAll(comboBox, button,kortingen);
    }

    public void createKorting(VBox kortingen, String name){
        CheckBox korting = new CheckBox(name);
        kortingen.getChildren().add(korting);
    }
}
