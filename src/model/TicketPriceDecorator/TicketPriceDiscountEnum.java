package model.TicketPriceDecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT(0.5),
    CHRISTMASLEAVEDISCOUNT(0.5),
    STUDENTDISCOUNT(0.5),
    FREQUENTTRAVELLERDISCOUNT(0.5);

    private double discount;

    TicketPriceDiscountEnum(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
