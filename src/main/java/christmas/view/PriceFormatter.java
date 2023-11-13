package christmas.view;

import java.text.DecimalFormat;

import static christmas.constant.SymbolConstant.*;

public final class PriceFormatter {

    private static final DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT.value);

    public static String createFormattedMessage(final int price) {
        return decimalFormat.format(price);
    }
}
