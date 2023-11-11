package christmas.domain.discount.policy;

import christmas.domain.Order;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateTotalWeekdayDiscount();
    }
}
