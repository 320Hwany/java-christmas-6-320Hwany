package christmas.domain.discount.policy;

import christmas.domain.Order;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.PriceConstant.SPECIAL_DISCOUNT;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;

public class SpecialDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        int expectedVisitDate = order.getExpectedVisitDate();
        if (isSpecialDay(expectedVisitDate)) {
            return SPECIAL_DISCOUNT.price;
        }

        return ZERO_DISCOUNT.price;
    }

    private boolean isSpecialDay(final int expectedVisitDate) {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return day == SPECIAL_DAY.value || expectedVisitDate == CHRISTMAS.value;
    }
}
