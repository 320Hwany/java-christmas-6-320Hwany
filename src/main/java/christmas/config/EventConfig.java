package christmas.config;

import christmas.EventManager;
import christmas.domain.discount.DiscountManager;
import christmas.domain.discount.policy.ChristmasDiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekdayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import christmas.view.DecimalFormatter;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;
import christmas.view.valid.ViewValidator;

public class EventConfig {

    private EventConfig() {
    }

    public static EventManager buildEventManager() {
        return new EventManager(messagePrinter(), messageReceiver(), discountManager());
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

    private static DiscountManager discountManager() {
        ChristmasDiscountPolicy christmasDiscount = new ChristmasDiscountPolicy();
        WeekdayDiscountPolicy weekdayDiscount = new WeekdayDiscountPolicy();
        WeekendDiscountPolicy weekendDiscount = new WeekendDiscountPolicy();
        SpecialDiscountPolicy specialDiscount = new SpecialDiscountPolicy();

        return new DiscountManager(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);
    }
}
