package christmas.config;

import christmas.EventManager;
import christmas.service.*;
import christmas.service.implement.ChristmasDiscountPolicy;
import christmas.service.implement.SpecialDiscountPolicy;
import christmas.service.implement.WeekdayDiscountPolicy;
import christmas.service.implement.WeekendDiscountPolicy;
import christmas.view.MessagePrinter;
import christmas.view.MessageReceiver;
import christmas.view.valid.ViewValidator;

import java.util.ArrayList;
import java.util.List;

public class EventConfig {

    private EventConfig() {
    }

    public static EventManager buildEventManager() {
        return new EventManager(messagePrinter(), messageReceiver(), discountManager());
    }

    private static MessagePrinter messagePrinter() {
        return new MessagePrinter();
    }

    private static MessageReceiver messageReceiver() {
        return new MessageReceiver(viewValidator());
    }

    private static ViewValidator viewValidator() {
        return new ViewValidator();
    }

    private static DiscountPolicyManager discountManager() {
        ChristmasDiscountPolicy christmasDiscount = new ChristmasDiscountPolicy();
        WeekdayDiscountPolicy weekdayDiscount = new WeekdayDiscountPolicy();
        WeekendDiscountPolicy weekendDiscount = new WeekendDiscountPolicy();
        SpecialDiscountPolicy specialDiscount = new SpecialDiscountPolicy();

        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(christmasDiscount);
        discountPolicies.add(weekdayDiscount);
        discountPolicies.add(weekendDiscount);
        discountPolicies.add(specialDiscount);

        return new DiscountPolicyManager(discountPolicies);
    }
}
