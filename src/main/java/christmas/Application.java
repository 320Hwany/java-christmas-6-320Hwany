package christmas;

import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;

public class Application {

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        MessageReceiver messageReceiver = new MessageReceiver();
        EventManager eventManager = new EventManager(messagePrinter, messageReceiver);
        eventManager.manageEvent();
    }
}
