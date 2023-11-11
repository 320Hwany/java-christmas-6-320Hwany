package christmas.domain.discount;

import christmas.domain.Order;
import christmas.view.DecimalFormatter;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.DiscountInfoConstant.*;
import static christmas.constant.MessageConstant.*;

public final class DiscountPrice {

    private final List<Integer> discountPrices = new ArrayList<>();

    public DiscountPrice(final Order order, final List<Integer> discountPrices) {
        int giveawayPrice = applyGiveawayEvent(order);
        this.discountPrices.addAll(discountPrices);
        this.discountPrices.add(giveawayPrice);
    }

    private int applyGiveawayEvent(final Order order) {
        int giveawayPrice = 0;
        if (order.calculateTotalPrice() > 120000) {
            giveawayPrice = -25000;
        }
        return giveawayPrice;
    }

    public String createBenefitResultText(final DecimalFormatter decimalFormatter) {
        DiscountResult discountResult = new DiscountResult();
        boolean isAddChristmasResult = isAddChristmasResult(decimalFormatter, discountResult);
        boolean isAddWeekdayResult = isAddWeekdayResult(decimalFormatter, discountResult);
        boolean isAddWeekendResult = isAddWeekendResult(decimalFormatter, discountResult);
        boolean isAddSpecialResult = isAddSpecialResult(decimalFormatter, discountResult);
        boolean isAddGiveawayResult = isAddGiveawayResult(decimalFormatter, discountResult);

        if (isAddChristmasResult || isAddWeekdayResult || isAddWeekendResult
                || isAddSpecialResult || isAddGiveawayResult) {
            StringBuilder result = discountResult.getResult();
            return result.toString();
        }

        return NOTHING.message;
    }

    public int calculateTotalBenefitPrice() {
        return discountPrices
                .stream()
                .mapToInt(price -> price)
                .sum();
    }

    public int calculateTotalDiscountPrice() {
        int totalBenefitPrice = discountPrices
                .stream()
                .mapToInt(price -> price)
                .sum();

        int giveawayPrice = discountPrices.get(GIVEAWAY_INDEX.value);

        return totalBenefitPrice - giveawayPrice;
    }

    private boolean isAddChristmasResult(final DecimalFormatter decimalFormatter,
                                          final DiscountResult discountResult) {
        int christmasDiscount = discountPrices.get(CHRISTMAS_DISCOUNT_INDEX.value);
        return discountResult.isAddChristmasResult(decimalFormatter, christmasDiscount);
    }

    private boolean isAddWeekdayResult(final DecimalFormatter decimalFormatter,
                                       final DiscountResult discountResult) {
        int weekdayDiscount = discountPrices.get(WEEKDAY_DISCOUNT_INDEX.value);
        return discountResult.isAddWeekdayResult(decimalFormatter, weekdayDiscount);
    }

    private boolean isAddWeekendResult(final DecimalFormatter decimalFormatter,
                                       final DiscountResult discountResult) {
        int weekendDiscount = discountPrices.get(WEEKEND_DISCOUNT_INDEX.value);
        return discountResult.isAddWeekendResult(decimalFormatter, weekendDiscount);
    }

    private boolean isAddSpecialResult(final DecimalFormatter decimalFormatter,
                                       final DiscountResult discountResult) {
        int specialDiscount = discountPrices.get(SPECIAL_DISCOUNT_INDEX.value);
        return discountResult.isAddSpecialResult(decimalFormatter, specialDiscount);
    }

    private boolean isAddGiveawayResult(final DecimalFormatter decimalFormatter,
                                        final DiscountResult discountResult) {
        int giveawayPrice = discountPrices.get(GIVEAWAY_INDEX.value);
        return discountResult.isAddGiveawayResult(decimalFormatter, giveawayPrice);
    }
}
