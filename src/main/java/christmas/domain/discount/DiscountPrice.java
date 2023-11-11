package christmas.domain.discount;

import christmas.view.DecimalFormatter;

import java.util.HashMap;
import java.util.Map;

import static christmas.constant.MessageConstant.*;

public final class DiscountPrice {

    private final Map<String, Integer> discountPrice = new HashMap<>();
    private final StringBuilder result = new StringBuilder();

    public DiscountPrice(final int christmasDiscount, final int weekdayDiscount, final int weekendDiscount,
                         final int specialDiscount, final int giveawayPrice) {
        discountPrice.put("christmasDiscount", christmasDiscount);
        discountPrice.put("weekdayDiscount", weekdayDiscount);
        discountPrice.put("weekendDiscount", weekendDiscount);
        discountPrice.put("specialDiscount", specialDiscount);
        discountPrice.put("giveawayPrice", giveawayPrice);
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

        return "없음";
    }

    public int calculateTotalBenefitPrice() {
        return discountPrice.values()
                .stream()
                .mapToInt(price -> price)
                .sum();
    }

    public int calculateTotalDiscountPrice() {
        int totalBenefitPrice = discountPrice.values()
                .stream()
                .mapToInt(price -> price)
                .sum();

        int giveawayPrice = discountPrice.get("giveawayPrice");

        return totalBenefitPrice - giveawayPrice;
    }

    private boolean isAddChristmasResult(final DecimalFormatter decimalFormatter) {
        int christmasDiscount = discountPrice.get("christmasDiscount");
        if (christmasDiscount < 0) {
            String formattedChristmasDiscount = decimalFormatter.createFormattedMessage(christmasDiscount);
            result.append(CHRISTMAS_DISCOUNT.message).append(formattedChristmasDiscount).append("원\n");
            return true;
        }
        return false;
    }

    private boolean isAddWeekdayResult(final DecimalFormatter decimalFormatter) {
        int weekdayDiscount = discountPrice.get("weekdayDiscount");
        if (weekdayDiscount < 0) {
            String formattedWeekdayDiscount = decimalFormatter.createFormattedMessage(weekdayDiscount);
            result.append(WEEKDAY_DISCOUNT.message).append(formattedWeekdayDiscount).append("원\n");
            return true;
        }
        return false;
    }


    private boolean isAddWeekendResult(final DecimalFormatter decimalFormatter) {
        int weekendDiscount = discountPrice.get("weekendDiscount");
        if (weekendDiscount < 0) {
            String formattedWeekendDiscount = decimalFormatter.createFormattedMessage(weekendDiscount);
            result.append(WEEKEND_DISCOUNT.message).append(formattedWeekendDiscount).append("원\n");
            return true;
        }
        return false;
    }

    private boolean isAddSpecialResult(final DecimalFormatter decimalFormatter) {
        int specialDiscount = discountPrice.get("specialDiscount");
        if (specialDiscount < 0) {
            String formattedSpecialDiscount = decimalFormatter.createFormattedMessage(specialDiscount);
            result.append(SPECIAL_DISCOUNT.message).append(formattedSpecialDiscount).append("원\n");
            return true;
        }
        return false;
    }

    private boolean isAddGiveawayResult(final DecimalFormatter decimalFormatter) {
        int giveawayPrice = discountPrice.get("giveawayPrice");
        if (giveawayPrice != 0) {
            String formattedGiveawayPrice = decimalFormatter.createFormattedMessage(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message).append(formattedGiveawayPrice).append("원\n");
            return true;
        }
        return false;
    }
}
