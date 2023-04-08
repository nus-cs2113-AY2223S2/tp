package seedu.brokeMan;

import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.ExitCommand;
import seedu.brokeMan.parser.Parser;
import seedu.brokeMan.save.SaveBudget;
import seedu.brokeMan.save.SaveExpense;
import seedu.brokeMan.save.SaveIncome;
import seedu.brokeMan.ui.Ui;

import java.util.NoSuchElementException;

public class BrokeMan {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        new BrokeMan().run();
    }

    public static void run() {
        Ui.showWelcomeMessages();
        runCommandUntilExitCommand();
        Ui.showGoodByeMessages();
    }

    public static void runCommandUntilExitCommand() {
        Command command = null;
        SaveExpense.readExpenseFile();
        SaveIncome.readIncomeFile();
        SaveBudget.readFile();
        String userFullInput;

        do {
            try {
                userFullInput = Ui.getUserCommand();
                command = Parser.parseCommand(userFullInput);
                command.execute();
            } catch (NoSuchElementException e) {
                Ui.showToUser("Invalid input...");
                command = new ExitCommand();
                command.execute();
            }
        } while (!ExitCommand.isExit(command));
    }
}
