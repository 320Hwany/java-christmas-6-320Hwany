package christmas.config;

import christmas.EventManager;
import christmas.service.policy.DiscountPolicyManager;
import christmas.service.policy.*;
import christmas.view.DecimalFormatter;
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
