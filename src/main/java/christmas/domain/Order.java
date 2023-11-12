package christmas.domain;

import java.util.*;

import static christmas.constant.MessageConstant.GIVE_AWAY_EVENT;
import static christmas.constant.MessageConstant.NOTHING;
import static christmas.constant.PriceConstant.*;

public final class Order {

    private final Menus menus;
    private final ExpectedVisitDate expectedVisitDate;

    public Order(final Menus menus, final ExpectedVisitDate expectedVisitDate) {
        this.menus = menus;
        this.expectedVisitDate = expectedVisitDate;
    }

    public int calculateTotalPrice() {
        return menus.calculateTotalPrice();
    }

    public String calculateGiveaway(final int totalPrice) {
        if (totalPrice >= GIVE_AWAY_CONDITION.price) {
            return GIVE_AWAY_EVENT.message;
        }
        return NOTHING.message;
    }

    public int calculateTotalChristmasDiscount() {
        int basicChristmasDiscount = CHRISTMAS_BASIC_DISCOUNT.price;
        if (expectedVisitDate.isNotChristmasDDay()) {
            return ZERO_DISCOUNT.price;
        }

        int applyDays = expectedVisitDate.calculateApplyDays();
        int christmasDayDiscount = CHRISTMAS_DISCOUNT_UNIT.price * applyDays;

        return basicChristmasDiscount - christmasDayDiscount;
    }

    public int calculateTotalWeekdayDiscount() {
        return menus.calculateTotalWeekdayDiscount(expectedVisitDate);
    }

    public int calculateTotalWeekendDiscount() {
        return menus.calculateTotalWeekendDiscount(expectedVisitDate);
    }

    public int calculateSpecialDayDiscount() {
        if (expectedVisitDate.isSpecialDay()) {
            return SPECIAL_DISCOUNT.price;
        }

        return ZERO_DISCOUNT.price;
    }

    public String createPreviewFormattedMessage() {
        return expectedVisitDate.createPreviewFormattedMessage();
    }

    public List<String> createOrderingMenuMessages() {
        return menus.createOrderingMenuMessages();
    }
}
