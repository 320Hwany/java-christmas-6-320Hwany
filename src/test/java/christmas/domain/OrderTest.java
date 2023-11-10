package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 메뉴 수가 20개를 초과하면 예외가 발생한다.")
    @Test
    void validateMenusQuantity() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 11);
        List<Menu> menus = List.of(menu1, menu2);

        // expected
        assertThatThrownBy(() -> new Order(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}