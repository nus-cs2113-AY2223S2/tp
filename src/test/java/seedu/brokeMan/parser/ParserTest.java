package seedu.brokeMan.parser;

import org.junit.jupiter.api.Test;
import seedu.brokeMan.command.AddExpenseCommand;
import seedu.brokeMan.command.AddIncomeCommand;
import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.DeleteExpenseCommand;
import seedu.brokeMan.command.DeleteIncomeCommand;
import seedu.brokeMan.command.EditExpenseCommand;
import seedu.brokeMan.command.EditIncomeCommand;
import seedu.brokeMan.command.HelpCommand;
import seedu.brokeMan.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void randomCommandShouldReturnHelpCommand() {
        final String userFullInput = "random inputs";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof HelpCommand);
    }

    @Test
    void wrongAddIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "addIncome a/ d/ t/";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongAddExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "addExpense wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongDeleteExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "deleteExpense wrong";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongDeleteIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "deleteIncome ";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongEditExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "editExpense a/ a/ a/";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongEditIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "editIncome wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validAddExpenseCommandShouldReturnAddExpenseCommand() {
        final String userFullInput = "addExpense a/ 4.0 d/ lunch t/ 12pm";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof AddExpenseCommand);
    }

    @Test
    void validAddIncomeCommandShouldReturnAddIncomeCommand() {
        final String userFullInput = "addIncome a/ 400 d/ stocks t/ 12pm";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof AddIncomeCommand);
    }

    @Test
    void validDeleteExpenseCommandShouldReturnDeleteExpenseCommand() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 12pm";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "deleteExpense 1";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof DeleteExpenseCommand);
    }

    @Test
    void validDeleteIncomeCommandShouldReturnDeleteIncomeCommand() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 12pm";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "deleteIncome 1";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof DeleteIncomeCommand);
    }

    @Test
    void validEditExpenseCommandShouldReturnEditExpenseCommand() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 12pm";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ info n/ brunch";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void validEditIncomeCommandShouldReturnEditIncomeCommand() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 12pm";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ income n/ 3000";
        Command actualCommand = Parser.parseCommand(userFullInput);

        assertTrue(actualCommand instanceof EditIncomeCommand);
    }
}