package model.TicketPriceDecorator;

public class BasicTicketPrice extends TicketPrice{
    private double price = 2.10;
    private String priceText = "basic price of ride is " + price + "€";

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
