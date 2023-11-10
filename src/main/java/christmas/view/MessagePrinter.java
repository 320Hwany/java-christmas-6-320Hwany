package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.DiscountPrice;

import java.text.DecimalFormat;
import java.util.List;

import static christmas.constant.MessageConstant.*;

public class MessagePrinter {

    public void printGreetingMessage() {
        System.out.println(EVENT_PLANNER_GREETING.message);
    }

    public void printOrderingMenus(final Order order) {
        int expectedVisitDate = order.getExpectedVisitDate();
        String formattedMessage = String.format(ORDER_EVENT_PREVIEW.message, expectedVisitDate);
        System.out.println(formattedMessage);
        System.out.println(ORDER_MENU.message);
        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getMenuName() + " " + menu.getQuantity() + "개");
        }
    }

    public void printOrderTotalPrice(final int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTotalPrice = decimalFormat.format(totalPrice);
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT.message);
        System.out.println(formattedTotalPrice + "원");
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
        int totalDiscountPrice = discountPrice.calculateTotalDiscountPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTotalDiscountPrice = decimalFormat.format(totalDiscountPrice);
        System.out.println(formattedTotalDiscountPrice + "원");
    }
}
