package model.database.loadSaveStrategies;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadSaveStrategyFactory{

    public LoadSaveStrategy createLoadSaveStrategy() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/bestanden/settings.properties")) {
            prop.load(input);
            String strategy = prop.getProperty("formaat");
            LoadSaveStrategyEnum loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf(strategy.toUpperCase());
            return (LoadSaveStrategy) Class.forName("model.database.loadSaveStrategies." + loadSaveStrategyEnum.getStrategy()).newInstance();
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
