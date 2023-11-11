package christmas.domain;

import static christmas.constant.ExceptionConstant.INVALID_ORDER_EXCEPTION;
import static christmas.constant.MenuTypeConstant.DESSERT;
import static christmas.constant.MenuTypeConstant.MAIN_MENU;
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

    private void validateQuantity(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION.message);
        }
    }

    public int calculatePrice() {
        return menuInfo.price * quantity;
    }

    public String createOrderingMenuMessage() {
        return menuInfo.menuName + BLANK.value + quantity + QUANTITY_UNIT.value;
    }

    public int calculateWeekdayDiscount(final ExpectedVisitDate expectedVisitDate) {
        if (menuInfo.menuType.equals(DESSERT.type) && expectedVisitDate.isWeekday()) {
            return WEEKDAY_DISCOUNT_UNIT.price * quantity;
        }

        return ZERO_DISCOUNT.price;
    }

    public int calculateWeekendDiscount(final ExpectedVisitDate expectedVisitDate) {
        if (menuInfo.menuType.equals(MAIN_MENU.type) && expectedVisitDate.isWeekend()) {
            return WEEKEND_DISCOUNT_UNIT.price * quantity;
        }

        return ZERO_DISCOUNT.price;
    }

    public boolean isNotBeverageMenu() {
        boolean isBeverage1 = menuInfo.equals(MenuInfo.BEVERAGE_1);
        boolean isBeverage2 = menuInfo.equals(MenuInfo.BEVERAGE_2);
        boolean isBeverage3 = menuInfo.equals(MenuInfo.BEVERAGE_3);

        return !(isBeverage1 || isBeverage2 || isBeverage3);
    }

    // getter
    public String getMenuName() {
        return menuInfo.menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
