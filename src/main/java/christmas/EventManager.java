package christmas;

import christmas.domain.discount.DiscountManager;
import christmas.domain.discount.policy.ChristmasDiscountPolicy;
import christmas.domain.Order;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekdayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;

public class EventManager {

    private final MessagePrinter messagePrinter;
    private final MessageReceiver messageReceiver;

    public EventManager(final MessagePrinter messagePrinter, final MessageReceiver messageReceiver) {
        this.messagePrinter = messagePrinter;
        this.messageReceiver = messageReceiver;
    }

    public void manageEvent() {
        Order order = receiveVisitInfo();
        processingOrder(order);
        applyEvent(order);
    }

    private Order receiveVisitInfo() {
        messagePrinter.printGreetingMessage();
        int expectedVisitDate = messageReceiver.receiveExpectedVisitDate();
        return messageReceiver.receiveOrder(expectedVisitDate);
    }

    private void processingOrder(final Order order) {
        messagePrinter.printOrderingMenus(order);
        int totalPrice = order.calculateTotalPrice();
        messagePrinter.printOrderTotalPrice(totalPrice);
    }

    private void applyEvent(final Order order) {
        messagePrinter.printGiveaway(order);
        ChristmasDiscountPolicy christmasDiscount = new ChristmasDiscountPolicy();
        WeekdayDiscountPolicy weekdayDiscount = new WeekdayDiscountPolicy();
        WeekendDiscountPolicy weekendDiscount = new WeekendDiscountPolicy();
        SpecialDiscountPolicy specialDiscount = new SpecialDiscountPolicy();
        DiscountManager discountManager =
                new DiscountManager(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);
        discountManager.applyDiscount(order);
    }
}
