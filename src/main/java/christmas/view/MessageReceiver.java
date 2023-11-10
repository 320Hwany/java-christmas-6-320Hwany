package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.valid.ViewValidator;

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
}
