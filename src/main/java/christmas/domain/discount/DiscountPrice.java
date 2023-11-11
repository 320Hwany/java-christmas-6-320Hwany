package christmas.domain.discount;

import java.text.DecimalFormat;
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

    public String createBenefitResultText() {
        addChristmasResult();
        addWeekdayResult();
        addWeekendResult();
        addSpecialResult();
        addGiveawayResult();

        return result.toString();
    }

    private void addChristmasResult() {
        int christmasDiscount = discountPrice.get("christmasDiscount");
        if (christmasDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedChristmasDiscount = decimalFormat.format(christmasDiscount);
            result.append(CHRISTMAS_DISCOUNT.message).append(formattedChristmasDiscount).append("원\n");
        }
    }

    private void addWeekdayResult() {
        int weekdayDiscount = discountPrice.get("weekdayDiscount");
        if (weekdayDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedWeekdayDiscount = decimalFormat.format(weekdayDiscount);
            result.append(WEEKDAY_DISCOUNT.message).append(formattedWeekdayDiscount).append("원\n");
        }
    }


    private void addWeekendResult() {
        int weekendDiscount = discountPrice.get("weekendDiscount");
        if (weekendDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedWeekendDiscount = decimalFormat.format(weekendDiscount);
            result.append(WEEKEND_DISCOUNT.message).append(formattedWeekendDiscount).append("원\n");
        }
    }

    private void addSpecialResult() {
        int specialDiscount = discountPrice.get("specialDiscount");
        if (specialDiscount < 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedSpecialDiscount = decimalFormat.format(specialDiscount);
            result.append(SPECIAL_DISCOUNT.message).append(formattedSpecialDiscount).append("원\n");
        }
    }

    private void addGiveawayResult() {
        int giveawayPrice = discountPrice.get("giveawayPrice");
        if (giveawayPrice != 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedGiveawayPrice = decimalFormat.format(giveawayPrice);
            result.append(GIVEAWAY_EVENT.message).append(formattedGiveawayPrice).append("원\n");
        }
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
}
