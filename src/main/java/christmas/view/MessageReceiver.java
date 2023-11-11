package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;
import christmas.view.valid.ViewValidator;

import java.util.Arrays;
import java.util.List;

import static christmas.constant.MessageConstant.*;

public class MessageReceiver {

    private final ViewValidator viewValidator;

    public MessageReceiver(final ViewValidator viewValidator) {
        this.viewValidator = viewValidator;
    }

    public ExpectedVisitDate receiveExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE.message);

        do {
            String inputText = Console.readLine();
            try {
                int inputNumber = viewValidator.parseIntExpectedDate(inputText);
                return new ExpectedVisitDate(inputNumber);
            } catch (IllegalArgumentException e) {
                viewValidator.printExceptionMessage(e);
            }
        } while (true);
    }

    public Order receiveOrder(final ExpectedVisitDate expectedVisitDate) {
        System.out.println(ORDER_INFO.message);

        String inputText;
        do {
            inputText = Console.readLine();
            try {
                List<String> orderInfo = Arrays.asList(inputText.split(","));
                return viewValidator.validateOrderInfo(orderInfo, expectedVisitDate);
             } catch (IllegalArgumentException e) {
                viewValidator.printExceptionMessage(e);
            }
        } while (true);
    }
}
