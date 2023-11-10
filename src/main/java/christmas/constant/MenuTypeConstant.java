package christmas.constant;

public enum MenuTypeConstant {

    APPETIZER("appetizer"),
    MAIN_MENU("main"),
    DESSERT("dessert"),
    BEVERAGE("beverage");

    public final String type;

    MenuTypeConstant(final String type) {
        this.type = type;
    }
}
