package model;

import java.util.ArrayList;

public class MetroStation {
    ArrayList<MetroGate> metroGates = new ArrayList<>();

    public MetroStation() {
        for (int i = 0; i < 4; i++) {
            metroGates.add(new MetroGate(i));
        }
    }

    public void scanMetroGate(int gateIndex){
        metroGates.get(gateIndex).scanMetroGate();
    }

    public void activateGate(int gateid) {
        metroGates.get(gateid).activate();
    }

    public void deactivateGate(int gateid) {
        metroGates.get(gateid).deactivate();
    }

    public void walkThroughGate(int gateIndex) {
        metroGates.get(gateIndex).walkThroughGate();
    }
}
