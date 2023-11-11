package christmas.domain.discount;

import christmas.view.DecimalFormatter;

import static christmas.constant.MessageConstant.*;
import static christmas.constant.SymbolConstant.PRICE_UNIT_LINE_BREAK;

public final class DiscountResult {

    private final StringBuilder result = new StringBuilder();

    public boolean isAddChristmasResult(final DecimalFormatter decimalFormatter, final int christmasDiscount) {
        if (christmasDiscount < 0) {
            String formattedChristmasDiscount = decimalFormatter.createFormattedMessage(christmasDiscount);
            result.append(CHRISTMAS_DISCOUNT.message)
                    .append(formattedChristmasDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    public boolean isAddWeekdayResult(final DecimalFormatter decimalFormatter, final int weekdayDiscount) {
        if (weekdayDiscount < 0) {
            String formattedWeekdayDiscount = decimalFormatter.createFormattedMessage(weekdayDiscount);
            result.append(WEEKDAY_DISCOUNT.message)
                    .append(formattedWeekdayDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    public boolean isAddWeekendResult(final DecimalFormatter decimalFormatter, final int weekendDiscount) {
        if (weekendDiscount < 0) {
            String formattedWeekendDiscount = decimalFormatter.createFormattedMessage(weekendDiscount);
            result.append(WEEKEND_DISCOUNT.message)
                    .append(formattedWeekendDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    public boolean isAddSpecialResult(final DecimalFormatter decimalFormatter, final int specialDiscount) {
        if (specialDiscount < 0) {
            String formattedSpecialDiscount = decimalFormatter.createFormattedMessage(specialDiscount);
            result.append(SPECIAL_DISCOUNT.message)
                    .append(formattedSpecialDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    public boolean isAddGiveawayResult(final DecimalFormatter decimalFormatter, final int giveawayPrice) {
        if (giveawayPrice != 0) {
            String formattedGiveawayPrice = decimalFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message)
                    .append(formattedGiveawayPrice)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    public StringBuilder getResult() {
        return result;
    }
}
