package result;

import lotto.Lotto;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(Lotto customerLotto) {
        int matchCount = customerLotto.matchCount(this.winningNumbers);
        boolean matchBonus = customerLotto.contains(this.bonusNumber);
        return Rank.valueOf(matchCount, matchBonus);
    }

    private void validateDuplication(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }


}