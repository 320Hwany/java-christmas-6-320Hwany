package christmas.service.policy;

import christmas.domain.order.Order;

public interface DiscountPolicy {

    int applyDiscount(final Order order);
}
