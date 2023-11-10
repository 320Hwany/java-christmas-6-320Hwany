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

    public DiscountPrice calculateDiscountPrice(final Order order) {
        int christmasDiscount = christmasDiscountPolicy.applyDiscount(order);
        int weekdayDiscount = weekdayDiscountPolicy.applyDiscount(order);
        int weekendDiscount = weekendDiscountPolicy.applyDiscount(order);
        int specialDiscount = specialDiscountPolicy.applyDiscount(order);
        int giveawayPrice = 0;
        if (order.calculateTotalPrice() > 120000) {
            giveawayPrice = -25000;
        }

        return new DiscountPrice(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, giveawayPrice);
    }
}
