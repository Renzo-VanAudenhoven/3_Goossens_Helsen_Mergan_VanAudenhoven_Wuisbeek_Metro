package model.MetroGateStates;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class Inactive implements MetroGateState {

    @Override
    public void activate(StateContext context) {
        System.out.println("Gate is activating");
        context.setState(new Closed());
    }

    @Override
    public String toString() {
        return "Inactive";
    }
}
