package christmas.constant;

public enum PriceConstant {

    CHRISTMAS_BASIC_DISCOUNT(-1000),
    CHRISTMAS_DISCOUNT_UNIT(100),
    ZERO_DISCOUNT(0);

    public final int price;

    PriceConstant(final int price) {
        this.price = price;
    }
}
