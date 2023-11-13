package christmas.view.valid;

import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static christmas.constant.ExceptionConstant.*;
import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;
import static christmas.constant.MenuInfoConstant.*;
import static christmas.constant.SymbolConstant.*;


public class ViewValidator {

    public int parseInt(final String inputText, final String exceptionMessage) {
        try {
            return Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public void printExceptionMessage(final IllegalArgumentException e) {
        String exceptionMessage = e.getMessage();
        System.out.println(exceptionMessage);
    }

    public Menus validateOrderInfo(final List<String> orderInfo) {
        List<Menu> menus = new ArrayList<>();

        for (String menuText : orderInfo) {
            List<String> menuInfo = Arrays.asList(menuText.split(HYPHEN.value));
            if (isIncorrectForm(menuInfo)) {
                throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION.message);
            }
            Menu menu = createMenu(menuInfo);
            menus.add(menu);
        }

        return Menus.from(menus);
    }

    private boolean isIncorrectForm(final List<String> menuInfo) {
        return menuInfo.size() != MENU_INFO_SIZE.value;
    }

    private Menu createMenu(final List<String> menuInfo) {
        String menuName = menuInfo.get(MENU_NAME_INDEX.value);
        String quantityText = menuInfo.get(QUANTITY_INDEX.value);
        int quantity = parseInt(quantityText, INVALID_ORDER_EXCEPTION.message);
        return Menu.createMenu(menuName, quantity);
    }
}
