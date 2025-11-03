package result;

import static lotto.LottoNumberRange.MAX_NUMBER;
import static lotto.LottoNumberRange.MIN_NUMBER;
import java.util.ArrayList;
import java.util.List;
import utils.Delimiter;
import utils.Validator;

public class WinningNumbersInputValidator implements Validator {

    private final String winningNumbersInput;
    private final String bonusNumberInput;

    public WinningNumbersInputValidator(String winningNumbersInput, String bonusNumberInput) {
        this.winningNumbersInput = winningNumbersInput;
        this.bonusNumberInput = bonusNumberInput;
    }

    @Override
    public void validate() {
        validateInputExistence(this.winningNumbersInput);
        List<Integer> winningNumbers = parseAndValidateFormat(this.winningNumbersInput);

        new lotto.Lotto(winningNumbers);
        int bonusNumber = validateAndParseBonusNumber(this.bonusNumberInput);

        validateDuplicationBetweenLottoAndBonus(winningNumbers, bonusNumber);
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
        validateInputExistence(bonusNumberInput);

        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(bonusNumberInput.trim()); // 파싱 시도
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
}


