package model.database.loadSaveStrategies;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public enum LoadSaveStrategyEnum {
    METROCARDS_TEKST("MetrocardsTekstLoadSaveStrategy"),
    METROCARDS_EXCEL("MetrocardsExcelLoadSaveStrategy");

    private String strategy;

    LoadSaveStrategyEnum(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }
}
