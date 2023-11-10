package christmas.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Order {

    private final List<Menu> menus;

    public Order(final List<Menu> menus) {
        validateMenusQuantity(menus);
        validateDuplication(menus);
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
}
