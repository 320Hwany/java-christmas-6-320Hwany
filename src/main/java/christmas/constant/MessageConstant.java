package christmas.constant;

public enum MessageConstant {

    EVENT_PLANNER_GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_INFO("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_EVENT_PREVIEW("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),

    ORDER_MENU("\n<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    GIVE_AWAY_MENU("\n<증정 메뉴>"),
    BENEFIT_RESULT("\n<혜택 내역>"),
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인: "),
    WEEKDAY_DISCOUNT("평일 할인: "),
    WEEKEND_DISCOUNT("주말 할인: "),
    SPECIAL_DISCOUNT("특별 할인: "),
    GIVEAWAY_EVENT("증정 이벤트: "),
    TOTAL_DISCOUNT_PRICE("<총혜택 금액>"),
    TOTAL_PRICE_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
    EVENT_BADGE("\n<12월 이벤트 배지>"),

    GIVE_AWAY_EVENT("샴페인 1개"),
    NOTHING("없음"),
    NOTHING_LINE_BREAK("없음\n");

    public final String message;

    MessageConstant(final String message) {
        this.message = message;
    }
}
