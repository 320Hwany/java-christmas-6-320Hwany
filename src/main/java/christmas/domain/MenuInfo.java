package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuInfo {

    APPETIZER_1("양송이수프", 6000),
    APPETIZER_2("타파스", 5500),
    APPETIZER_3("시저샐러드", 8000),

    MAIN_1("티본스테이크", 55000),
    MAIN_2("바비큐립", 54000),
    MAIN_3("해산물파스타", 35000),
    MAIN_4("크리스마스파스타", 25000),

    DESSERT_1("초코케이크", 15000),
    DESSERT_2("아이스크림", 5000),

    BEVERAGE_1("제로콜라", 3000),
    BEVERAGE_2("레드와인", 60000),
    BEVERAGE_3("샴페인", 25000);

    public final String menuName;
    public final int price;

    MenuInfo(final String menuName, final int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public static MenuInfo createMenuInfo(final String menuName) {
        return validateMenuName(menuName);
    }

    private static MenuInfo validateMenuName(final String menuName) {
        List<MenuInfo> menuInfos = Arrays.asList(values());
        for (MenuInfo menuInfo : menuInfos) {
            if (menuInfo.menuName.equals(menuName)) {
                return menuInfo;
            }
        }

        throw new IllegalArgumentException();
    }
}
