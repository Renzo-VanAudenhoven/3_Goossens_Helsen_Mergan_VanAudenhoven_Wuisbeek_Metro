package model.TicketPriceDecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("Age 64+ discount"),
    CHRISTMASLEAVEDISCOUNT("Christmas leave discount"),
    STUDENTDISCOUNT("Student discount"),
    FREQUENTTRAVELLERDISCOUNT("Frequent traveller discount");

    private String discountName;

    TicketPriceDiscountEnum(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountName() {
        return discountName;
    }
}
