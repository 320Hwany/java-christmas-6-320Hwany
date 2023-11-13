package christmas.domain.order;

import christmas.domain.date.ExpectedVisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static christmas.constant.PriceConstant.*;
import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 메뉴들의 할인 전 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given 1
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        int day = 10;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(day);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalPrice = order.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(6000 * 5 + 15000 * 3);
    }

    @DisplayName("12만원을 기준으로 증정 이벤트 적용 대상인지 확인한다.")
    @Test
    void checkGiveaway() {
        // given
        String menuName = "양송이수프";
        Menu menu1 = Menu.createMenu(menuName, 3);
        Order order = Order.of(Menus.from(List.of(menu1)), ExpectedVisitDate.from(10));

        int totalPrice1 = 120000;
        int totalPrice2 = 119999;

        // when
        String giveaway1 = order.calculateGiveaway(totalPrice1);
        String giveaway2 = order.calculateGiveaway(totalPrice2);

        // then
        assertThat(giveaway1).isEqualTo("샴페인 1개");
        assertThat(giveaway2).isEqualTo("없음");
    }

    @DisplayName("주문에서 크리스마스 디데이 할인을 적용하고 할인 금액을 계산한다.")
    @Test
    void calculateTotalChristmasDiscount() {
        // given 1
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        int christmasDay = 25;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(christmasDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalChristmasDiscount = order.calculateTotalChristmasDiscount();

        // then
        assertThat(totalChristmasDiscount).isEqualTo(-3400);
    }

    @DisplayName("주문에서 평일 할인을 적용하고 할인 값을 반환한다.")
    @Test
    void calculateTotalWeekdayDiscount() {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        int weekday = 3;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(weekday);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalWeekdayDiscount = order.calculateTotalWeekdayDiscount();

        // then
        assertThat(totalWeekdayDiscount).isEqualTo(-WEEKDAY_DISCOUNT_UNIT.price * 3);
    }

    @DisplayName("주문에서 주말 할인을 적용하고 할인 값을 반환한다.")
    @Test
    void calculateWeekendDiscount() {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        int weekend = 2;
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(weekend);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalWeekdayDiscount = order.calculateTotalWeekendDiscount();

        // then
        assertThat(totalWeekdayDiscount).isEqualTo(-WEEKEND_DISCOUNT_UNIT.price * 5);
    }

    @DisplayName("주문한 날짜가 이벤트 달력에 별이 있는 날이면 특별 할인을 적용하고 할인 금액을 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void calculateSpecialDayDiscount(final int specialDay) {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(specialDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalSpecialDayDiscount = order.calculateSpecialDayDiscount();

        // then
        assertThat(totalSpecialDayDiscount).isEqualTo(SPECIAL_DISCOUNT.price);
    }

    @DisplayName("주문한 날짜가 이벤트 달력에 별이 있지 않은 날이면 특별 할인은 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 11, 16, 23, 26, 30})
    void calculateSpecialDayDiscountNotApply(final int specialDay) {
        // given 1
        String menuName1 = "티본스테이크";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 5);
        Menu menu2 = Menu.createMenu(menuName2, 3);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(specialDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalSpecialDayDiscount = order.calculateSpecialDayDiscount();

        // then
        assertThat(totalSpecialDayDiscount).isEqualTo(0);
    }
}