package christmas.domain;

import java.util.List;

public final class Order {

    private final List<Menu> menus;

    public Order(final List<Menu> menus) {
        validateMenusQuantity(menus);
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
}
