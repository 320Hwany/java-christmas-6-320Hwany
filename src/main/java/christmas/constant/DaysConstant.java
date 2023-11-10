package christmas.constant;

public enum DaysConstant {

    SEVEN_DAYS(7),
    FRIDAY(1),
    SATURDAY(2),
    CHRISTMAS(25);

    public final int value;

    DaysConstant(final int value) {
        this.value = value;
    }
}
