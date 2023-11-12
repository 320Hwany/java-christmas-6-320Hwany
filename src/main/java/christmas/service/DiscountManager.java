package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.discount.DiscountPrice;
import christmas.service.policy.DiscountPolicy;

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

        int totalPrice = order.calculateTotalPrice();
        return new DiscountPrice(totalPrice, discountPrices);
    }
}