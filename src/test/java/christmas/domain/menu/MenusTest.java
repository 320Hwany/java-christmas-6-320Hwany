package christmas.domain.menu;

import christmas.constant.PriceConstant;
import christmas.domain.date.ExpectedVisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constant.PriceConstant.WEEKDAY_DISCOUNT_UNIT;
import static christmas.constant.PriceConstant.WEEKEND_DISCOUNT_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenusTest {

    @DisplayName("주문한 메뉴 개수의 총합이 20개를 초과하면 예외가 발생한다.")
    @Test
    void validateMenusQuantity() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 11);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 중에서 중복된 메뉴가 있으면 예외가 발생한다.")
    @Test
    void validateDuplication() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "양송이수프";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 중에서 음료만 있으면 예외가 발생한다.")
    @Test
    void validateOnlyBeverage() {
        // given
        String menuName1 = "레드와인";
        String menuName2 = "제로콜라";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최대 개수/중복/음료만 주문과 같은 조건에 제한되지 않으면 메뉴 목록이 생성된다.")
    @Test
    void createMenus() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // when
        Menus menus = new Menus(List.of(menu1, menu2));

        // then
        assertThat(menus).isNotNull();
    }

    @DisplayName("메뉴 목록에서 메뉴들의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = new Menus(List.of(menu1, menu2));

        // when
        int totalPrice = menus.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(6000 * 5 + 15000 * 3);
    }

    @DisplayName("메뉴 목록에서 평일 할인을 적용하고 할인 값을 반환한다.")
    @Test
    void calculateTotalWeekdayDiscount() {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = new Menus(List.of(menu1, menu2));

        // given 2
        int weekday = 3;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(weekday);

        // when
        int totalWeekdayDiscount = menus.calculateTotalWeekdayDiscount(expectedVisitDate);

        // then
        assertThat(totalWeekdayDiscount).isEqualTo(-WEEKDAY_DISCOUNT_UNIT.price * 3);
    }

    @DisplayName("메뉴 목록에서 주말 할인을 적용하고 할인 값을 반환한다.")
    @Test
    void calculateWeekendDiscount() {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = new Menus(List.of(menu1, menu2));

        // given 2
        int weekend = 2;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(weekend);

        // when
        int totalWeekdayDiscount = menus.calculateTotalWeekendDiscount(expectedVisitDate);

        // then
        assertThat(totalWeekdayDiscount).isEqualTo(-WEEKEND_DISCOUNT_UNIT.price * 5);
    }
}