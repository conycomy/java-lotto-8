package result;
import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),      // 1등: 6개 일치
    SECOND(5, true, 30_000_000),         // 2등: 5개 일치 + 보너스 O
    THIRD(5, false, 1_500_000),          // 3등: 5개 일치 + 보너스 X
    FOURTH(4, false, 50_000),           // 4등: 4개 일치
    FIFTH(3, false, 5_000),             // 5등: 3개 일치 (최소 당첨 기준)
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int winningPrize;

    Rank(int matchCount, boolean matchBonus, int winningPrize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.winningPrize = winningPrize;
    }

    public int getWinningPrize () {
        return winningPrize;
    }

    public int getMatchCount () {
        return matchCount;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount < FIFTH.matchCount) {
            return MISS;
        }

        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.matchBonus == matchBonus)
                .findFirst()
                .orElse(MISS);


    }
}