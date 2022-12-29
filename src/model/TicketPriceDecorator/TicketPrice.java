package model.TicketPriceDecorator;

import model.MetroCard;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public abstract class TicketPrice {
    private boolean is64Plus;
    private boolean isStudent;
    private String attribute;
    private MetroCard metroCard;

    public boolean isIs64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public abstract double getPrice();
    public abstract String getPriceText();
}
