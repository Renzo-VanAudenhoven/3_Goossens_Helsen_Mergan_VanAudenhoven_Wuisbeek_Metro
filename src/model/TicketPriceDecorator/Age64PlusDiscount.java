package model.TicketPriceDecorator;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class Age64PlusDiscount extends TicketPriceDiscountDecorator{

    public Age64PlusDiscount(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double getPrice() {
        return ticketPrice.getPrice() - 0.15;
    }

    @Override
    public String getPriceText() {
        return ticketPrice.getPriceText() + " - 0.15€ 64+ age discount";
    }
}
