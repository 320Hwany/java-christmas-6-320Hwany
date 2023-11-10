package christmas.domain;

public final class Menu {

    private final MenuInfo menuInfo;
    private final int quantity;

    private Menu(final MenuInfo menuInfo, final int quantity) {
        validateQuantity(quantity);
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static Menu createMenu(final String menuName, final int quantity) {
        MenuInfo menuInfo = MenuInfo.createMenuInfo(menuName);
        return new Menu(menuInfo, quantity);
    }

    private void validateQuantity(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }
    }

    public int calculatePrice() {
        return menuInfo.price * quantity;
    }

    // getter
    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    public String getMenuName() {
        return menuInfo.menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
