package lotto;

import static lotto.LottoNumberRange.MAX_NUMBER;
import static lotto.LottoNumberRange.MIN_NUMBER;
import static lotto.LottoNumberRange.NUMBERS_PER_TICKET;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateAllRules(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int matchCount(Lotto winningLotto) {
        Set<Integer> customerNumbers = new HashSet<>(this.numbers);

        List<Integer> winningNumbers = winningLotto.getNumbers();

        customerNumbers.retainAll(winningNumbers);
        return customerNumbers.size();
    }

    public boolean contains(int number) {
        return this.numbers.contains(number);
    }

    private static void validateAllRules(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_PER_TICKET) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        checkDuplicate(numbers);

        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1에서 45 사이의 숫자여야 합니다.");
            }
        }
        checkDuplicate(numbers);
    }

    private static void checkDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복이 있습니다.");
        }
    }
}
