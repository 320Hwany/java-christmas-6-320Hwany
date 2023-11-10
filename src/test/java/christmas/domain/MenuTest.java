package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("메뉴 수량과 메뉴 가격으로부터 한 가지 메뉴의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        Menu menu = Menu.createMenu("양송이수프", 5);

        // when
        int price = menu.calculatePrice();

        // then
        assertThat(price).isEqualTo(6000 * 5);
    }
}