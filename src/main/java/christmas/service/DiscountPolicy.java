package christmas.service;

import christmas.domain.order.Order;

public interface DiscountPolicy {

    int applyDiscount(final Order order);
}
