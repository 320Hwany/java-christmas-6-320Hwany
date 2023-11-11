package christmas.constant;

public enum ExceptionConstant {

    EXPECTED_DATE_EXCEPTION("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_EXCEPTION("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public final String message;

    ExceptionConstant(final String message) {
        this.message = message;
    }
}
