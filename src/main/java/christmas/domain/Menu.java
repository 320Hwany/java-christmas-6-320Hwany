package christmas.domain;

public final class Menu {

    private final MenuInfo menuInfo;
    private final int quantity;

    private Menu(final MenuInfo menuInfo, final int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static Menu createMenu(final String menuName, final int quantity) {
        MenuInfo menuInfo = MenuInfo.createMenuInfo(menuName);
        validateQuantity(quantity);
        return new Menu(menuInfo, quantity);
    }

    private static void validateQuantity(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }
    }
}
