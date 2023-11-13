package christmas.constant;

public enum SymbolConstant {

    COMMA(","),
    BLANK(" "),
    QUANTITY_UNIT("개"),
    PRICE_UNIT("원"),
    PRICE_UNIT_LINE_BREAK("원\n"),
    HYPHEN("-"),
    PRICE_FORMAT("#,###");

    public final String value;

    SymbolConstant(final String value) {
        this.value = value;
    }
}
