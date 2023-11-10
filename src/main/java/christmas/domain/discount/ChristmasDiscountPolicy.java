package christmas.domain.discount;

import christmas.domain.Order;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.PriceConstant.*;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    @Override
    public int applyDiscount(final Order order) {
        int basicChristmasDiscount = CHRISTMAS_BASIC_DISCOUNT.price;
        int expectedVisitDate = order.getExpectedVisitDate();
        if (expectedVisitDate > CHRISTMAS.value) {
            return ZERO_DISCOUNT.price;
        }

        int christmasDayDiscount = CHRISTMAS_DISCOUNT_UNIT.price * (expectedVisitDate - 1);
        return basicChristmasDiscount - christmasDayDiscount;
    }
}
