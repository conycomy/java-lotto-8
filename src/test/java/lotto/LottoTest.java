package lotto;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개가 넘거나 부족하면 예외가 발생한다")
    @Test
    void createLottoByInvalidSize() {
        // 7개 입력 (초과)
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        // 5개 입력 (부족)
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호가 1에서 45 사이의 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidRangeNumbers")
    void createLottoByInvalidRange(List<Integer> invalidNumbers) {
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    static Stream<Arguments> invalidRangeNumbers() {
        return Stream.of(
                // 45 초과
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                // 1 미만 (0)
                Arguments.of(List.of(0, 2, 3, 4, 5, 6))
        );
    }

    // --- 2. 기능 검증 테스트 ---

    @DisplayName("다른 로또와 일치하는 번호 개수(matchCount)를 정확히 반환해야 한다.")
    @Test
    void matchCount_shouldReturnCorrectCount() {
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        // 당첨 번호: 1, 2, 3, 10 일치 -> 총 4개 일치
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 9, 10, 45));

        int matchedCount = myLotto.matchCount(winningLotto);

        assertThat(matchedCount).isEqualTo(4);
    }

    @DisplayName("특정 번호 포함 여부(contains)를 정확히 확인해야 한다.")
    @Test
    void contains_shouldCheckNumberPresence() {
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // 포함되는 경우
        assertThat(myLotto.contains(5)).isTrue();
        // 포함되지 않는 경우
        assertThat(myLotto.contains(45)).isFalse();
    }}
