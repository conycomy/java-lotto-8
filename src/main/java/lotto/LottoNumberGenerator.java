package lotto;

import static lotto.LottoNumberRange.MAX_NUMBER;
import static lotto.LottoNumberRange.MIN_NUMBER;
import static lotto.LottoNumberRange.NUMBERS_PER_TICKET;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static List<Integer> generate() {
        List<Integer> immutableNumbers = generateUniqueRandomNumbers();
        List<Integer> mutableNumbers = new ArrayList<>(immutableNumbers);
        sortNumbers(mutableNumbers);
        return Collections.unmodifiableList(mutableNumbers);
    }

    private static List<Integer> generateUniqueRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBERS_PER_TICKET);
    }


    private static void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }
}
