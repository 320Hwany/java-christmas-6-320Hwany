package christmas.constant;

public enum DiscountInfoConstant {

    CHRISTMAS_DISCOUNT_INDEX(0),
    WEEKDAY_DISCOUNT_INDEX(1),
    WEEKEND_DISCOUNT_INDEX(2),
    SPECIAL_DISCOUNT_INDEX(3),
    GIVEAWAY_INDEX(4);

    public final int value;

    DiscountInfoConstant(final int value) {
        this.value = value;
    }
}
