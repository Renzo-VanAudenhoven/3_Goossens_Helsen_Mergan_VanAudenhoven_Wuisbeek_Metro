package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public interface Subject {

    void addObserver(MetroEventsEnum e, Observer o);

    void removeObserver(MetroEventsEnum e, Observer o);

    void notifyObservers(MetroEventsEnum e);
}
