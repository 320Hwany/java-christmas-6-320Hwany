package christmas.domain;

public final class Discount {

    private int christmasDiscount;
    private int weekdayDiscount;
    private int weekendDiscount;
    private int specialDiscount;

    public void applyDiscount(final Order order) {
        applyChristmasDiscount(order);
    }

    private void applyChristmasDiscount(final Order order) {
        int basicChristmasDiscount = -1000;
        int expectedVisitDate = order.getExpectedVisitDate();
        christmasDiscount = basicChristmasDiscount - (100 * (expectedVisitDate - 1));
    }
}
