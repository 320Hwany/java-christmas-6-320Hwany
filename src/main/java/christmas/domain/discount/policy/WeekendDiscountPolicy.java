package christmas.domain.discount.policy;

import christmas.domain.Order;

public class WeekendDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateTotalWeekendDiscount();
    }
}
