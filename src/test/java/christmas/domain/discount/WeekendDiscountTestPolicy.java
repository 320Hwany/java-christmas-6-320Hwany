package christmas.domain.discount;

import christmas.domain.date.ExpectedVisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.domain.order.Order;
import christmas.service.policy.WeekendDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountTestPolicy {

    @DisplayName("주말 할인을 적용하고 할인 금액을 계산한다.")
    @Test
    void applyDiscount() {
        // given
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(new Menus(menus), new ExpectedVisitDate(9));

        WeekendDiscountPolicy weekendDiscount = new WeekendDiscountPolicy();

        // when
        int discountPrice = weekendDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(-2023 * 5);
    }

    @DisplayName("평일이 아니면 평일 할인이 적용되지 않는다.")
    @Test
    void notApplyDiscount() {
        // given
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(new Menus(menus), new ExpectedVisitDate(10));

        WeekendDiscountPolicy weekendDiscount = new WeekendDiscountPolicy();

        // when
        int discountPrice = weekendDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}