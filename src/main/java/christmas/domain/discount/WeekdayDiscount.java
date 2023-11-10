package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.MenuInfo;
import christmas.domain.Order;

import java.util.List;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.MenuTypeConstant.*;
import static christmas.constant.PriceConstant.WEEKDAY_DISCOUNT_UNIT;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;

public class WeekdayDiscount implements Discount {

    @Override
    public int applyDiscount(final Order order) {
        int weekdayDiscount = ZERO_DISCOUNT.price;
        int expectedVisitDate = order.getExpectedVisitDate();

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            MenuInfo menuInfo = menu.getMenuInfo();
            if (menuInfo.menuType.equals(DESSERT.type) && isWeekday(expectedVisitDate)) {
                int quantity = menu.getQuantity();
                weekdayDiscount -= WEEKDAY_DISCOUNT_UNIT.price * quantity;
            }
        }

        return weekdayDiscount;
    }

    private boolean isWeekday(final int expectedVisitDate) {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return !(day == FRIDAY.value || day == SATURDAY.value);
    }
}
