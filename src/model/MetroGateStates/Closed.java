package model.MetroGateStates;

public class Closed implements MetroGateState {

    @Override
    public void scanMetroGate(StateContext context) {
        System.out.println("Gate is opening");
        context.setState(new Open());
    }

    @Override
    public void walkThroughGate(StateContext context) {
        System.out.println("Trying to walk through closed gate");
        System.out.println("create alert");
    }

    @Override
    public void deactivate(StateContext context) {
        System.out.println("Gate is deactivating");
        context.setState(new Inactive());
    }

    public String toString() {
        return "Closed";
    }
}
