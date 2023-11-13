package christmas.domain.discount;

import christmas.view.DecimalFormatter;

import static christmas.constant.MessageConstant.*;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;
import static christmas.constant.SymbolConstant.PRICE_UNIT_LINE_BREAK;

public final class DiscountResult {

    private final StringBuilder result;

    public DiscountResult() {
        this.result = new StringBuilder();
    }

    public boolean addChristmasResult(final DecimalFormatter decimalFormatter, final int christmasDiscount) {
        return addDiscountResult(decimalFormatter, christmasDiscount, CHRISTMAS_DISCOUNT.message);
    }

    public boolean addWeekdayResult(final DecimalFormatter decimalFormatter, final int weekdayDiscount) {
        return addDiscountResult(decimalFormatter, weekdayDiscount, WEEKDAY_DISCOUNT.message);
    }

    public boolean addWeekendResult(final DecimalFormatter decimalFormatter, final int weekendDiscount) {
        return addDiscountResult(decimalFormatter, weekendDiscount, WEEKEND_DISCOUNT.message);
    }

    public boolean addSpecialResult(final DecimalFormatter decimalFormatter, final int specialDiscount) {
        return addDiscountResult(decimalFormatter, specialDiscount, SPECIAL_DISCOUNT.message);
    }

    public boolean addGiveawayResult(final DecimalFormatter decimalFormatter, final int giveawayPrice) {
        if (giveawayPrice != ZERO_DISCOUNT.price) {
            String formattedGiveawayPrice = decimalFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message)
                    .append(formattedGiveawayPrice)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean addDiscountResult(final DecimalFormatter decimalFormatter, final int discount,
                                      final String discountMessage) {
        if (discount < ZERO_DISCOUNT.price) {
            String formattedDiscountMessage = decimalFormatter.createFormattedMessage(discount);
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
