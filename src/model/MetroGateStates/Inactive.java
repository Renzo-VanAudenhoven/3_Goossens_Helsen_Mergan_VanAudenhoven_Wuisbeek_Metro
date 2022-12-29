package model.MetroGateStates;

public class Inactive implements MetroGateState {

    @Override
    public void activate(StateContext context) {
        System.out.println("Gate is activating");
        context.setState(new Closed());
    }

    public String toString() {
        return "Inactive";
    }
}
