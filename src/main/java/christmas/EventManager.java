package christmas;

import christmas.domain.Order;
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
        return messageReceiver.receiveOrder();
    }

    private void processingOrder(final Order order) {
        messagePrinter.printOrderingMenus(order);
        int totalPrice = order.calculateTotalPrice();
        messagePrinter.printOrderTotalPrice(totalPrice);
    }

    private void applyEvent(final Order order) {
        boolean isGiveaway = order.isGiveaway();
    }
}
