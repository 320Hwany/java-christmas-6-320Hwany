package christmas.domain.date;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;
import static christmas.constant.MessageConstant.ORDER_EVENT_PREVIEW;
import static christmas.constant.PriceConstant.*;

public final class ExpectedVisitDate {

    private final int expectedVisitDate;

    private ExpectedVisitDate(final int expectedVisitDate) {
        validateExpectedDate(expectedVisitDate);
        this.expectedVisitDate = expectedVisitDate;
    }

    public static ExpectedVisitDate from(final int expectedVisitDate) {
        return new ExpectedVisitDate(expectedVisitDate);
    }

    // validation
    private void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < DECEMBER_START_DAY.value || expectedVisitDate > DECEMBER_END_DAY.value) {
            throw new IllegalArgumentException(EXPECTED_DATE_EXCEPTION.message);
        }
    }

    // business
    public String createPreviewFormattedMessage() {
        return String.format(ORDER_EVENT_PREVIEW.message, expectedVisitDate);
    }

    public int calculateTotalChristmasDiscount() {
        int basicChristmasDiscount = CHRISTMAS_BASIC_DISCOUNT.price;
        if (isNotChristmasDDay()) {
            return ZERO_DISCOUNT.price;
        }

        int applyDays = calculateApplyDays();
        int christmasDayDiscount = CHRISTMAS_DISCOUNT_UNIT.price * applyDays;

        return basicChristmasDiscount - christmasDayDiscount;
    }

    public int calculateSpecialDayDiscount() {
        if (isSpecialDay()) {
            return SPECIAL_DISCOUNT.price;
        }

        return ZERO_DISCOUNT.price;
    }

    public boolean isWeekday() {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return !(day == FRIDAY.value || day == SATURDAY.value);
    }

    public boolean isWeekend() {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return day == FRIDAY.value || day == SATURDAY.value;
    }

    private int calculateApplyDays() {
        return expectedVisitDate - ONE_DAY.value;
    }

    private boolean isNotChristmasDDay() {
        return expectedVisitDate > CHRISTMAS.value;
    }

    private boolean isSpecialDay() {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return day == SPECIAL_DAY.value || expectedVisitDate == CHRISTMAS.value;
    }
}
