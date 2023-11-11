package christmas.domain;

import java.util.*;

import static christmas.constant.ExceptionConstant.*;
import static christmas.constant.MessageConstant.GIVE_AWAY_EVENT;
import static christmas.constant.MessageConstant.NOTHING;
import static christmas.constant.PriceConstant.*;
import static java.util.stream.Collectors.toList;

public final class Order {

    private final List<Menu> menus;
    private final ExpectedVisitDate expectedVisitDate;

    public Order(final List<Menu> menus, final ExpectedVisitDate expectedVisitDate) {
        validateMenusQuantity(menus);
        validateDuplication(menus);
        validateOnlyBeverage(menus);
        this.menus = menus;
        this.expectedVisitDate = expectedVisitDate;
    }

    // validation
    private void validateMenusQuantity(final List<Menu> menus) {
        int sum = menus.stream()
                .mapToInt(Menu::getQuantity)
                .sum();

        if (sum > 20) {
            throw new IllegalArgumentException(MAX_MENU_QUANTITY_EXCEPTION.message);
        }
    }

    private void validateDuplication(final List<Menu> menus) {
        long distinctMenuCount = menus.stream()
                .map(Menu::getMenuName)
                .distinct()
                .count();

        if (distinctMenuCount != menus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION.message);
        }
    }

    private void validateOnlyBeverage(final List<Menu> menus) {
        boolean hasNonBeverageMenu = menus.stream()
                .anyMatch(Menu::isNotBeverageMenu);

        if (!hasNonBeverageMenu) {
            throw new IllegalArgumentException(ONLY_BEVERAGE_ORDER_EXCEPTION.message);
        }
    }

    // business
    public int calculateTotalPrice() {
        return menus.stream()
                .mapToInt(Menu::calculatePrice)
                .sum();
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
        int totalWeekdayDiscount = ZERO_DISCOUNT.price;
        for (Menu menu : menus) {
            int weekdayDiscount = menu.calculateWeekdayDiscount(expectedVisitDate);
            totalWeekdayDiscount -= weekdayDiscount;
        }

        return totalWeekdayDiscount;
    }

    public int calculateTotalWeekendDiscount() {
        int totalWeekendDiscount = ZERO_DISCOUNT.price;
        for (Menu menu : menus) {
            int weekendDiscount = menu.calculateWeekendDiscount(expectedVisitDate);
            totalWeekendDiscount -= weekendDiscount;
        }

        return totalWeekendDiscount;
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
        return menus.stream()
                .map(Menu::createOrderingMenuMessage)
                .collect(toList());
    }
}
