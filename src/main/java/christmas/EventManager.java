package christmas;

import christmas.domain.date.ExpectedVisitDate;
import christmas.service.policy.DiscountPolicyManager;
import christmas.service.DiscountPrice;
import christmas.domain.order.Order;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;

public class EventManager {

    private final MessagePrinter messagePrinter;
    private final MessageReceiver messageReceiver;
    private final DiscountPolicyManager discountPolicyManager;

    public EventManager(final MessagePrinter messagePrinter, final MessageReceiver messageReceiver,
                        final DiscountPolicyManager discountPolicyManager) {
        this.messagePrinter = messagePrinter;
        this.messageReceiver = messageReceiver;
        this.discountPolicyManager = discountPolicyManager;
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
        return discountPolicyManager.calculateDiscountPrice(order);
    }

    private void processingEventResult(final DiscountPrice discountPrice, final Order order) {
        messagePrinter.printBenefitResult(discountPrice);
        messagePrinter.printTotalDiscountPrice(discountPrice);
        messagePrinter.printTotalPriceAfterDiscount(discountPrice, order);
        messagePrinter.printEventBadge(discountPrice);
    }
}
