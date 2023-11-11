package christmas.domain;

public final class ExpectedVisitDate {

    private final int expectedVisitDate;

    public ExpectedVisitDate(final int expectedVisitDate) {
        validateExpectedDate(expectedVisitDate);
        this.expectedVisitDate = expectedVisitDate;
    }

    private void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < 1 || expectedVisitDate > 31) {
            throw new IllegalArgumentException();
        }
    }

    // getter
    public int getExpectedVisitDate() {
        return expectedVisitDate;
    }
}
