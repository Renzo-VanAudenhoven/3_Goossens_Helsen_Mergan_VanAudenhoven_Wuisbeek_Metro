package model.database.loadSaveStrategies;

public class LoadSaveStrategyFactory{

    public LoadSaveStrategy createLoadSaveStrategy(LoadSaveStrategyEnum loadSaveStrategyEnum){
        try {
            return (LoadSaveStrategy) Class.forName("model.database.loadSaveStrategies." + loadSaveStrategyEnum.getStrategy()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
