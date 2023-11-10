package christmas.domain.discount;

import christmas.domain.Order;

public interface Discount {

    int applyDiscount(final Order order);
}
