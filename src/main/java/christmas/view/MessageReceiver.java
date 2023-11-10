package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
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

    public int receiveExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE.message);
        String inputText = Console.readLine();
        int expectedVisitDate = viewValidator.parseInt(inputText);
        viewValidator.validateExpectedDate(expectedVisitDate);

        return expectedVisitDate;
    }

    public Order receiveOrder() {
        System.out.println(ORDER_INFO.message);
        String inputText = Console.readLine();
        List<String> orderInfo = Arrays.asList(inputText.split(","));
        return viewValidator.validateOrderInfo(orderInfo);
    }
}
