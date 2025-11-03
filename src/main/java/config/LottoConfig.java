package config;

import lotto.LottoNumberGenerator;
import lotto.LottoStore;
import lotto.Lotto;
import result.WinningLotto;
import result.LottoResultChecker;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoConfig {


    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }


    public LottoNumberGenerator lottoNumberGenerator() {
        return new LottoNumberGenerator();
    }


    public LottoStore lottoStore(int purchaseAmount) {
        return new LottoStore(purchaseAmount, lottoNumberGenerator());
    }


    public WinningLotto winningLotto(Lotto winningLottoModel, int bonusNumber) {
        return new WinningLotto(winningLottoModel, bonusNumber);
    }


    public LottoResultChecker lottoResultChecker(
            List<Lotto> purchasedLottos,
            WinningLotto winningLotto,
            int purchaseAmount
    ) {
        return new LottoResultChecker(
                purchasedLottos,
                winningLotto,
                purchaseAmount
        );
    }
}