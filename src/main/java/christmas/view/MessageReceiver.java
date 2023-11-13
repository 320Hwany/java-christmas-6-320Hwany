package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.date.ExpectedVisitDate;
import christmas.domain.menu.Menus;
import christmas.domain.order.Order;
import christmas.view.valid.ExpectedVisitDateValidation;
import christmas.view.valid.InputValidation;
import christmas.view.valid.MenusValidation;
import christmas.view.valid.ViewValidator;

import static christmas.constant.MessageConstant.*;

public class MessageReceiver {

    private final ViewValidator viewValidator;
    private final ExpectedVisitDateValidation expectedVisitDateValidation;
    private final MenusValidation menusValidation;

    public MessageReceiver(final ViewValidator viewValidator,
                           final ExpectedVisitDateValidation expectedVisitDateValidation,
                           final MenusValidation menusValidation) {
        this.viewValidator = viewValidator;
        this.expectedVisitDateValidation = expectedVisitDateValidation;
        this.menusValidation = menusValidation;
    }

    public ExpectedVisitDate receiveExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE.message);
        return receiveInput(expectedVisitDateValidation);
    }

    public Order receiveOrder(final ExpectedVisitDate expectedVisitDate) {
        System.out.println(ORDER_INFO.message);
        Menus menus = receiveInput(menusValidation);

        return Order.of(menus, expectedVisitDate);
    }

    private <T> T receiveInput(final InputValidation<T> inputValidation) {
        do {
            String inputText = Console.readLine();
            try {
                return inputValidation.validateInput(inputText, viewValidator);

            } catch (IllegalArgumentException e) {
                String exceptionMessage = e.getMessage();
                System.out.println(exceptionMessage);
            }
        } while (true);
    }
}
