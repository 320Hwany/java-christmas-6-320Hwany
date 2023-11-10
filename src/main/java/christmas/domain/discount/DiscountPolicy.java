package christmas.domain.discount;

import christmas.domain.Order;

public interface DiscountPolicy {

    int applyDiscount(final Order order);
}
