package model.MetroGateStates;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public interface MetroGateState {

    default void walkThroughGate(StateContext stateContext) {
        throw new IllegalArgumentException("Gate is "+ stateContext.getState());
    }

    default void activate(StateContext stateContext){
        throw new IllegalArgumentException("Gate is " + stateContext.getState());
    }

     default void deactivate(StateContext stateContext){
        throw new IllegalArgumentException("Gate is " + stateContext.getState());
    }

    default void scanMetroGate(StateContext stateContext){
        throw new IllegalArgumentException("Gate is " + stateContext.getState());
    }

    @Override
    String toString();

}
