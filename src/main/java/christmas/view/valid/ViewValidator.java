package christmas.view.valid;

public class ViewValidator {

    public int parseInt(final String inputText) {
        try {
            return Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < 1 || expectedVisitDate > 31) {
            throw new IllegalArgumentException();
        }
    }
}
