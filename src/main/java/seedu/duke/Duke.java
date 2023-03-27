package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;

import java.math.BigDecimal;

public class Duke {

    private static Ui ui;
    private static final AccountList accounts = new AccountList();

    /**
     * Runs the main input loop until the exit command is called
     */
    public static void run () {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                ui.printSpacer();
                Command c = Parser.parseInput(fullCommand);
                c.execute(ui, accounts);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printSpacer();
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main (String[] args) {
        TransactionManager transactionManager = TransactionManager.getInstance();
        transactionManager.addTransaction(Currency.SGD, "Buy chicken rice", false, BigDecimal.valueOf(5), BigDecimal.valueOf(10));
        transactionManager.addTransaction(Currency.SGD, "Buy chicken rice", false, BigDecimal.valueOf(5), BigDecimal.valueOf(10));
        ui = new Ui();
        ui.printGreeting();
        ui.printSpacer();
        run();
    }
}
