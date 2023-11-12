package christmas.domain.discount;

import christmas.view.DecimalFormatter;

import static christmas.constant.MessageConstant.*;
import static christmas.constant.PriceConstant.ZERO_DISCOUNT;
import static christmas.constant.SymbolConstant.PRICE_UNIT_LINE_BREAK;

public final class DiscountResult {

    private final StringBuilder result = new StringBuilder();

    public boolean isAddChristmasResult(final DecimalFormatter decimalFormatter, final int christmasDiscount) {
        return isAddDiscountResult(decimalFormatter, christmasDiscount, CHRISTMAS_DISCOUNT.message);
    }

    public boolean isAddWeekdayResult(final DecimalFormatter decimalFormatter, final int weekdayDiscount) {
        return isAddDiscountResult(decimalFormatter, weekdayDiscount, WEEKDAY_DISCOUNT.message);
    }

    public boolean isAddWeekendResult(final DecimalFormatter decimalFormatter, final int weekendDiscount) {
        return isAddDiscountResult(decimalFormatter, weekendDiscount, WEEKEND_DISCOUNT.message);
    }

    public boolean isAddSpecialResult(final DecimalFormatter decimalFormatter, final int specialDiscount) {
        return isAddDiscountResult(decimalFormatter, specialDiscount, SPECIAL_DISCOUNT.message);
    }

    public boolean isAddGiveawayResult(final DecimalFormatter decimalFormatter, final int giveawayPrice) {
        if (giveawayPrice != ZERO_DISCOUNT.price) {
            String formattedGiveawayPrice = decimalFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message)
                    .append(formattedGiveawayPrice)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean isAddDiscountResult(final DecimalFormatter decimalFormatter, final int discount,
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

    public StringBuilder getResult() {
        return result;
    }
}
