package christmas.domain.discount;

import christmas.domain.Order;
import christmas.domain.discount.policy.ChristmasDiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekdayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;

public class DiscountManager {

    private final ChristmasDiscountPolicy christmasDiscountPolicy;
    private final WeekdayDiscountPolicy weekdayDiscountPolicy;
    private final WeekendDiscountPolicy weekendDiscountPolicy;
    private final SpecialDiscountPolicy specialDiscountPolicy;

    public DiscountManager(final ChristmasDiscountPolicy christmasDiscountPolicy,
                           final WeekdayDiscountPolicy weekdayDiscountPolicy,
                           final WeekendDiscountPolicy weekendDiscountPolicy,
                           final SpecialDiscountPolicy specialDiscountPolicy) {
        this.christmasDiscountPolicy = christmasDiscountPolicy;
        this.weekdayDiscountPolicy = weekdayDiscountPolicy;
        this.weekendDiscountPolicy = weekendDiscountPolicy;
        this.specialDiscountPolicy = specialDiscountPolicy;
    }

    public void applyDiscount(final Order order) {
        christmasDiscountPolicy.applyDiscount(order);
        weekdayDiscountPolicy.applyDiscount(order);
        weekendDiscountPolicy.applyDiscount(order);
        specialDiscountPolicy.applyDiscount(order);
    }
}
