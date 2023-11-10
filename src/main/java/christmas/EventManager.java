package christmas;

import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.Order;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
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
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        int christmasDiscountPrice = christmasDiscount.applyDiscount(order);
        int weekdayDiscountPrice = weekdayDiscount.applyDiscount(order);
        int weekendDiscountPrice = weekendDiscount.applyDiscount(order);
    }
}
