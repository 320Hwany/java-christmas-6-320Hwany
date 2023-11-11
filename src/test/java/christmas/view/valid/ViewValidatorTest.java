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
        int expectedVisitDate = viewValidator.parseIntExpectedDate(inputText);

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
        assertThatThrownBy(() -> viewValidator.parseIntExpectedDate(inputText))
                .isInstanceOf(IllegalArgumentException.class);
    }
}