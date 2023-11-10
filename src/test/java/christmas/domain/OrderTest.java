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

    @DisplayName("주문한 메뉴에 중복된 메뉴가 있으면 예외가 발생한다.")
    @Test
    void validateDuplication() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "양송이수프";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);

        // expected
        assertThatThrownBy(() -> new Order(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문시 음료만 주문하면 예외가 발생한다.")
    @Test
    void validateOnlyBeverage() {
        // given
        String menuName1 = "제로콜라";
        String menuName2 = "레드와인";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 5);
        List<Menu> menus = List.of(menu1, menu2);

        // expected
        assertThatThrownBy(() -> new Order(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}