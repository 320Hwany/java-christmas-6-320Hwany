package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MenuInfoTest {

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void createMenuInfoFailByMenuName() {
        // given
        String menuName = "주문에 없는 메뉴";

        // expected
        assertThatThrownBy(() -> MenuInfo.createMenuInfo(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}