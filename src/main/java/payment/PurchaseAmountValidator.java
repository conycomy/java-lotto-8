package payment;

import static lotto.LottoNumberRange.PRICE_TICKET; // 1000원 상수를 위해 import 추가
import utils.Validator;

public class PurchaseAmountValidator implements Validator {

    private final String inputAmount;

    public PurchaseAmountValidator(String inputAmount) {
        this.inputAmount = inputAmount;
    }

    @Override
    public void validate() {
        validateAndParseAmount();
    }

    public int validateAndParseAmount() {
        validateInputExistence(this.inputAmount);
        int amount = parseAmount(this.inputAmount);
        validateUnit(amount);
        return amount;
    }

    private void validateInputExistence(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("구입 금액을 입력해 주세요.");
        }
    }

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % PRICE_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_TICKET + "원 단위여야 합니다.");
        }

        if (amount < PRICE_TICKET) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다.");
        }
    }
}