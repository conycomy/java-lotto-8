package result;

import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import lotto.Lotto;

public class LottoResultChecker {

    private final Map<Rank, Integer> resultCounts;
    private final List<Lotto> purchasedLottos;
    private final WinningLotto winningLotto;
    private final int purchaseAmount;

    public LottoResultChecker(List<Lotto> purchasedLottos, WinningLotto winningLotto, int purchaseAmount) {
        this.purchasedLottos = purchasedLottos;
        this.winningLotto = winningLotto;
        this.purchaseAmount = purchaseAmount;
        this.resultCounts = new EnumMap<>(Rank.class);

        initializeResultCounts();
        calculateResults();
    }

    private void initializeResultCounts() {
        for (Rank rank : Rank.values()) {
            resultCounts.put(rank, 0);
        }
    }

    private void calculateResults() {
        for (Lotto customerLotto : purchasedLottos) { // 깊이 1
            Rank rank = winningLotto.getRank(customerLotto);

            if (rank != Rank.MISS) { // 깊이 2
                resultCounts.put(rank, resultCounts.get(rank) + 1);
            }
        }
    }

    private long calculateTotalPrize() {
        long total = 0;
        for (Map.Entry<Rank, Integer> entry : resultCounts.entrySet()) { // 깊이 1
            Rank rank = entry.getKey();
            int count = entry.getValue();

            total += (long) rank.getWinningPrize() * count;
        }
        return total;
    }

    public double calculateReturnRate() {
        long totalPrize = calculateTotalPrize();
        double returnRate = ((double) totalPrize / purchaseAmount) * 100;

        return Math.round(returnRate * 10.0) / 10.0;
    }

    public Map<Rank, Integer> getResultCounts() {
        return resultCounts;
    }
}