package christmas.domain;

import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;
import static christmas.constant.MessageConstant.ORDER_EVENT_PREVIEW;

public final class ExpectedVisitDate {

    private final int expectedVisitDate;

    public ExpectedVisitDate(final int expectedVisitDate) {
        validateExpectedDate(expectedVisitDate);
        this.expectedVisitDate = expectedVisitDate;
    }

    private void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < 1 || expectedVisitDate > 31) {
            throw new IllegalArgumentException(EXPECTED_DATE_EXCEPTION.message);
        }
    }

    public String createPreviewFormattedMessage() {
        return String.format(ORDER_EVENT_PREVIEW.message, expectedVisitDate);
    }

    // getter
    public int getExpectedVisitDate() {
        return expectedVisitDate;
    }
}
