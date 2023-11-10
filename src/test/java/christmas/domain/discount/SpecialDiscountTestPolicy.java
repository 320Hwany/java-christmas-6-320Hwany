package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTestPolicy {

    @DisplayName("이벤트 달력에 별이 있는 날짜에 특별 할인을 적용하고 할인 금액을 계산한다.")
    @Test
    void applyDiscount() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(menus, 10);

        SpecialDiscountPolicy specialDiscount = new SpecialDiscountPolicy();

        // when
        int discountPrice = specialDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(-1000);
    }

    @DisplayName("이벤트 달력에 별이 있지 않으면 특별 할인은 적용되지 않는다.")
    @Test
    void applyDiscountLimit() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);
        Order order = new Order(menus, 11);

        SpecialDiscountPolicy specialDiscount = new SpecialDiscountPolicy();

        // when
        int discountPrice = specialDiscount.applyDiscount(order);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}