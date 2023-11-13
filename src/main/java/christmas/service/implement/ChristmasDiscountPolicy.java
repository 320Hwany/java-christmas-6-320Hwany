package christmas.service.implement;

import christmas.domain.order.Order;
import christmas.service.DiscountPolicy;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateTotalChristmasDiscount();
    }
}
