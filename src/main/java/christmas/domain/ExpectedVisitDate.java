package christmas.domain;


import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;

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

    // getter
    public int getExpectedVisitDate() {
        return expectedVisitDate;
    }
}
