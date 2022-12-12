package model.database.loadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;
import model.database.utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MetrocardsTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{
    @Override
    public MetroCard maakObject(String[] tokens) {
        return new MetroCard(Integer.parseInt(tokens[0]), String.valueOf(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    public String getKey(String[] tokens) {
        return tokens[0];
    }

}
