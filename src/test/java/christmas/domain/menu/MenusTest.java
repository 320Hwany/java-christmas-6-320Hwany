package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenusTest {

    @DisplayName("주문한 메뉴 개수의 총합이 20개를 초과하면 예외가 발생한다.")
    @Test
    void validateMenusQuantity() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 11);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 중에서 중복된 메뉴가 있으면 예외가 발생한다.")
    @Test
    void validateDuplication() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "양송이수프";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 중에서 음료만 있으면 예외가 발생한다.")
    @Test
    void validateOnlyBeverage() {
        // given
        String menuName1 = "레드와인";
        String menuName2 = "제로콜라";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // expected
        assertThatThrownBy(() -> new Menus(List.of(menu1, menu2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최대 개수/중복/음료만 주문과 같은 조건에 제한되지 않으면 메뉴 목록이 생성된다.")
    @Test
    void createMenus() {
        // given
        String menuName1 = "양송이수프";
        String menuName2 = "초코케이크";
        Menu menu1 = Menu.createMenu(menuName1, 10);
        Menu menu2 = Menu.createMenu(menuName2, 10);

        // when
        Menus menus = new Menus(List.of(menu1, menu2));

        // then
        assertThat(menus).isNotNull();
    }
}