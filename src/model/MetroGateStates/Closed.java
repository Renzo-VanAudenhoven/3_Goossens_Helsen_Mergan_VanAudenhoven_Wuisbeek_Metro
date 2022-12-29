package model.MetroGateStates;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class Closed implements MetroGateState {

    @Override
    public void scanMetroGate(StateContext context) {
        System.out.println("Gate is opening");
        context.setState(new Open());
    }

    @Override
    public void deactivate(StateContext context) {
        System.out.println("Gate is deactivating");
        context.setState(new Inactive());
    }

    @Override
    public String toString() {
        return "Closed";
    }
}
