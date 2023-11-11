package christmas.domain.discount.policy;

import christmas.domain.Order;


public class SpecialDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        return order.calculateSpecialDayDiscount();
    }
}
