package model.database.utilities;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class TekstLoadSaveTemplate<K,V>{

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);

    public final Map<K,V> load(File file) throws IOException, BiffException {
        Map<K,V> returnMap = new HashMap<K,V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(";");
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }



    public final void save(Map<K,V> map, File file) throws WriteException, IOException, BiffException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                writer.write(key + ";" + value);
                writer.newLine();
            }
        }
    }
}
