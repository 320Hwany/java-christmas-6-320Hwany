package christmas.view;

import christmas.domain.order.Order;
import christmas.service.DiscountPrice;

import java.util.List;

import static christmas.constant.MessageConstant.*;
import static christmas.constant.SymbolConstant.*;

public class MessagePrinter {

    private final DecimalFormatter decimalFormatter;

    public MessagePrinter(final DecimalFormatter decimalFormatter) {
        this.decimalFormatter = decimalFormatter;
    }

    public void printGreetingMessage() {
        System.out.println(EVENT_PLANNER_GREETING.message);
    }

    public void printOrderingMenus(final Order order) {
        String formattedMessage = order.createPreviewFormattedMessage();
        System.out.println(formattedMessage);
        System.out.println(ORDER_MENU.message);

        List<String> orderingMenuMessages = order.createOrderingMenuMessages();
        orderingMenuMessages.forEach(System.out::println);
    }

    public void printOrderTotalPrice(final int totalPrice) {
        String formattedTotalPrice = decimalFormatter.createFormattedMessage(totalPrice);
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT.message);

        String orderTotalPriceMessage = formattedTotalPrice + PRICE_UNIT.value;
        System.out.println(orderTotalPriceMessage);
    }

    public void printGiveaway(final Order order) {
        System.out.println(GIVE_AWAY_MENU.message);

        int totalPrice = order.calculateTotalPrice();
        String giveawayResult = order.calculateGiveaway(totalPrice);
        System.out.println(giveawayResult);
    }

    public void printBenefitResult(final DiscountPrice discountPrice) {
        System.out.println(BENEFIT_RESULT.message);

        String benefitResultText = discountPrice.createBenefitResultText(decimalFormatter);
        System.out.println(benefitResultText);
    }

    public void printTotalDiscountPrice(final DiscountPrice discountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE.message);

        int totalDiscountPrice = discountPrice.calculateTotalBenefitPrice();
        String formattedTotalDiscountPrice = decimalFormatter.createFormattedMessage(totalDiscountPrice);
        String totalDiscountPriceMessage = formattedTotalDiscountPrice + PRICE_UNIT.value;
        System.out.println(totalDiscountPriceMessage);
    }

    public void printTotalPriceAfterDiscount(final DiscountPrice discountPrice, final Order order) {
        int totalPrice = order.calculateTotalPrice();
        int totalDiscountPrice = discountPrice.calculateTotalDiscountPrice();
        int totalPriceAfterDiscount = totalPrice + totalDiscountPrice;
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT.message);

        String formattedTotalPriceAfterDiscount = decimalFormatter.createFormattedMessage(totalPriceAfterDiscount);
        String totalPriceAfterDiscountMessage = formattedTotalPriceAfterDiscount + PRICE_UNIT.value;
        System.out.println(totalPriceAfterDiscountMessage);
    }

    public void printEventBadge(final DiscountPrice discountPrice) {
        int totalBenefitPrice = discountPrice.calculateTotalBenefitPrice();
        String eventBadgeText = discountPrice.createEventBadgeText(totalBenefitPrice);

        System.out.println(EVENT_BADGE.message);
        System.out.println(eventBadgeText);
    }
}
