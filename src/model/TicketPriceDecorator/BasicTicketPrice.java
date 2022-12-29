package model.TicketPriceDecorator;

/**
 * @author Renzo Van Audenhoven & Jan Helsen
 */

public class BasicTicketPrice extends TicketPrice{
    private double price = 2.10;
    private String priceText = "Basic price of ride is " + price + "€";

    public BasicTicketPrice() {

    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getPriceText() {
        return priceText;
    }
}
