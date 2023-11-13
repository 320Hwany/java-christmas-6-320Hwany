package christmas.domain.menu;

import christmas.domain.date.ExpectedVisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.PriceConstant.WEEKDAY_DISCOUNT_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("메뉴 개수는 1이상의 숫자이면 메뉴를 생성한다.")
    @Test
    void validateQuantity() {
        // given
        String menuName = "양송이수프";
        int quantity = 1;

        // when
        Menu menu = Menu.createMenu(menuName, quantity);

        // expected
        assertThat(menu).isNotNull();
    }

    @DisplayName("메뉴 개수는 1이상의 숫자가 아니면 예외가 발생한다.")
    @Test
    void validateQuantityFail() {
        // given
        String menuName = "양송이수프";
        int quantity = 0;

        // expected
        assertThatThrownBy(() -> Menu.createMenu(menuName, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 수량과 메뉴 가격으로부터 한 가지 메뉴의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        Menu menu = Menu.createMenu("양송이수프", 5);

        // when
        int price = menu.calculatePrice();

        // then
        assertThat(price).isEqualTo(6000 * 5);
    }

    @DisplayName("평일 할인 금액을 계산하고 값을 반환한다.")
    @Test
    void calculateWeekdayDiscount() {
        // given
        Menu menu = Menu.createMenu("초코케이크", 5);
        int weekday = 3;
        int weekend = 2;
        ExpectedVisitDate expectedVisitDate1 = ExpectedVisitDate.from(weekday);
        ExpectedVisitDate expectedVisitDate2 = ExpectedVisitDate.from(weekend);

        // when
        int weekdayDiscount1 = menu.calculateWeekdayDiscount(expectedVisitDate1);
        int weekdayDiscount2 = menu.calculateWeekdayDiscount(expectedVisitDate2);

        // then
        assertThat(weekdayDiscount1).isEqualTo(WEEKDAY_DISCOUNT_UNIT.price * 5);
        assertThat(weekdayDiscount2).isEqualTo(0);
    }

    @DisplayName("메뉴의 종류가 음료인지 아닌지 확인한다.")
    @Test
    void isNotBeverageMenu() {
        // given
        Menu menu1 = Menu.createMenu("양송이수프", 5);
        Menu menu2 = Menu.createMenu("제로콜라", 5);

        // when
        boolean isNotBeverageMenu1 = menu1.isNotBeverageMenu();
        boolean isNotBeverageMenu2 = menu2.isNotBeverageMenu();

        // then
        assertThat(isNotBeverageMenu1).isTrue();
        assertThat(isNotBeverageMenu2).isFalse();
    }

    @DisplayName("최종 메뉴의 개수에서 해당 메뉴의 개수를 추가하여 반환한다.")
    @Test
    void addQuantity() {
        // given
        Menu menu = Menu.createMenu("양송이수프", 5);
        int totalQuantity = 0;

        // when
        int quantity = menu.addQuantity(totalQuantity);

        // then
        assertThat(quantity).isEqualTo(5);
    }
}