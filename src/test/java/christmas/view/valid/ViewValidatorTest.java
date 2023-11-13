package christmas.view.valid;

import org.assertj.core.api.FileAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.constant.ExceptionConstant.EXPECTED_DATE_EXCEPTION;
import static org.assertj.core.api.Assertions.*;

class ViewValidatorTest {

    @DisplayName("문자열로 작성된 숫자를 정수 타입으로 변환한다.")
    @ParameterizedTest
    @CsvSource({"10, 예외 메세지"})
    void parseIntSuccess(final String inputText, final String exceptionMessage) {
        // given
        ViewValidator viewValidator = new ViewValidator();

        // when
        int expectedVisitDate = viewValidator.parseInt(inputText, exceptionMessage);

        // then
        assertThat(expectedVisitDate).isEqualTo(10);
    }

    @DisplayName("문자열로 작성된 숫자를 정수 타입으로 변환할 수 없으며 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"숫자가 아님, 예외 메세지"})
    void parseIntFail(final String inputText, final String exceptionMessage) {
        // given
        ViewValidator viewValidator = new ViewValidator();

        // expected
        assertThatThrownBy(() -> viewValidator.parseInt(inputText, exceptionMessage))
                .isInstanceOf(IllegalArgumentException.class);
    }
}