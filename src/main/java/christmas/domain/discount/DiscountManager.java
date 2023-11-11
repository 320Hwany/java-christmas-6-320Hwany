package christmas.domain.discount;

import christmas.domain.Order;
import christmas.domain.discount.policy.*;

import java.util.ArrayList;
import java.util.List;


public class DiscountManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountManager(final List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountPrice calculateDiscountPrice(final Order order) {
        List<Integer> discountPrices = new ArrayList<>();
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountPrice = discountPolicy.applyDiscount(order);
            discountPrices.add(discountPrice);
        }

        return DiscountPrice.createDiscountPrice(order, discountPrices);
    }
}
