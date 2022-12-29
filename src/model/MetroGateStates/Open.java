package model.MetroGateStates;

public class Open implements MetroGateState {

    @Override
    public void scanMetroGate(StateContext context) {
        System.out.println("Gate is already open");
        System.out.println("create warning");
    }

    @Override
    public void walkThroughGate(StateContext context) {
        System.out.println("Walking through gate");
        context.setState(new Closed());
    }

    public String toString() {
        return "Open";
    }
}
