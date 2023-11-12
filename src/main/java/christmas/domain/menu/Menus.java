package christmas.domain.menu;

import christmas.domain.date.ExpectedVisitDate;

import java.util.List;

import static christmas.constant.ExceptionConstant.*;
import static christmas.constant.MenuInfoConstant.*;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;
import static java.util.stream.Collectors.toList;

public final class Menus {

    private final List<Menu> menus;

    public Menus(final List<Menu> menus) {
        validateMenusQuantity(menus);
        validateDuplication(menus);
        validateOnlyBeverage(menus);
        this.menus = menus;
    }

    // validation
    private void validateMenusQuantity(final List<Menu> menus) {
        int sum = menus.stream()
                .mapToInt(menu -> menu.addQuantity(ZERO_QUANTITY.value))
                .sum();

        if (sum > MAXIMUM_MENU_QUANTITY.value) {
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

    public int calculateTotalWeekdayDiscount(final ExpectedVisitDate expectedVisitDate) {
        int totalWeekdayDiscount = ZERO_DISCOUNT.price;
        for (Menu menu : menus) {
            int weekdayDiscount = menu.calculateWeekdayDiscount(expectedVisitDate);
            totalWeekdayDiscount -= weekdayDiscount;
        }

        return totalWeekdayDiscount;
    }

    public int calculateTotalWeekendDiscount(final ExpectedVisitDate expectedVisitDate) {
        int totalWeekendDiscount = ZERO_DISCOUNT.price;
        for (Menu menu : menus) {
            int weekendDiscount = menu.calculateWeekendDiscount(expectedVisitDate);
            totalWeekendDiscount -= weekendDiscount;
        }

        return totalWeekendDiscount;
    }

    public List<String> createOrderingMenuMessages() {
        return menus.stream()
                .map(Menu::createOrderingMenuMessage)
                .collect(toList());
    }
}
