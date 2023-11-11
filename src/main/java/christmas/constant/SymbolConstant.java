package christmas.constant;

public enum SymbolConstant {

    COMMA(","),
    BLANK(" "),
    QUANTITY_UNIT("개"),
    PRICE_UNIT("원");

    public final String value;

    SymbolConstant(final String value) {
        this.value = value;
    }
}
