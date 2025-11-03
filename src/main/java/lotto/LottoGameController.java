package lotto;

import config.LottoConfig;
import result.LottoResultChecker;
import payment.PurchaseAmountValidator;
import result.WinningLotto;
import result.WinningNumbersInputValidator;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoGameController {

    private final LottoConfig config = new LottoConfig();
    private final InputView inputView = config.inputView();
    private final OutputView outputView = config.outputView();

    private int purchaseAmount;
    private LottoStore lottoStore;
    private WinningLotto winningLotto;

    public void run() {
        processPurchaseAndIssueLottos();

        processWinningLottoInput();

        processResultCalculationAndOutput();
    }


    private void processPurchaseAndIssueLottos() {
        while (true) {
            try {
                String amountInput = inputView.inputPurchaseAmount();

                PurchaseAmountValidator validator = new PurchaseAmountValidator(amountInput);

                int amount = validator.validateAndParseAmount();

                LottoNumberGenerator generator = config.lottoNumberGenerator();
                lottoStore = new LottoStore(amount, generator);
                purchaseAmount = amount;

                outputView.printPurchasedLottos(lottoStore.getPurchasedLottos());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void processWinningLottoInput() {
        while (true) {
            try {
                String winningInput = inputView.inputWinningNumbers();
                String bonusInput = inputView.inputBonusNumber();

                WinningNumbersInputValidator validator = new WinningNumbersInputValidator(
                        winningInput, bonusInput
                );
                validator.validate();

                List<Integer> winningNumbers = validator.getWinningNumbers();
                int bonusNumber = validator.getBonusNumber();

                Lotto winningLottoModel = new Lotto(winningNumbers);
                winningLotto = config.winningLotto(winningLottoModel, bonusNumber); // Config 사용

                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void processResultCalculationAndOutput() {
        LottoResultChecker checker = config.lottoResultChecker(
                lottoStore.getPurchasedLottos(),
                winningLotto,
                purchaseAmount
        );

        outputView.printWinningResult(
                checker.getResultCounts(),
                checker.calculateReturnRate()
        );
    }
}