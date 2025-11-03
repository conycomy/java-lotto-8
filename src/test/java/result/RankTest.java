package result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {


    @DisplayName("일치 개수와 보너스 여부에 따라 정확한 Rank 객체가 반환되어야 한다.")
    @ParameterizedTest(name = "일치:{0}, 보너스:{1} => 등수:{2}")
    @CsvSource(value = {
            "6, false, FIRST",   // 1등: 6개 일치
            "5, true, SECOND",   // 2등: 5개 일치 + 보너스 O
            "5, false, THIRD",   // 3등: 5개 일치 + 보너스 X
            "4, false, FOURTH",  // 4등: 4개 일치
            "3, false, FIFTH",   // 5등: 3개 일치
            "2, false, MISS",    // 꽝: 2개 일치 (3개 미만)
            "0, true, MISS"      // 꽝: 0개 일치
    })
    void valueOf_shouldReturnCorrectRank(int matchCount, boolean matchBonus, Rank expectedRank) {
        Rank actualRank = Rank.valueOf(matchCount, matchBonus);

        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @DisplayName("5등 미만(3개 미만 일치)은 무조건 낙첨(MISS) 처리되어야 한다.")
    @Test
    void valueOf_shouldReturnMissForLowCount() {
        Rank rankOne = Rank.valueOf(2, true);
        assertThat(rankOne).isEqualTo(Rank.MISS);
    }


    @DisplayName("각 등수의 상금 금액(winningPrize)이 정확해야 한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "FIRST, 2000000000",
            "SECOND, 30000000",
            "THIRD, 1500000",
            "FOURTH, 50000",
            "FIFTH, 5000"
    })
    void getWinningPrize_shouldReturnCorrectAmount(Rank rank, long expectedPrize) {
        // 상금은 20억까지 있으므로 long 타입으로 비교해야 오버플로우를 피할 수 있습니다.
        assertThat((long)rank.getWinningPrize()).isEqualTo(expectedPrize);
    }
}