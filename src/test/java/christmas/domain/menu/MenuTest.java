package christmas.domain.menu;

import christmas.domain.date.ExpectedVisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.constant.PriceConstant.WEEKDAY_DISCOUNT_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("메뉴 개수는 1이상의 숫자이면 메뉴를 생성한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 1"})
    void validateQuantity(final String menuName, final int validQuantity) {
        // when
        Menu menu = Menu.createMenu(menuName, validQuantity);

        // expected
        assertThat(menu).isNotNull();
    }

    @DisplayName("메뉴 개수는 1이상의 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 0"})
    void validateQuantityFail(final String menuName, final int invalidQuantity) {
        // expected
        assertThatThrownBy(() -> Menu.createMenu(menuName, invalidQuantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 수량과 메뉴 가격으로부터 한 가지 메뉴의 가격을 계산한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 5"})
    void calculatePrice(final String menuName, final int validQuantity) {
        // given
        Menu menu = Menu.createMenu(menuName, validQuantity);

        // when
        int price = menu.calculatePrice();

        // then
        assertThat(price).isEqualTo(6000 * 5);
    }

    @DisplayName("평일 할인 금액을 계산하고 값을 반환한다.")
    @ParameterizedTest
    @CsvSource({"초코케이크, 5, 3, 2"})
    void calculateWeekdayDiscount(final String dessertMenuName, final int validQuantity,
                                  final int weekday, final int weekend) {
        // given
        Menu menu = Menu.createMenu(dessertMenuName, validQuantity);
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
    @ParameterizedTest
    @CsvSource({"양송이수프, 제로콜라, 5"})
    void isNotBeverageMenu(final String notBeverageMenuName, final String beverageMenuName,
                           final int validQuantity) {
        // given
        Menu menu1 = Menu.createMenu(notBeverageMenuName, validQuantity);
        Menu menu2 = Menu.createMenu(beverageMenuName, validQuantity);

        // when
        boolean isNotBeverageMenu1 = menu1.isNotBeverageMenu();
        boolean isNotBeverageMenu2 = menu2.isNotBeverageMenu();

        // then
        assertThat(isNotBeverageMenu1).isTrue();
        assertThat(isNotBeverageMenu2).isFalse();
    }

    @DisplayName("최종 메뉴의 개수에서 해당 메뉴의 개수를 추가하여 반환한다.")
    @ParameterizedTest
    @CsvSource({"양송이수프, 5, 0"})
    void addQuantity(final String menuName, final int validQuantity, final int quantity) {
        // given
        Menu menu = Menu.createMenu(menuName, validQuantity);

        // when
        int totalQuantity = menu.addQuantity(quantity);

        // then
        assertThat(totalQuantity).isEqualTo(5);
    }
}