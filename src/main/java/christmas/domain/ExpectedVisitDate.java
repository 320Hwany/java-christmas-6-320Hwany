package christmas.domain;

import static christmas.constant.DaysConstant.DECEMBER_END_DAY;
import static christmas.constant.DaysConstant.DECEMBER_START_DAY;
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
}
