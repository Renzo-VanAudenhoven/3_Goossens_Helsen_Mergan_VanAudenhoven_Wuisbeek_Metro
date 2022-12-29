package model.MetroGateStates;

public interface MetroGateState {

    default void walkThroughGate(StateContext stateContext) {
        throw new IllegalArgumentException("Cannot walk through gate because gate is "+ stateContext.getState());
    }

    default void activate(StateContext stateContext){
        throw new IllegalArgumentException("Cannot activate gate because gate is " + stateContext.getState());
    }

     default void deactivate(StateContext stateContext){
        throw new IllegalArgumentException("Cannot deactivate gate because gate is "+ stateContext.getState());
    }

    default void scanMetroGate(StateContext stateContext){
        throw new IllegalArgumentException("Cannot scan gate because gate is "+ stateContext.getState());
    }


}
