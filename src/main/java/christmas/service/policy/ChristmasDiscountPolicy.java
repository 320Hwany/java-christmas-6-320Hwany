package christmas.service.policy;

import christmas.domain.order.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateTotalChristmasDiscount();
    }
}
