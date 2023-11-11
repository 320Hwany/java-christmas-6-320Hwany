package christmas.config;

import christmas.EventManager;
import christmas.view.DecimalFormatter;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;
import christmas.view.valid.ViewValidator;

public class EventConfig {

    private EventConfig() {
    }

    public static EventManager buildEventManager() {
        return new EventManager(messagePrinter(), messageReceiver());
    }

    private static MessagePrinter messagePrinter() {
        return new MessagePrinter(decimalFormatter());
    }

    private static MessageReceiver messageReceiver() {
        return new MessageReceiver(viewValidator());
    }

    private static ViewValidator viewValidator() {
        return new ViewValidator();
    }

    private static DecimalFormatter decimalFormatter() {
        return new DecimalFormatter();
    }
}
