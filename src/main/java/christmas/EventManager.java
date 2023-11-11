package christmas;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.discount.DiscountManager;
import christmas.domain.discount.DiscountPrice;
import christmas.domain.Order;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;

public class EventManager {

    private final MessagePrinter messagePrinter;
    private final MessageReceiver messageReceiver;
    private final DiscountManager discountManager;

    public EventManager(final MessagePrinter messagePrinter, final MessageReceiver messageReceiver,
                        final DiscountManager discountManager) {
        this.messagePrinter = messagePrinter;
        this.messageReceiver = messageReceiver;
        this.discountManager = discountManager;
    }

    public void manageEvent() {
        Order order = receiveVisitInfo();
        processingOrder(order);
        applyEvent(order);
        processEventResult(discountManager, order);
    }

    private Order receiveVisitInfo() {
        messagePrinter.printGreetingMessage();
        ExpectedVisitDate expectedVisitDate = messageReceiver.receiveExpectedVisitDate();
        return messageReceiver.receiveOrder(expectedVisitDate);
    }

    private void processingOrder(final Order order) {
        messagePrinter.printOrderingMenus(order);
        int totalPrice = order.calculateTotalPrice();
        messagePrinter.printOrderTotalPrice(totalPrice);
    }

    private void applyEvent(final Order order) {
        messagePrinter.printGiveaway(order);
    }

    private void processEventResult(final DiscountManager discountManager, final Order order) {
        DiscountPrice discountPrice = discountManager.calculateDiscountPrice(order);
        messagePrinter.printBenefitResult(discountPrice);
        messagePrinter.printTotalDiscountPrice(discountPrice);
        messagePrinter.printTotalPriceAfterDiscount(discountPrice, order);
        messagePrinter.printEventBadge(discountPrice);
    }
}
