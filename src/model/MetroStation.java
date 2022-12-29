package model;

import java.util.ArrayList;

public class MetroStation {
    ArrayList<MetroGate> metroGates = new ArrayList<>();

    public void scanMetroGate(int id){
        metroGates.get(id).scanMetroGate();
    }

}
