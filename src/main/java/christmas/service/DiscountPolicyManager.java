package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.discount.DiscountPrice;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.DiscountInfoConstant.DISCOUNT_TYPE_COUNT;
import static christmas.constant.PriceConstant.*;


public class DiscountPolicyManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountPolicyManager(final List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountPrice calculateDiscountPrice(final Order order) {
        int totalPrice = order.calculateTotalPrice();
        List<Integer> discountPrices = calculateDiscountPrices(order, totalPrice);

        return DiscountPrice.of(totalPrice, discountPrices);
    }

    private List<Integer> calculateDiscountPrices(final Order order, final int totalPrice) {
        List<Integer> discountPrices = new ArrayList<>();

        if (!order.isApplyEvent(totalPrice)) {
            initializeZeroDiscountPrices(discountPrices);
        }
        if (order.isApplyEvent(totalPrice)) {
            calculateDiscountPrices(order, discountPrices);
        }

        return discountPrices;
    }

    private void calculateDiscountPrices(final Order order, final List<Integer> discountPrices) {
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountPrice = discountPolicy.applyDiscount(order);
            discountPrices.add(discountPrice);
        }
    }

    private void initializeZeroDiscountPrices(final List<Integer> discountPrices) {
        for (int i = 0; i < DISCOUNT_TYPE_COUNT.value; i++) {
            discountPrices.add(ZERO_DISCOUNT.price);
        }
    }
}
