package christmas;

import christmas.domain.date.ExpectedVisitDate;
import christmas.service.DiscountManager;
import christmas.domain.discount.DiscountPrice;
import christmas.domain.order.Order;
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
        DiscountPrice discountPrice = applyEvent(order);
        processingEventResult(discountPrice, order);
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

    private DiscountPrice applyEvent(final Order order) {
        messagePrinter.printGiveaway(order);
        return discountManager.calculateDiscountPrice(order);
    }

    private void processingEventResult(final DiscountPrice discountPrice, final Order order) {
        messagePrinter.printBenefitResult(discountPrice);
        messagePrinter.printTotalDiscountPrice(discountPrice);
        messagePrinter.printTotalPriceAfterDiscount(discountPrice, order);
        messagePrinter.printEventBadge(discountPrice);
    }
}
