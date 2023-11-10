package christmas.constant;

public enum PriceConstant {

    CHRISTMAS_BASIC_DISCOUNT(-1000),
    CHRISTMAS_DISCOUNT_UNIT(100),
    WEEKDAY_DISCOUNT_UNIT(2023),
    WEEKEND_DISCOUNT_UNIT(2023),
    ZERO_DISCOUNT(0);

    public final int price;

    PriceConstant(final int price) {
        this.price = price;
    }
}
