package christmas.domain.order;

import christmas.domain.date.ExpectedVisitDate;
import christmas.domain.menu.Menus;

import java.util.*;

import static christmas.constant.MessageConstant.GIVE_AWAY_EVENT;
import static christmas.constant.MessageConstant.NOTHING;
import static christmas.constant.PriceConstant.*;

public final class Order {

    private final Menus menus;
    private final ExpectedVisitDate expectedVisitDate;

    private Order(final Menus menus, final ExpectedVisitDate expectedVisitDate) {
        this.menus = menus;
        this.expectedVisitDate = expectedVisitDate;
    }

    public static Order of(final Menus menus, final ExpectedVisitDate expectedVisitDate) {
        return new Order(menus, expectedVisitDate);
    }

    // business
    public int calculateTotalPrice() {
        return menus.calculateTotalPrice();
    }

    public String calculateGiveaway(final int totalPrice) {
        if (totalPrice >= GIVEAWAY_CONDITION.price) {
            return GIVE_AWAY_EVENT.message;
        }
        return NOTHING.message;
    }

    public int calculateTotalChristmasDiscount() {
        return expectedVisitDate.calculateTotalChristmasDiscount();
    }

    public int calculateTotalWeekdayDiscount() {
        return menus.calculateTotalWeekdayDiscount(expectedVisitDate);
    }

    public int calculateTotalWeekendDiscount() {
        return menus.calculateTotalWeekendDiscount(expectedVisitDate);
    }

    public int calculateSpecialDayDiscount() {
        return expectedVisitDate.calculateSpecialDayDiscount();
    }

    public String createPreviewFormattedMessage() {
        return expectedVisitDate.createPreviewFormattedMessage();
    }

    public List<String> createOrderingMenuMessages() {
        return menus.createOrderingMenuMessages();
    }
}
