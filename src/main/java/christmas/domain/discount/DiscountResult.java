package christmas.domain.discount;

import christmas.view.PriceFormatter;

import static christmas.constant.MessageConstant.*;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;
import static christmas.constant.SymbolConstant.PRICE_UNIT_LINE_BREAK;

public final class DiscountResult {

    private final StringBuilder result;

    public DiscountResult() {
        this.result = new StringBuilder();
    }

    public boolean addChristmasResult(final int christmasDiscount) {
        return addDiscountResult(christmasDiscount, CHRISTMAS_DISCOUNT.message);
    }

    public boolean addWeekdayResult(final int weekdayDiscount) {
        return addDiscountResult(weekdayDiscount, WEEKDAY_DISCOUNT.message);
    }

    public boolean addWeekendResult(final int weekendDiscount) {
        return addDiscountResult(weekendDiscount, WEEKEND_DISCOUNT.message);
    }

    public boolean addSpecialResult(final int specialDiscount) {
        return addDiscountResult(specialDiscount, SPECIAL_DISCOUNT.message);
    }

    public boolean addGiveawayResult(final int giveawayPrice) {
        if (giveawayPrice != ZERO_DISCOUNT.price) {
            String formattedGiveawayPrice = PriceFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message)
                    .append(formattedGiveawayPrice)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean addDiscountResult(final int discount, final String discountMessage) {
        if (discount < ZERO_DISCOUNT.price) {
            String formattedDiscountMessage = PriceFormatter.createFormattedMessage(discount);
            result.append(discountMessage)
                    .append(formattedDiscountMessage)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
