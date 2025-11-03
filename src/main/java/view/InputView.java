package view;
import camp.nextstep.edu.missionutils.Console;
import view.constants.SystemMessage;

public class InputView {
    public String inputPurchaseAmount() {
        while (true) {
            try {
                System.out.println(SystemMessage.INPUT_PURCHASE_AMOUNT.getSystemMessage());
                String input = Console.readLine();

                return input;

            } catch(IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public String inputWinningNumbers() {
        while (true) {
            try {
                System.out.println(SystemMessage.INPUT_WINNING_NUMBERS.getSystemMessage());
                String input = Console.readLine();

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public String inputBonusNumber() {
        while (true) {
            try {
                System.out.println(SystemMessage.INPUT_BONUS_NUMBER.getSystemMessage());
                String input = Console.readLine();
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}

