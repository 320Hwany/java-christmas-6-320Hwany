package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.MenuInfo;
import christmas.domain.Order;

import java.util.List;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.MenuTypeConstant.MAIN_MENU;
import static christmas.constant.PriceConstant.WEEKEND_DISCOUNT_UNIT;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;

public class WeekendDiscount implements Discount {

    @Override
    public int applyDiscount(final Order order) {
        int weekendDiscount = ZERO_DISCOUNT.price;
        int expectedVisitDate = order.getExpectedVisitDate();

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            MenuInfo menuInfo = menu.getMenuInfo();
            if (menuInfo.menuType.equals(MAIN_MENU.type) && isWeekend(expectedVisitDate)) {
                int quantity = menu.getQuantity();
                weekendDiscount -= WEEKEND_DISCOUNT_UNIT.price * quantity;
            }
        }

        return weekendDiscount;
    }

    private boolean isWeekend(final int expectedVisitDate) {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return day == FRIDAY.value || day == SATURDAY.value;
    }
}
