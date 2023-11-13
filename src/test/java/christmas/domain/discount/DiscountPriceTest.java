package christmas.domain.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.BadgeConstant.*;
import static christmas.constant.DiscountInfoConstant.GIVEAWAY_INDEX;
import static christmas.constant.MessageConstant.NOTHING;
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

    @DisplayName("총 혜택 금액에 따른 이벤트 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({"120000, -20000, -10000, -5000, -4999"})
    void createEventBadgeText(final int totalPrice, final int totalBenefitPrice1, final int totalBenefitPrice2,
                              final int totalBenefitPrice3,  final int totalBenefitPrice4) {
        // given
        List<Integer> discountPrices = new ArrayList<>();
        DiscountPrice discountPrice = new DiscountPrice(totalPrice, discountPrices);

        // when
        String eventBadgeText1 = discountPrice.createEventBadgeText(totalBenefitPrice1);
        String eventBadgeText2 = discountPrice.createEventBadgeText(totalBenefitPrice2);
        String eventBadgeText3 = discountPrice.createEventBadgeText(totalBenefitPrice3);
        String eventBadgeText4 = discountPrice.createEventBadgeText(totalBenefitPrice4);

        // then
        assertThat(eventBadgeText1).isEqualTo(SANTA.badge);
        assertThat(eventBadgeText2).isEqualTo(TREE.badge);
        assertThat(eventBadgeText3).isEqualTo(STAR.badge);
        assertThat(eventBadgeText4).isEqualTo(NOTHING.message);
    }

    @DisplayName("증정 이벤트를 포함한 총 혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"120000, -1000, 0, -2023, -1000"})
    void calculateTotalBenefitPrice(final int totalPrice, final int christmasDiscount,
                                    final int weekdayDiscount, final int weekendDiscount,
                                    final int specialDiscount) {
        // given
        List<Integer> discountPrices = new ArrayList<>();
        discountPrices.add(christmasDiscount);
        discountPrices.add(weekdayDiscount);
        discountPrices.add(weekendDiscount);
        discountPrices.add(specialDiscount);

        DiscountPrice discountPrice = new DiscountPrice(totalPrice, discountPrices);

        // when
        int totalBenefitPrice = discountPrice.calculateTotalBenefitPrice();


        // then
        int expectedPrice = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount - 25000;
        assertThat(totalBenefitPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("증정 이벤트를 제외한 총 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"120000, -1000, 0, -2023, -1000"})
    void calculateTotalDiscountPrice(final int totalPrice, final int christmasDiscount,
                                    final int weekdayDiscount, final int weekendDiscount,
                                    final int specialDiscount) {
        // given
        List<Integer> discountPrices = new ArrayList<>();
        discountPrices.add(christmasDiscount);
        discountPrices.add(weekdayDiscount);
        discountPrices.add(weekendDiscount);
        discountPrices.add(specialDiscount);

        DiscountPrice discountPrice = new DiscountPrice(totalPrice, discountPrices);

        // when
        int totalDiscountPrice = discountPrice.calculateTotalDiscountPrice();

        // then
        int expectedPrice = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        assertThat(totalDiscountPrice).isEqualTo(expectedPrice);
    }
}