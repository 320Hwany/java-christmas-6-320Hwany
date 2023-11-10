package christmas.view.valid;

import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.ArrayList;
import java.util.List;


public class ViewValidator {

    public int parseInt(final String inputText) {
        try {
            return Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public Order validateOrderInfo(final List<String> orderInfo, final int expectedVisitDate) {
        List<Menu> menus = new ArrayList<>();

        for (String menuText : orderInfo) {
            String menuName = menuText.split("-")[0];
            String quantityText = menuText.split("-")[1];
            int quantity = parseInt(quantityText);
            Menu menu = Menu.createMenu(menuName, quantity);
            menus.add(menu);
        }

        return new Order(menus, expectedVisitDate);
    }
}
