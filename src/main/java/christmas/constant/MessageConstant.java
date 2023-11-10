package christmas.constant;

public enum MessageConstant {

    EVENT_PLANNER_GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_INFO("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_EVENT_PREVIEW("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("\n<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    GIVE_AWAY_MENU("\n<증정 메뉴>");

    public final String message;

    MessageConstant(final String message) {
        this.message = message;
    }
}
