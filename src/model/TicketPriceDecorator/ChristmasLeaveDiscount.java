package model.TicketPriceDecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator{

    public ChristmasLeaveDiscount (TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice() - 0.10;
    }

    @Override
    public String getPriceText() {
        return ticketPrice.getPriceText() + " - 0.10€ Christmas leave discount";
    }
}
