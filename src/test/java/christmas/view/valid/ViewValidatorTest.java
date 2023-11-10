package christmas.view.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ViewValidatorTest {

    @DisplayName("문자열로 작성된 숫자를 정수 타입으로 변환한다.")
    @Test
    void parseIntSuccess() {
        // given
        ViewValidator viewValidator = new ViewValidator();
        String inputText = "10";

        // when
        int expectedVisitDate = viewValidator.parseInt(inputText);

        // then
        assertThat(expectedVisitDate).isEqualTo(10);
    }

    @DisplayName("문자열로 작성된 숫자를 정수 타입으로 변환할 수 없으며 예외가 발생한다.")
    @Test
    void parseIntFail() {
        // given
        ViewValidator viewValidator = new ViewValidator();
        String inputText = "숫자가 아님";

        // expected
        assertThatThrownBy(() -> viewValidator.parseInt(inputText))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 숫자가 해당 범위의 날짜인지 확인한다.")
    @Test
    void validateExpectedDateSuccess() {
        // given
        ViewValidator viewValidator = new ViewValidator();
        int expectedVisitDate = 31;

        // expected
        viewValidator.validateExpectedDate(expectedVisitDate);
    }

    @DisplayName("입력받은 숫자가 해당 범위의 날짜가 아니면 예외가 발생한다.")
    @Test
    void validateExpectedDateFail() {
        // given
        ViewValidator viewValidator = new ViewValidator();
        int expectedVisitDate = 32;

        // expected
        assertThatThrownBy(() -> viewValidator.validateExpectedDate(expectedVisitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}