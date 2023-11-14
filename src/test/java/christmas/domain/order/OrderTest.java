package christmas.domain.order;

import christmas.domain.date.ExpectedVisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static christmas.constant.PriceConstant.*;
import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 메뉴들의 할인 전 총 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 초코케이크, 5, 3, 10"})
    void calculateTotalPrice(final String menuName1, final String menuName2,
                             final int validQuantity1, final int validQuantity2,
                             final int validDay) {
        // given 1
        Menu menu1 = Menu.createMenu(menuName1, validQuantity1);
        Menu menu2 = Menu.createMenu(menuName2, validQuantity2);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(validDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalPrice = order.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(6000 * 5 + 15000 * 3);
    }

    @DisplayName("12만원을 기준으로 증정 이벤트 적용 대상인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 3, 10, 120000, 119999"})
    void checkGiveaway(final String menuName, final int validQuantity, final int validDay,
                       final int totalPrice1, final int totalPrice2) {
        // given
        Menu menu1 = Menu.createMenu(menuName, validQuantity);
        Order order = Order.of(Menus.from(List.of(menu1)), ExpectedVisitDate.from(validDay));

        // when
        String giveaway1 = order.calculateGiveaway(totalPrice1);
        String giveaway2 = order.calculateGiveaway(totalPrice2);

        // then
        assertThat(giveaway1).isEqualTo("샴페인 1개");
        assertThat(giveaway2).isEqualTo("없음");
    }

    @DisplayName("주문에서 크리스마스 디데이 할인을 적용하고 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 초코케이크, 5, 3, 25"})
    void calculateTotalChristmasDiscount(final String menuName1, final String menuName2,
                                         final int validQuantity1, final int validQuantity2,
                                         final int christmasDay) {
        // given 1
        Menu menu1 = Menu.createMenu(menuName1, validQuantity1);
        Menu menu2 = Menu.createMenu(menuName2, validQuantity2);
        Menus menus = Menus.from(List.of(menu1, menu2));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(christmasDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalChristmasDiscount = order.calculateTotalChristmasDiscount();

        // then
        assertThat(totalChristmasDiscount).isEqualTo(-3400);
    }

    @DisplayName("주문에서 평일 할인을 적용하고 할인 값을 반환한다.")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 초코케이크, 5, 3, 3"})
    void calculateTotalWeekdayDiscount(final String mainMenuName, final String dessertMenuName,
                                       final int validQuantity1, final int validQuantity2,
                                       final int weekday) {
        // given 1
        Menu mainMenu = Menu.createMenu(mainMenuName, validQuantity1);
        Menu dessertMenu = Menu.createMenu(dessertMenuName, validQuantity2);
        Menus menus = Menus.from(List.of(mainMenu, dessertMenu));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(weekday);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalWeekdayDiscount = order.calculateTotalWeekdayDiscount();

        // then
        assertThat(totalWeekdayDiscount).isEqualTo(-WEEKDAY_DISCOUNT_UNIT.price * 3);
    }

    @DisplayName("주문에서 주말 할인을 적용하고 할인 값을 반환한다.")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 초코케이크, 5, 3, 2"})
    void calculateWeekendDiscount(final String mainMenuName, final String dessertMenuName,
                                  final int validQuantity1, final int validQuantity2,
                                  final int weekend) {
        // given 1
        Menu mainMenu = Menu.createMenu(mainMenuName, validQuantity1);
        Menu dessertMenu = Menu.createMenu(dessertMenuName, validQuantity2);
        Menus menus = Menus.from(List.of(mainMenu, dessertMenu));

        // given 2
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
        String mainMenuName = "티본스테이크";
        String dessertMenuName = "초코케이크";
        Menu mainMenu = Menu.createMenu(mainMenuName, 5);
        Menu dessertMenu = Menu.createMenu(dessertMenuName, 3);
        Menus menus = Menus.from(List.of(mainMenu, dessertMenu));

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
        String mainMenuName = "티본스테이크";
        String dessertMenuName = "초코케이크";
        Menu mainMenu = Menu.createMenu(mainMenuName, 5);
        Menu dessertMenu = Menu.createMenu(dessertMenuName, 3);
        Menus menus = Menus.from(List.of(mainMenu, dessertMenu));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(specialDay);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        int totalSpecialDayDiscount = order.calculateSpecialDayDiscount();

        // then
        assertThat(totalSpecialDayDiscount).isEqualTo(0);
    }

    @DisplayName("총 주문 금액에서 이벤트 적용 대상인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"티본스테이크, 초코케이크, 5, 3, 2, 9999, 10000"})
    void isApplyEvent(final String mainMenuName, final String dessertMenuName,
                      final int validQuantity1, final int validQuantity2,
                      final int day, final int notApplyEventTotalPrice,
                      final int applyEventTotalPrice) {

        // given 1
        Menu mainMenu = Menu.createMenu(mainMenuName, validQuantity1);
        Menu dessertMenu = Menu.createMenu(dessertMenuName, validQuantity2);
        Menus menus = Menus.from(List.of(mainMenu, dessertMenu));

        // given 2
        ExpectedVisitDate expectedVisitDate = ExpectedVisitDate.from(day);
        Order order = Order.of(menus, expectedVisitDate);

        // when
        boolean isNotApplyEvent = order.isApplyEvent(notApplyEventTotalPrice);
        boolean isApplyEvent = order.isApplyEvent(applyEventTotalPrice);

        // then
        assertThat(isNotApplyEvent).isFalse();
        assertThat(isApplyEvent).isTrue();
    }
}