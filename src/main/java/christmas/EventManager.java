package christmas;

import christmas.domain.Menu;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;

import java.util.List;

public class EventManager {

    private final MessagePrinter messagePrinter;
    private final MessageReceiver messageReceiver;

    public EventManager(final MessagePrinter messagePrinter, final MessageReceiver messageReceiver) {
        this.messagePrinter = messagePrinter;
        this.messageReceiver = messageReceiver;
    }

    public void manageEvent() {
        receiveVisitInfo();
    }

    private void receiveVisitInfo() {
        messagePrinter.printGreetingMessage();
        int expectedVisitDate = messageReceiver.receiveExpectedVisitDate();
        List<Menu> menus = messageReceiver.receiveOrder();
    }
}
