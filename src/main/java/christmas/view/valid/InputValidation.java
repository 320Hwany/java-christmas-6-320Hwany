package christmas.view.valid;

public interface InputValidation<T> {

    T validateInput(final String inputText, final ViewValidator viewValidator);
}
