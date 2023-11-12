package christmas.domain.menu;

import christmas.domain.date.ExpectedVisitDate;

import static christmas.constant.ExceptionConstant.INVALID_ORDER_EXCEPTION;
import static christmas.constant.MenuInfoConstant.MINIMUM_MENU_QUANTITY;
import static christmas.constant.PriceConstant.*;
import static christmas.constant.SymbolConstant.BLANK;
import static christmas.constant.SymbolConstant.QUANTITY_UNIT;

public final class Menu {

    private final MenuInfo menuInfo;
    private final int quantity;

    private Menu(final MenuInfo menuInfo, final int quantity) {
        validateQuantity(quantity);
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static Menu createMenu(final String menuName, final int quantity) {
        MenuInfo menuInfo = MenuInfo.createMenuInfo(menuName);
        return new Menu(menuInfo, quantity);
    }

    // validation
    private void validateQuantity(final int quantity) {
        if (quantity < MINIMUM_MENU_QUANTITY.value) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION.message);
        }
    }

    // business
    public int calculatePrice() {
        return menuInfo.price * quantity;
    }

    public String createOrderingMenuMessage() {
        return menuInfo.menuName + BLANK.value + quantity + QUANTITY_UNIT.value;
    }

    public int calculateWeekdayDiscount(final ExpectedVisitDate expectedVisitDate) {
        if (menuInfo.isDessertMenuType() && expectedVisitDate.isWeekday()) {
            return WEEKDAY_DISCOUNT_UNIT.price * quantity;
        }

        return ZERO_DISCOUNT.price;
    }

    public int calculateWeekendDiscount(final ExpectedVisitDate expectedVisitDate) {
        if (menuInfo.isMainMenuType() && expectedVisitDate.isWeekend()) {
            return WEEKEND_DISCOUNT_UNIT.price * quantity;
        }

        return ZERO_DISCOUNT.price;
    }

    public boolean isNotBeverageMenu() {
        return menuInfo.isNotBeverageMenu();
    }

    public int addQuantity(final int sum) {
        return sum + quantity;
    }

    // getter
    public String getMenuName() {
        return menuInfo.menuName;
    }
}
