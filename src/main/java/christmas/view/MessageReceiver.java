package christmas.view;

import camp.nextstep.edu.missionutils.Console;
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

    public List<String> receiveMenu() {
        System.out.println(ORDERING_INFO.message);
        String inputText = Console.readLine();
        return Arrays.asList(inputText.split(","));
    }
}
