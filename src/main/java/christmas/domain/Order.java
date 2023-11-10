package christmas.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Order {

    private final List<Menu> menus;

    public Order(final List<Menu> menus) {
        validateMenusQuantity(menus);
        validateDuplication(menus);
        validateOnlyBeverage(menus);
        this.menus = menus;
    }

    private void validateMenusQuantity(final List<Menu> menus) {
        int sum = 0;
        for (Menu menu : menus) {
            sum += menu.getQuantity();
        }

        if (sum > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(final List<Menu> menus) {
        Set<String> distinctMenuName = new HashSet<>();
        for (Menu menu : menus) {
            String menuName = menu.getMenuName();
            distinctMenuName.add(menuName);
        }

        if (distinctMenuName.size() != menus.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyBeverage(final List<Menu> menus) {
        boolean isOnlyBeverageMenu = true;

        for (Menu menu : menus) {
            MenuInfo menuInfo = menu.getMenuInfo();
            if (isNotBeverageMenu(menuInfo)) {
                isOnlyBeverageMenu = false;
            }
        }

        if (isOnlyBeverageMenu) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotBeverageMenu(final MenuInfo menuInfo) {
        boolean isBeverage1 = menuInfo.equals(MenuInfo.BEVERAGE_1);
        boolean isBeverage2 = menuInfo.equals(MenuInfo.BEVERAGE_2);
        boolean isBeverage3 = menuInfo.equals(MenuInfo.BEVERAGE_3);

        return !(isBeverage1 || isBeverage2 || isBeverage3);
    }

    public boolean isGiveaway() {
        int totalPrice = calculateTotalPrice();
        return totalPrice >= 120000;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : menus) {
            totalPrice += menu.calculatePrice();
        }

        return totalPrice;
    }

    // getter
    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }
}
