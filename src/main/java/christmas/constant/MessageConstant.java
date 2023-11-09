package christmas.constant;

public enum MessageConstant {

    EVENT_PLANNER_GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

    public final String message;

    MessageConstant(final String message) {
        this.message = message;
    }
}
