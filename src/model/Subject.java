package model;

import java.util.ArrayList;
import java.util.List;

public interface Subject {

    List<Observer> observers = new ArrayList<>();

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
