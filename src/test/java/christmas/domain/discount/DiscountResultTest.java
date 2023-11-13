package christmas.domain.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountResultTest {

    @DisplayName("크리스마스 할인 이벤트가 적용되면 true 적용이 되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"-1, 0"})
    void addChristmasResult(final int christmasDiscount1, final int christmasDiscount2) {
        // given
        DiscountResult discountResult1 = new DiscountResult();
        DiscountResult discountResult2 = new DiscountResult();

        // when
        boolean result1 = discountResult1.addChristmasResult(christmasDiscount1);
        boolean result2 = discountResult2.addChristmasResult(christmasDiscount2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }

    @DisplayName("평일 할인 이벤트가 적용되면 true 적용이 되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"-1, 0"})
    void addWeekdayResult(final int weekdayDiscount1, final int weekdayDiscount2) {
        // given
        DiscountResult discountResult1 = new DiscountResult();
        DiscountResult discountResult2 = new DiscountResult();

        // when
        boolean result1 = discountResult1.addWeekdayResult(weekdayDiscount1);
        boolean result2 = discountResult2.addWeekdayResult(weekdayDiscount2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }

    @DisplayName("주말 할인 이벤트가 적용되면 true 적용이 되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"-1, 0"})
    void addWeekendResult(final int weekendDiscount1, final int weekendDiscount2) {
        // given
        DiscountResult discountResult1 = new DiscountResult();
        DiscountResult discountResult2 = new DiscountResult();

        // when
        boolean result1 = discountResult1.addWeekendResult(weekendDiscount1);
        boolean result2 = discountResult2.addWeekendResult(weekendDiscount2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }

    @DisplayName("스페셜 할인 이벤트가 적용되면 true 적용이 되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"-1, 0"})
    void addSpecialResult(final int specialDiscount1, final int specialDiscount2) {
        // given
        DiscountResult discountResult1 = new DiscountResult();
        DiscountResult discountResult2 = new DiscountResult();

        // when
        boolean result1 = discountResult1.addWeekendResult(specialDiscount1);
        boolean result2 = discountResult2.addWeekendResult(specialDiscount2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }

    @DisplayName("증정 이벤트가 적용되면 true 적용이 되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"-1, 0"})
    void addGiveawayResult(final int giveawayPrice1, final int giveawayPrice2) {
        // given
        DiscountResult discountResult1 = new DiscountResult();
        DiscountResult discountResult2 = new DiscountResult();

        // when
        boolean result1 = discountResult1.addGiveawayResult(giveawayPrice1);
        boolean result2 = discountResult2.addGiveawayResult(giveawayPrice2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}