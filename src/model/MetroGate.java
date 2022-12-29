package model;

import model.MetroGateStates.*;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class MetroGate {
    private StateContext context;
    private int gateid;

    public MetroGate(int gateid) {
        this.gateid = gateid;
        this.context = new StateContext();
    }

    public void scanMetroGate(){
        context.getState().scanMetroGate(context);
    }

    public void walkThroughGate(){
        context.getState().walkThroughGate(context);
    }

    public void activate(){
        context.getState().activate(context);
    }

    public void deactivate(){
        context.getState().deactivate(context);
    }

    public void setState(String state) {
        switch (state) {
            case "Inactive":
                context.setState(new Inactive());
                break;
            case "Closed":
                context.setState(new Closed());
                break;
            case "Open":
                context.setState(new Open());
                break;
        }
    }
}
