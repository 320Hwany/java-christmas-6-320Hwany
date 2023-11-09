package christmas;

import christmas.view.MessagePrinter;

public class Application {

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        EventManager eventManager = new EventManager(messagePrinter);
        eventManager.manageEvent();
    }
}
