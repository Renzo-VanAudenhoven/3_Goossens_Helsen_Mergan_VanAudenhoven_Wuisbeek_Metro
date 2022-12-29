package model.MetroGateStates;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class Open implements MetroGateState {

    @Override
    public void walkThroughGate(StateContext context) {
        System.out.println("Walking through gate");
        context.setState(new Closed());
    }

    @Override
    public String toString() {
        return "Open";
    }
}
