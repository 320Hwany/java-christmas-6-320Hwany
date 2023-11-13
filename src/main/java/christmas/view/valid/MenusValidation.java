package christmas.view.valid;

import christmas.domain.menu.Menus;

import java.util.Arrays;
import java.util.List;

import static christmas.constant.SymbolConstant.COMMA;

public class MenusValidation implements InputValidation<Menus> {

    @Override
    public Menus validateInput(final String inputText, final ViewValidator viewValidator) {
        List<String> orderInfo = Arrays.asList(inputText.split(COMMA.value));
        return viewValidator.validateOrderInfo(orderInfo);
    }
}
