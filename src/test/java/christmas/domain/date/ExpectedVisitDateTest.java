package christmas.domain.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.constant.PriceConstant.SPECIAL_DISCOUNT;
import static org.assertj.core.api.Assertions.*;

class ExpectedVisitDateTest {

    @DisplayName("입력받은 숫자가 해당 범위의 날짜이면 날짜 정보가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void validateExpectedDateSuccess(final int validDay) {
        // when
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(validDay);

        // then
        assertThat(expectedVisitDate).isNotNull();
    }

    @DisplayName("입력받은 숫자가 해당 범위의 날짜가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void validateExpectedDateFail(final int invalidDay) {
        // expected
        assertThatThrownBy(() -> new ExpectedVisitDate(invalidDay))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("크리스마스 디데이 할인을 적용하고 할인 금액을 계산한다.")
    @Test
    void calculateTotalChristmasDiscount() {
        // given
        int christmasDay = 25;
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(christmasDay);

        // when
        int totalChristmasDiscount = expectedVisitDate.calculateTotalChristmasDiscount();

        // then
        assertThat(totalChristmasDiscount).isEqualTo(-3400);
    }

    @DisplayName("크리스마스가 지나면 디데이 할인은 적용되지 않는다.")
    @Test
    void calculateTotalChristmasDiscountNotApply() {
        // given
        int afterChristmasDay = 26;
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(afterChristmasDay);

        // when
        int totalChristmasDiscount = expectedVisitDate.calculateTotalChristmasDiscount();

        // then
        assertThat(totalChristmasDiscount).isEqualTo(0);
    }

    @DisplayName("이벤트 달력에 별이 있는 날짜에 특별 할인을 적용하고 할인 금액을 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void calculateSpecialDayDiscount(final int specialDay) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(specialDay);

        // when
        int totalSpecialDayDiscount = expectedVisitDate.calculateSpecialDayDiscount();

        // then
        assertThat(totalSpecialDayDiscount).isEqualTo(SPECIAL_DISCOUNT.price);
    }

    @DisplayName("이벤트 달력에 별이 있지 않으면 특별 할인은 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 11, 16, 23, 26, 30})
    void calculateSpecialDayDiscountNotApply(final int notSpecialDay) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(notSpecialDay);

        // when
        int totalSpecialDayDiscount = expectedVisitDate.calculateSpecialDayDiscount();

        // then
        assertThat(totalSpecialDayDiscount).isEqualTo(0);
    }

    @DisplayName("해당 날짜가 평일이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void isWeekday(final int weekday) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(weekday);

        // when
        boolean isWeekday = expectedVisitDate.isWeekday();

        // then
        assertThat(isWeekday).isTrue();
    }

    @DisplayName("해당 날짜가 평일이 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void isNotWeekday(final int notWeekday) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(notWeekday);

        // when
        boolean isWeekday = expectedVisitDate.isWeekday();

        // then
        assertThat(isWeekday).isFalse();
    }

    @DisplayName("해당 날짜가 주말이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void isWeekend(final int weekend) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(weekend);

        // when
        boolean isWeekend = expectedVisitDate.isWeekend();

        // then
        assertThat(isWeekend).isTrue();
    }

    @DisplayName("해당 날짜가 주말이 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void isNotWeekend(final int notWeekend) {
        // given
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(notWeekend);

        // when
        boolean isWeekend = expectedVisitDate.isWeekend();

        // then
        assertThat(isWeekend).isFalse();
    }
}