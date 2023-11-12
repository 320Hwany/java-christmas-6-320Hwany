package christmas.domain.discount;

import christmas.view.DecimalFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.DiscountInfoConstant.*;
import static christmas.constant.MessageConstant.*;
import static christmas.constant.PriceConstant.*;

public final class DiscountPrice {

    private final List<Integer> discountPrices = new ArrayList<>();

    public DiscountPrice(final int totalPrice, final List<Integer> discountPrices) {
        int giveawayPrice = applyGiveawayEvent(totalPrice);
        this.discountPrices.addAll(discountPrices);
        this.discountPrices.add(giveawayPrice);
    }

    // validation
    private int applyGiveawayEvent(final int totalPrice) {
        if (totalPrice > GIVEAWAY_CONDITION.price) {
            return GIVEAWAY_BENEFIT.price;
        }

        return ZERO_BENEFIT.price;
    }

    // business
    public String createBenefitResultText(final DecimalFormatter decimalFormatter) {
        DiscountResult discountResult = new DiscountResult();
        boolean hasResults = addResults(decimalFormatter, discountResult);

        if (hasResults) {
            return discountResult.toString();
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

    private boolean addResults(final DecimalFormatter decimalFormatter, final DiscountResult discountResult) {
        return Stream.of(
                discountResult.addChristmasResult(decimalFormatter,
                        discountPrices.get(CHRISTMAS_DISCOUNT_INDEX.value)),
                discountResult.addWeekdayResult(decimalFormatter,
                        discountPrices.get(WEEKDAY_DISCOUNT_INDEX.value)),
                discountResult.addWeekendResult(decimalFormatter,
                        discountPrices.get(WEEKEND_DISCOUNT_INDEX.value)),
                discountResult.addSpecialResult(decimalFormatter,
                        discountPrices.get(SPECIAL_DISCOUNT_INDEX.value)),
                discountResult.addGiveawayResult(decimalFormatter,
                        discountPrices.get(GIVEAWAY_INDEX.value))
        ).anyMatch(Boolean::booleanValue);
    }
}
