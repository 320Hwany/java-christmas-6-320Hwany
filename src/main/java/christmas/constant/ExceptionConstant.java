package christmas.constant;

public enum ExceptionConstant {

    EXPECTED_DATE_EXCEPTION("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_EXCEPTION("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE_ORDER_EXCEPTION("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    MAX_MENU_QUANTITY_EXCEPTION("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    public final String message;

    ExceptionConstant(final String message) {
        this.message = message;
    }
}
