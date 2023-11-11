package christmas.view;

import java.text.DecimalFormat;

public final class DecimalFormatter {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public String createFormattedMessage(final int price) {
        return decimalFormat.format(price);
    }
}
