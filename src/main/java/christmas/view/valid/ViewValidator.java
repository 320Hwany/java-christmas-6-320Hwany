package christmas.view.valid;

import java.util.Arrays;
import java.util.List;

import static christmas.constant.MenuConstant.*;

public class ViewValidator {

    public int parseInt(final String inputText) {
        try {
            return Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public void validateExpectedDate(final int expectedVisitDate) {
        if (expectedVisitDate < 1 || expectedVisitDate > 31) {
            throw new IllegalArgumentException();
        }
    }

    public void validateOrderInfo(final List<String> orderInfo) {
        for (String menuInfo : orderInfo) {
            List<String> menuAndQuantity = Arrays.asList(menuInfo.split("-"));
            String menu = menuAndQuantity.get(0);
            String quantity = menuAndQuantity.get(1);
            validateMenuInfo(menu);
        }
    }

    private void validateMenuInfo(final String menu) {
        boolean isAppetizer = appetizer.contains(menu);
        boolean isMain = main.contains(menu);
        boolean isDessert = dessert.contains(menu);
        boolean isBeverage = beverage.contains(menu);

        if (!(isAppetizer || isMain || isDessert || isBeverage)) {
            throw new IllegalArgumentException();
        }
    }
}
