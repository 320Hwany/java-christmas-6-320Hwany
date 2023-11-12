package christmas.service.policy;

import christmas.domain.order.Order;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateTotalWeekdayDiscount();
    }
}
