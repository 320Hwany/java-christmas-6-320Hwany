package christmas.service.policy;

import christmas.domain.order.Order;
import christmas.service.DiscountPrice;

import java.util.ArrayList;
import java.util.List;


public class DiscountPolicyManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountPolicyManager(final List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountPrice calculateDiscountPrice(final Order order) {
        List<Integer> discountPrices = new ArrayList<>();
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountPrice = discountPolicy.applyDiscount(order);
            discountPrices.add(discountPrice);
        }

        int totalPrice = order.calculateTotalPrice();
        return new DiscountPrice(totalPrice, discountPrices);
    }
}
