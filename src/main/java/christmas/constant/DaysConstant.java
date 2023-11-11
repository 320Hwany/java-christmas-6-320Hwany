package christmas.constant;

public enum DaysConstant {

    DECEMBER_START_DAY(1),
    DECEMBER_END_DAY(31),
    SEVEN_DAYS(7),
    FRIDAY(1),
    SATURDAY(2),
    SPECIAL_DAY(3),
    CHRISTMAS(25);

    public final int value;

    DaysConstant(final int value) {
        this.value = value;
    }
}
