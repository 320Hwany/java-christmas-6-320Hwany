package christmas.domain.discount;

import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.BadgeConstant.*;
import static christmas.constant.DiscountInfoConstant.*;
import static christmas.constant.MessageConstant.NOTHING;
import static christmas.constant.MessageConstant.NOTHING_LINE_BREAK;
import static christmas.constant.PriceConstant.*;

public final class DiscountPrice {

    private final List<Integer> discountPrices;

    private DiscountPrice(final int totalPrice, final List<Integer> discountPrices) {
        int giveawayPrice = applyGiveawayEvent(totalPrice);
        this.discountPrices = discountPrices;
        this.discountPrices.add(giveawayPrice);
    }

    public static DiscountPrice of(final int totalPrice, final List<Integer> discountPrices) {
        return new DiscountPrice(totalPrice, discountPrices);
    }

    private int applyGiveawayEvent(final int totalPrice) {
        if (totalPrice >= GIVEAWAY_CONDITION.price) {
            return GIVEAWAY_BENEFIT.price;
        }

        return ZERO_BENEFIT.price;
    }

    // business
    public String createBenefitResultText() {
        DiscountResult discountResult = new DiscountResult();
        boolean hasResults = addResults(discountResult);

        if (hasResults) {
            return discountResult.toString();
        }

        return NOTHING_LINE_BREAK.message;
    }

    public String createEventBadgeText(final int totalBenefitPrice) {
        if (totalBenefitPrice <= SANTA_BADGE_CONDITION.price) {
            return SANTA.badge;
        }
        if (totalBenefitPrice <= TREE_BADGE_CONDITION.price) {
            return TREE.badge;
        }
        if (totalBenefitPrice <= STAR_BADGE_CONDITION.price) {
            return STAR.badge;
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

    private boolean addResults(final DiscountResult discountResult) {
        return Stream.of(
                discountResult.addChristmasResult(discountPrices.get(CHRISTMAS_DISCOUNT_INDEX.value)),
                discountResult.addWeekdayResult(discountPrices.get(WEEKDAY_DISCOUNT_INDEX.value)),
                discountResult.addWeekendResult(discountPrices.get(WEEKEND_DISCOUNT_INDEX.value)),
                discountResult.addSpecialResult(discountPrices.get(SPECIAL_DISCOUNT_INDEX.value)),
                discountResult.addGiveawayResult(discountPrices.get(GIVEAWAY_INDEX.value))
        ).anyMatch(Boolean::booleanValue);
    }
}
