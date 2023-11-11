package christmas.domain.discount;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.policy.ChristmasDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ChristmasDiscountTestPolicy {

    @DisplayName("크리스마스 디데이 할인을 적용하고 할인 금액을 계산한다.")
    @Test
    void applyDiscount() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(menus, new ExpectedVisitDate(10));

        ChristmasDiscountPolicy christmasDiscount = new ChristmasDiscountPolicy();

        // when
        int discountPrice = christmasDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(-1900);
    }

    @DisplayName("크리스마스가 지나면 디데이 할인은 적용되지 않는다.")
    @Test
    void applyDiscountLimit() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(menus, new ExpectedVisitDate(26));

        ChristmasDiscountPolicy christmasDiscount = new ChristmasDiscountPolicy();

        // when
        int discountPrice = christmasDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}