package christmas;

import christmas.view.MessagePrinter;

public class EventManager {

    private final MessagePrinter messagePrinter;

    public EventManager(final MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    public void manageEvent() {
        receiveVisitInfo();
    }

    private void receiveVisitInfo() {
        messagePrinter.printGreetingMessage();
    }
}
