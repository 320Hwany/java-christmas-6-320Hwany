package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.constant.MessageConstant.*;

public class MessageReceiver {

    public int receiveExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE.message);
        String expectedVisitDate = Console.readLine();
        return Integer.parseInt(expectedVisitDate);
    }
}
