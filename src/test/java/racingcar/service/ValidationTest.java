package racingcar.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.RacingCar;

class ValidationTest {

    private Validation validation;

    @BeforeEach
    void setUp() {
        validation = new Validation();
    }

    @DisplayName("사용자가 5글자를 초과하는 이름을 입력했을때 예외를 발생하는지")
    @ParameterizedTest
    @ValueSource(strings = {"pobidd", "pobibb", "pobiee", "pobirr,bbbbooo"})
    void validateUserInput5CharacterTest(String inputCarName) {
        // given & when & then
        assertThatThrownBy(
                () -> validation.validateUserInput(inputCarName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5글자를 초과하는 이름은 입력할 수 없습니다.");
    }

    @DisplayName("사용자가 숫자를 입력했을 때, 예외를 발생하는지")
    @ParameterizedTest
    @ValueSource(strings = {"pob1", "12", "bbo32", "123,pob1"})
    void validateUserInputNumber(String inputCarName) {
        // given & when & then
        assertThatThrownBy(
                () -> validation.validateUserInput(inputCarName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름에 숫자가 들어갈 수 없습니다.");
    }
}