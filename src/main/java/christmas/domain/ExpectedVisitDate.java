package christmas.domain;

import static christmas.constant.DaysConstant.*;
import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;
import static christmas.constant.MessageConstant.ORDER_EVENT_PREVIEW;

public record ExpectedVisitDate(
        int expectedVisitDate
) {

    public ExpectedVisitDate {
        validateExpectedDate(expectedVisitDate);
    }

    private void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < DECEMBER_START_DAY.value || expectedVisitDate > DECEMBER_END_DAY.value) {
            throw new IllegalArgumentException(EXPECTED_DATE_EXCEPTION.message);
        }
    }

    public String createPreviewFormattedMessage() {
        return String.format(ORDER_EVENT_PREVIEW.message, expectedVisitDate);
    }

    public int calculateApplyDays() {
        return expectedVisitDate - 1;
    }

    public boolean isNotChristmasDDay() {
        return expectedVisitDate > CHRISTMAS.value;
    }

    public boolean isWeekday() {
        int day = expectedVisitDate % SEVEN_DAYS.value;
        return !(day == FRIDAY.value || day == SATURDAY.value);
    }
}
