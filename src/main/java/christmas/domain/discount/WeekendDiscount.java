package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.MenuInfo;
import christmas.domain.Order;

import java.util.List;

import static christmas.constant.DaysConstant.*;

public class WeekendDiscount implements Discount {

    @Override
    public int applyDiscount(final Order order) {
        int weekendDiscount = 0;
        int expectedVisitDate = order.getExpectedVisitDate();

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            MenuInfo menuInfo = menu.getMenuInfo();
            if (menuInfo.menuType.equals("main") && isWeekend(expectedVisitDate)) {
                int quantity = menu.getQuantity();
                weekendDiscount -= 2023 * quantity;
            }
        }

        return weekendDiscount;
    }

    private boolean isWeekend(final int expectedVisitDate) {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return day == FRIDAY.value || day == SATURDAY.value;
    }
}
