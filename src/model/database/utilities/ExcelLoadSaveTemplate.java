package model.database.utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public abstract class ExcelLoadSaveTemplate<K,V>{
    public ExcelPlugin excelPlugin = new ExcelPlugin();



    public abstract V maakObject(String[] tokens);



    public abstract K getKey(String[] tokens);



    public final Map<K,V> load(File file) throws IOException, BiffException{
        return listArrayListToMap(excelPlugin.read(file));
    }



    public final void save(Map<K,V> map, File file) throws WriteException, IOException, BiffException {
        excelPlugin.write(file,mapToListArrayList(map));
    }

    private ArrayList<ArrayList<String>> mapToListArrayList(Map<K,V> map){
        ArrayList<ArrayList<String>> lijst = new ArrayList<>();

        for (V value : map.values()) {
            ArrayList<String> waardeOmgezetNaarArrayList = new ArrayList<String>(Arrays.asList(value.toString().split(",")));
            lijst.add(waardeOmgezetNaarArrayList);
        }
        return lijst;
    }



    private Map<K,V> listArrayListToMap(ArrayList<ArrayList<String>> lijst){
        Map<K,V> map = new TreeMap();

        for (ArrayList<String> value : lijst) {
            K arrayListOmgezetNaarKey = getKey(value.toArray(new String[0]));
            V arrayListOmgezetNaarObject = maakObject(value.toArray(new String[0]));
            map.put(arrayListOmgezetNaarKey,arrayListOmgezetNaarObject);
        }
        return map;
    }



}