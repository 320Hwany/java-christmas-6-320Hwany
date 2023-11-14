package christmas.constant;

public enum PriceConstant {

    CHRISTMAS_BASIC_DISCOUNT(-1000),
    SPECIAL_DISCOUNT(-1000),
    ZERO_DISCOUNT(0),

    CHRISTMAS_DISCOUNT_UNIT(100),
    WEEKDAY_DISCOUNT_UNIT(2023),
    WEEKEND_DISCOUNT_UNIT(2023),

    GIVEAWAY_BENEFIT(-25000),
    ZERO_BENEFIT(0),

    GIVEAWAY_CONDITION(120000),
    SANTA_BADGE_CONDITION(-20000),
    TREE_BADGE_CONDITION(-10000),
    STAR_BADGE_CONDITION(-5000),
    EVENT_APPLY_CONDITION(10000);

    public final int price;

    PriceConstant(final int price) {
        this.price = price;
    }
}
