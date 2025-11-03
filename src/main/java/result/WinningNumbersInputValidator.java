package result;

import static lotto.LottoNumberRange.MAX_NUMBER;
import static lotto.LottoNumberRange.MIN_NUMBER;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Delimiter;
import utils.Validator;

public class WinningNumbersInputValidator implements Validator {

    private final String winningNumbersInput;
    private final String bonusNumberInput;

    private List<Integer> winningNumbers;
    private int bonusNumber;

    public WinningNumbersInputValidator(String winningNumbersInput, String bonusNumberInput) {
        this.winningNumbersInput = winningNumbersInput;
        this.bonusNumberInput = bonusNumberInput;
    }

    @Override
    public void validate() {
        this.validateInputExistence(this.winningNumbersInput);

        this.winningNumbers = this.parseAndValidateFormat(this.winningNumbersInput);

        new lotto.Lotto(this.winningNumbers);

        this.bonusNumber = this.validateAndParseBonusNumber(this.bonusNumberInput);

        this.validateDuplicationBetweenLottoAndBonus(this.winningNumbers, this.bonusNumber);
    }

    private void validateInputExistence(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력은 비어있을 수 없습니다.");
        }
    }

    private List<Integer> parseAndValidateFormat(String input) {
        String[] parts = input.split(Delimiter.COMMA);
        List<Integer> winningNumbers = new ArrayList<>();

        for (String part : parts) {
            String trimmedPart = part.trim();

            if (trimmedPart.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 쉼표(,)가 연속되거나 입력이 비어있을 수 없습니다.");
            }
            try {
                int number = Integer.parseInt(trimmedPart);
                winningNumbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자만 입력해야만 합니다.");
            }
        }
        return winningNumbers;
    }


    private int validateAndParseBonusNumber(String bonusNumberInput) {
        this.validateInputExistence(bonusNumberInput); // 존재성 검증 재활용

        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(bonusNumberInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 하나의 숫자여야 합니다.");
        }

        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 " + MIN_NUMBER + "에서 " + MAX_NUMBER + " 사이의 숫자여야 합니다.");
        }

        return bonusNumber;
    }

    private void validateDuplicationBetweenLottoAndBonus(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(this.winningNumbers);
    }

    public int getBonusNumber() {
        return this.bonusNumber;
    }
}