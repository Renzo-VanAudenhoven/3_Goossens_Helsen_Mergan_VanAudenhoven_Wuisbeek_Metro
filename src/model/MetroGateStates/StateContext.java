package model.MetroGateStates;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class StateContext {
    private MetroGateState currentState;

    public StateContext() {
        currentState = new Inactive();
    }

    public void setState(MetroGateState state) {
        currentState = state;
    }

    public MetroGateState getState() {
        return currentState;
    }
}
