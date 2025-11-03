package view;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.Lotto;
import result.Rank;
import view.constants.SystemMessage;

public class OutputView {

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.US);

    public void printPurchasedLottos(List<Lotto> purchasedLottos) {
        int count = purchasedLottos.size();

        String countMessage = String.format(SystemMessage.RESULT_LOTTO_COUNT.getSystemMessage(), count);
        System.out.println(countMessage);

        for (Lotto lotto : purchasedLottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public void printWinningResult(Map<Rank, Integer> resultCounts, double returnRate) {
        System.out.println(SystemMessage.WINNING_STATISTICS_TITLE.getSystemMessage());
        System.out.println(SystemMessage.SEPARATOR.getSystemMessage());

        printAllRankDetails(resultCounts);

        printReturnRate(returnRate);
    }

    private void printAllRankDetails(Map<Rank, Integer> resultCounts) {
        List<Rank> allRanks = Arrays.stream(Rank.values()).collect(Collectors.toList());
        Collections.reverse(allRanks);

        for (Rank rank : allRanks) {
            if (rank == Rank.MISS) {
                continue;
            }

            int count = resultCounts.getOrDefault(rank, 0);
            String prizeFormatted = NUMBER_FORMAT.format(rank.getWinningPrize());

            printRankDetail(rank, count, prizeFormatted);
        }
    }

    private void printRankDetail(Rank rank, int count, String prizeFormatted) {
        String message;

        if (rank == Rank.SECOND) {
            message = String.format(
                    SystemMessage.RANK_RESULT_SECOND_PLACE_FORMAT.getSystemMessage(),
                    prizeFormatted, count);
            System.out.println(message);
            return;
        }

        message = String.format(
                SystemMessage.RANK_RESULT_FORMAT.getSystemMessage(),
                rank.getMatchCount(), prizeFormatted, count);
        System.out.println(message);
    }

    private void printReturnRate(double returnRate) {
        String formattedRate = String.format("%.1f", returnRate);
        String rateMessage = String.format(SystemMessage.RETURN_RATE_MESSAGE.getSystemMessage(), formattedRate);
        System.out.println(rateMessage);
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}