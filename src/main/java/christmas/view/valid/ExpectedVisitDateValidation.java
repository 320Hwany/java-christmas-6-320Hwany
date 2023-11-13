package christmas.view.valid;

import christmas.domain.date.ExpectedVisitDate;

import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;

public class ExpectedVisitDateValidation implements InputValidation<ExpectedVisitDate> {

    @Override
    public ExpectedVisitDate validateInput(final String inputText, final ViewValidator viewValidator) {
        int inputNumber = viewValidator.parseInt(inputText, EXPECTED_DATE_EXCEPTION.message);
        return ExpectedVisitDate.from(inputNumber);
    }
}
