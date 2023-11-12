package christmas.constant;

public enum MenuInfoConstant {

    MENU_INFO_SIZE(2),
    MENU_NAME_INDEX(0),
    QUANTITY_INDEX(1),
    MINIMUM_MENU_QUANTITY(1),
    ZERO_QUANTITY(0),
    MAXIMUM_MENU_QUANTITY(20);

    public final int value;

    MenuInfoConstant(final int value) {
        this.value = value;
    }
}
