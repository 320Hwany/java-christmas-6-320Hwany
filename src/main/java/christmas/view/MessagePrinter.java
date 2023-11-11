package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.DiscountPrice;

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
        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            String orderingMenuMessage = menu.createOrderingMenuMessage();
            System.out.println(orderingMenuMessage);
        }
    }

    public void printOrderTotalPrice(final int totalPrice) {
        String formattedTotalPrice = decimalFormatter.createFormattedMessage(totalPrice);
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT.message);
        System.out.println(formattedTotalPrice + PRICE_UNIT.value);
    }

    public void printGiveaway(final Order order) {
        System.out.println(GIVE_AWAY_MENU.message);
        int totalPrice = order.calculateTotalPrice();
        String giveaway = order.checkGiveaway(totalPrice);
        System.out.println(giveaway);
    }

    public void printBenefitResult(final DiscountPrice discountPrice) {
        System.out.println(BENEFIT_RESULT.message);
        String benefitResultText = discountPrice.createBenefitResultText();
        System.out.println(benefitResultText);
    }

    public void printTotalDiscountPrice(final DiscountPrice discountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE.message);
        int totalDiscountPrice = discountPrice.calculateTotalBenefitPrice();
        String formattedTotalDiscountPrice = decimalFormatter.createFormattedMessage(totalDiscountPrice);
        System.out.println(formattedTotalDiscountPrice + PRICE_UNIT.value);
    }

    public void printTotalPriceAfterDiscount(final DiscountPrice discountPrice, final Order order) {
        int totalPrice = order.calculateTotalPrice();
        int totalDiscountPrice = discountPrice.calculateTotalDiscountPrice();
        int totalPriceAfterDiscount = totalPrice + totalDiscountPrice;
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT.message);

        String formattedTotalPriceAfterDiscount = decimalFormatter.createFormattedMessage(totalPriceAfterDiscount);
        System.out.println(formattedTotalPriceAfterDiscount + PRICE_UNIT.value);
    }

    public void printEventBadge(final DiscountPrice discountPrice) {
        int totalBenefitPrice = discountPrice.calculateTotalBenefitPrice();
        String badge = "없음";
        if (totalBenefitPrice <= -5000) {
            badge = "별";
        }

        if (totalBenefitPrice <= -10000) {
            badge = "트리";
        }

        if (totalBenefitPrice <= -20000) {
            badge = "산타";
        }

        System.out.println(EVENT_BADGE.message);
        System.out.println(badge);
    }
}
