package view.constants;

public enum SystemMessage {
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해주세요"),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해주세요"),

    WINNING_STATISTICS_TITLE("당첨 통계"),

    SEPARATOR("---"),

    RESULT_LOTTO_COUNT("%s개를 구매했습니다."),

    RANK_RESULT_FORMAT("%s개 일치 (%s원) - %s개"),

    RANK_RESULT_SECOND_PLACE_FORMAT("5개 일치, 보너스 볼 일치 (%s원) - %s개"),

    RETURN_RATE_MESSAGE("총 수익률은 %s%%입니다.");


    private final String systemMessage;

    SystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getSystemMessage() {
        return systemMessage;
    }
}
