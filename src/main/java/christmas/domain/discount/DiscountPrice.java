package christmas.domain.discount;

import christmas.domain.Order;
import christmas.view.DecimalFormatter;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.DiscountInfoConstant.*;
import static christmas.constant.MessageConstant.*;
import static christmas.constant.SymbolConstant.PRICE_UNIT_LINE_BREAK;

public final class DiscountPrice {

    private final List<Integer> discountPriceList = new ArrayList<>();
    private final StringBuilder result = new StringBuilder();

    public DiscountPrice(final Order order, final List<Integer> discountPrices) {
        int giveawayPrice = applyGiveawayEvent(order);
        discountPriceList.addAll(discountPrices);
        discountPriceList.add(giveawayPrice);
    }

    private int applyGiveawayEvent(final Order order) {
        int giveawayPrice = 0;
        if (order.calculateTotalPrice() > 120000) {
            giveawayPrice = -25000;
        }
        return giveawayPrice;
    }

    public String createBenefitResultText(final DecimalFormatter decimalFormatter) {
        boolean isAddChristmasResult = isAddChristmasResult(decimalFormatter);
        boolean isAddWeekdayResult = isAddWeekdayResult(decimalFormatter);
        boolean isAddWeekendResult = isAddWeekendResult(decimalFormatter);
        boolean isAddSpecialResult = isAddSpecialResult(decimalFormatter);
        boolean isAddGiveawayResult = isAddGiveawayResult(decimalFormatter);

        if (isAddChristmasResult || isAddWeekdayResult || isAddWeekendResult
                || isAddSpecialResult || isAddGiveawayResult) {
            return result.toString();
        }

        return NOTHING.message;
    }

    public int calculateTotalBenefitPrice() {
        return discountPriceList
                .stream()
                .mapToInt(price -> price)
                .sum();
    }

    public int calculateTotalDiscountPrice() {
        int totalBenefitPrice = discountPriceList
                .stream()
                .mapToInt(price -> price)
                .sum();

        int giveawayPrice = discountPriceList.get(GIVEAWAY_INDEX.value);

        return totalBenefitPrice - giveawayPrice;
    }

    private boolean isAddChristmasResult(final DecimalFormatter decimalFormatter) {
        int christmasDiscount = discountPriceList.get(CHRISTMAS_DISCOUNT_INDEX.value);
        if (christmasDiscount < 0) {
            String formattedChristmasDiscount = decimalFormatter.createFormattedMessage(christmasDiscount);
            result.append(CHRISTMAS_DISCOUNT.message)
                    .append(formattedChristmasDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean isAddWeekdayResult(final DecimalFormatter decimalFormatter) {
        int weekdayDiscount = discountPriceList.get(WEEKDAY_DISCOUNT_INDEX.value);
        if (weekdayDiscount < 0) {
            String formattedWeekdayDiscount = decimalFormatter.createFormattedMessage(weekdayDiscount);
            result.append(WEEKDAY_DISCOUNT.message)
                    .append(formattedWeekdayDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }


    private boolean isAddWeekendResult(final DecimalFormatter decimalFormatter) {
        int weekendDiscount = discountPriceList.get(WEEKEND_DISCOUNT_INDEX.value);
        if (weekendDiscount < 0) {
            String formattedWeekendDiscount = decimalFormatter.createFormattedMessage(weekendDiscount);
            result.append(WEEKEND_DISCOUNT.message)
                    .append(formattedWeekendDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean isAddSpecialResult(final DecimalFormatter decimalFormatter) {
        int specialDiscount = discountPriceList.get(SPECIAL_DISCOUNT_INDEX.value);
        if (specialDiscount < 0) {
            String formattedSpecialDiscount = decimalFormatter.createFormattedMessage(specialDiscount);
            result.append(SPECIAL_DISCOUNT.message)
                    .append(formattedSpecialDiscount)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }

    private boolean isAddGiveawayResult(final DecimalFormatter decimalFormatter) {
        int giveawayPrice = discountPriceList.get(GIVEAWAY_INDEX.value);
        if (giveawayPrice != 0) {
            String formattedGiveawayPrice = decimalFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message)
                    .append(formattedGiveawayPrice)
                    .append(PRICE_UNIT_LINE_BREAK.value);
            return true;
        }
        return false;
    }
}
