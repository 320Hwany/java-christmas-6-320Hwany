package christmas.domain.discount.policy;

import christmas.domain.Order;

public interface DiscountPolicy {

    int applyDiscount(final Order order);
}
