package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("메뉴 개수는 1이상의 숫자가 아니면 예외가 발생한다.")
    @Test
    void validateQuantityFail() {
        // given
        String menuName = "양송이수프";
        int quantity = 0;

        // expected
        assertThatThrownBy(() -> Menu.createMenu(menuName, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }
}