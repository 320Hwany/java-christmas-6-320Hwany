package christmas.domain.discount;

import christmas.domain.Order;

public class ChristmasDiscount implements Discount {

    @Override
    public int applyDiscount(final Order order) {
        int basicChristmasDiscount = -1000;
        int expectedVisitDate = order.getExpectedVisitDate();
        int discountDay = Math.min(expectedVisitDate, 25);

        return basicChristmasDiscount - (100 * (discountDay - 1));
    }
}
