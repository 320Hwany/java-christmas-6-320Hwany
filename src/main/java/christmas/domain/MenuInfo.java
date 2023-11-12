package christmas.domain;

import java.util.Arrays;
import java.util.List;

import static christmas.constant.ExceptionConstant.INVALID_ORDER_EXCEPTION;
import static christmas.constant.MenuTypeConstant.DESSERT;
import static christmas.constant.MenuTypeConstant.MAIN_MENU;

public enum MenuInfo {

    APPETIZER_1("appetizer", "양송이수프", 6000),
    APPETIZER_2("appetizer", "타파스", 5500),
    APPETIZER_3("appetizer", "시저샐러드", 8000),

    MAIN_1("main", "티본스테이크", 55000),
    MAIN_2("main", "바비큐립", 54000),
    MAIN_3("main", "해산물파스타", 35000),
    MAIN_4("main", "크리스마스파스타", 25000),

    DESSERT_1("dessert", "초코케이크", 15000),
    DESSERT_2("dessert", "아이스크림", 5000),

    BEVERAGE_1("beverage", "제로콜라", 3000),
    BEVERAGE_2("beverage", "레드와인", 60000),
    BEVERAGE_3("beverage", "샴페인", 25000);

    public final String menuType;
    public final String menuName;
    public final int price;

    MenuInfo(final String menuType, final String menuName, final int price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }

    public static MenuInfo createMenuInfo(final String menuName) {
        return validateMenuName(menuName);
    }

    // validation
    private static MenuInfo validateMenuName(final String menuName) {
        List<MenuInfo> menuInfos = Arrays.asList(values());
        for (MenuInfo menuInfo : menuInfos) {
            if (menuInfo.menuName.equals(menuName)) {
                return menuInfo;
            }
        }

        throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION.message);
    }

    // business
    public boolean isDessertMenuType() {
        return menuType.equals(DESSERT.type);
    }

    public boolean isMainMenuType() {
        return menuType.equals(MAIN_MENU.type);
    }

    public boolean isNotBeverageMenu() {
        boolean isBeverage1 = this.equals(MenuInfo.BEVERAGE_1);
        boolean isBeverage2 = this.equals(MenuInfo.BEVERAGE_2);
        boolean isBeverage3 = this.equals(MenuInfo.BEVERAGE_3);

        return !(isBeverage1 || isBeverage2 || isBeverage3);
    }
}
