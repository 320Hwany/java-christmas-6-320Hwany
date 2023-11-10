package christmas;

import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;
import christmas.view.valid.ViewValidator;

public class Application {

    public static void main(String[] args) {
        ViewValidator viewValidator = new ViewValidator();
        MessagePrinter messagePrinter = new MessagePrinter();
        MessageReceiver messageReceiver = new MessageReceiver(viewValidator);
        EventManager eventManager = new EventManager(messagePrinter, messageReceiver);
        eventManager.manageEvent();
    }
}
