package model.database.loadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public interface LoadSaveStrategy<K,V> {
    Map<K,V> load(File file) throws IOException, BiffException;
    void save(Map<K,V> map, File file) throws IOException, BiffException, WriteException;
}
