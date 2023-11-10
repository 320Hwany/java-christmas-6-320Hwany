package christmas;

import christmas.config.EventConfig;

public class Application {

    public static void main(String[] args) {
        EventManager eventManager = EventConfig.buildEventManager();
        eventManager.manageEvent();
    }
}
