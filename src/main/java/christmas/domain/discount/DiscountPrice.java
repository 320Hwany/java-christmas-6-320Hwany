package christmas.domain.discount;

import java.text.DecimalFormat;

import static christmas.constant.MessageConstant.*;

public record DiscountPrice(
        int christmasDiscount,
        int weekdayDiscount,
        int weekendDiscount,
        int specialDiscount,
        int giveawayPrice
) {

    public String createBenefitResultText() {
        StringBuilder stringBuilder = new StringBuilder();
        if (christmasDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedChristmasDiscount = decimalFormat.format(christmasDiscount);
            stringBuilder.append(CHRISTMAS_DISCOUNT.message).append(formattedChristmasDiscount).append("원\n");
        }

        if (weekdayDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedWeekdayDiscount = decimalFormat.format(weekdayDiscount);
            stringBuilder.append(WEEKDAY_DISCOUNT.message).append(formattedWeekdayDiscount).append("원\n");
        }

        if (weekendDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedWeekendDiscount = decimalFormat.format(weekendDiscount);
            stringBuilder.append(WEEKEND_DISCOUNT.message).append(formattedWeekendDiscount).append("원\n");
        }

        if (specialDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedSpecialDiscount = decimalFormat.format(specialDiscount);
            stringBuilder.append(SPECIAL_DISCOUNT.message).append(formattedSpecialDiscount).append("원\n");
        }

        if (giveawayPrice != 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedGiveawayPrice = decimalFormat.format(giveawayPrice);
            stringBuilder.append(GIVEAWAY_EVENT.message).append(formattedGiveawayPrice).append("원\n");
        }

        return stringBuilder.toString();
    }

    public int calculateTotalDiscountPrice() {
        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayPrice;
    }
}
