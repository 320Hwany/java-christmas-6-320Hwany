package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.List;

import static christmas.constant.MessageConstant.EVENT_PLANNER_GREETING;
import static christmas.constant.MessageConstant.ORDER_MENU;

public class MessagePrinter {

    public void printGreetingMessage() {
        System.out.println(EVENT_PLANNER_GREETING.message);
    }

    public void printOrderingMenus(final Order order) {
        System.out.println(ORDER_MENU.message);
        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getMenuName() + " " + menu.getQuantity() + "개");
        }
    }
}
