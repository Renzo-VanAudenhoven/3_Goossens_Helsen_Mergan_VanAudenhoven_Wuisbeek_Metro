package model.database.loadSaveStrategies;

import model.MetroCard;
import model.database.utilities.ExcelLoadSaveTemplate;

public class MetrocardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy{

    @Override
    public MetroCard maakObject(String[] tokens) {
        return new MetroCard(Integer.parseInt(tokens[0]), String.valueOf(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    public String getKey(String[] tokens) {
        return tokens[0];
    }
}
