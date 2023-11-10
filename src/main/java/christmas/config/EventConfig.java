package christmas.config;

import christmas.EventManager;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;
import christmas.view.valid.ViewValidator;

public class EventConfig {

    private EventConfig() {
    }

    public static EventManager buildEventManager() {
        return new EventManager(messagePrinter(), messageReceiver());
    }

    public static MessagePrinter messagePrinter() {
        return new MessagePrinter();
    }

    public static MessageReceiver messageReceiver() {
        return new MessageReceiver(viewValidator());
    }

    public static ViewValidator viewValidator() {
        return new ViewValidator();
    }
}
