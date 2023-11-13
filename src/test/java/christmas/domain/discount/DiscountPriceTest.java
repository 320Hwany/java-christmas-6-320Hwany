package christmas.domain.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.DiscountInfoConstant.GIVEAWAY_INDEX;
import static christmas.constant.PriceConstant.*;
import static org.assertj.core.api.Assertions.assertThat;


class DiscountPriceTest {

    @DisplayName("12만원 이상이면 증정 이벤트 적용 대상이다.")
    @ParameterizedTest
    @CsvSource({"120000, -1000, 0, -2023, -1000"})
    void applyGiveawayEvent(final int totalPrice, final int christmasDiscount, final int weekdayDiscount,
                            final int weekendDiscount, final int specialDiscount) {
        // given
        List<Integer> discountPrices = new ArrayList<>();
        discountPrices.add(christmasDiscount);
        discountPrices.add(weekdayDiscount);
        discountPrices.add(weekendDiscount);
        discountPrices.add(specialDiscount);

        // when
        DiscountPrice discountPrice = new DiscountPrice(totalPrice, discountPrices);
        int giveawayPrice = discountPrices.get(GIVEAWAY_INDEX.value);

        // then
        assertThat(giveawayPrice).isEqualTo(GIVEAWAY_BENEFIT.price);
    }

    @DisplayName("12만원 미만이면 증정 이벤트 적용 대상이 아니다.")
    @ParameterizedTest
    @CsvSource({"119999, -1000, 0, -2023, -1000"})
    void notApplyGiveawayEvent(final int totalPrice, final int christmasDiscount, final int weekdayDiscount,
                            final int weekendDiscount, final int specialDiscount) {
        // given
        List<Integer> discountPrices = new ArrayList<>();
        discountPrices.add(christmasDiscount);
        discountPrices.add(weekdayDiscount);
        discountPrices.add(weekendDiscount);
        discountPrices.add(specialDiscount);

        // when
        DiscountPrice discountPrice = new DiscountPrice(totalPrice, discountPrices);
        int giveawayPrice = discountPrices.get(GIVEAWAY_INDEX.value);

        // then
        assertThat(giveawayPrice).isEqualTo(0);
    }
}