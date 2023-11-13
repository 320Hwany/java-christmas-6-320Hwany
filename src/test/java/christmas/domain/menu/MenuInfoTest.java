package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MenuInfoTest {

    @DisplayName("메뉴판에 없는 메뉴 이름을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"주문에 없는 메뉴"})
    void createMenuInfoFailByMenuName(final String notExistMenuName) {
        // expected
        assertThatThrownBy(() -> MenuInfo.from(notExistMenuName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 있는 메뉴 이름을 입력하면 메뉴 정보가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"})
    void validateExpectedDateSuccess(final String menuName) {
        // when
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // then
        assertThat(menuInfo).isNotNull();
    }

    @DisplayName("디저트 메뉴이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    void isDessertMenuTypeTrue(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isDessertMenuType = menuInfo.isDessertMenuType();

        // then
        assertThat(isDessertMenuType).isTrue();
    }

    @DisplayName("디저트 메뉴가 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"크리스마스파스타", "제로콜라"})
    void isDessertMenuTypeFalse(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isDessertMenuType = menuInfo.isDessertMenuType();

        // then
        assertThat(isDessertMenuType).isFalse();
    }

    @DisplayName("메인 메뉴이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    void isMainMenuTypeTrue(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isMainMenuType = menuInfo.isMainMenuType();

        // then
        assertThat(isMainMenuType).isTrue();
    }

    @DisplayName("메인 메뉴가 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드", "초코케이크"})
    void isMainMenuTypeFalse(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isMainMenuType = menuInfo.isMainMenuType();

        // then
        assertThat(isMainMenuType).isFalse();
    }

    @DisplayName("음료 메뉴가 아니면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"아이스크림", "양송이수프"})
    void isNotBeverageMenuTrue(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isNotBeverageMenu = menuInfo.isNotBeverageMenu();

        // then
        assertThat(isNotBeverageMenu).isTrue();
    }

    @DisplayName("음료 메뉴이면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void isNotBeverageMenuFalse(final String menuName) {
        // given
        MenuInfo menuInfo = MenuInfo.from(menuName);

        // when
        boolean isNotBeverageMenu = menuInfo.isNotBeverageMenu();

        // then
        assertThat(isNotBeverageMenu).isFalse();
    }
}