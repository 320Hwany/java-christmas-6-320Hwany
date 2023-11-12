package christmas.constant;

public enum PriceConstant {

    CHRISTMAS_BASIC_DISCOUNT(-1000),
    CHRISTMAS_DISCOUNT_UNIT(100),
    WEEKDAY_DISCOUNT_UNIT(2023),
    WEEKEND_DISCOUNT_UNIT(2023),
    SPECIAL_DISCOUNT(-1000),
    GIVEAWAY_BENEFIT(-25000),
    ZERO_BENEFIT(0),
    ZERO_DISCOUNT(0),
    GIVEAWAY_CONDITION(120000);

    public final int price;

    PriceConstant(final int price) {
        this.price = price;
    }
}
