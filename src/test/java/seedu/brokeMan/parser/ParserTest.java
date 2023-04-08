package seedu.brokeMan.parser;

import org.junit.jupiter.api.Test;
import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.DeleteExpenseCommand;
import seedu.brokeMan.command.DeleteIncomeCommand;
import seedu.brokeMan.command.EditExpenseCommand;
import seedu.brokeMan.command.EditIncomeCommand;
import seedu.brokeMan.command.ExitCommand;
import seedu.brokeMan.command.HelpCommand;
import seedu.brokeMan.command.InvalidCommand;
import seedu.brokeMan.command.ListExpenseCommand;
import seedu.brokeMan.command.ListIncomeCommand;
import seedu.brokeMan.command.SortExpenseByAmountCommand;
import seedu.brokeMan.command.SortExpenseByDateCommand;
import seedu.brokeMan.command.SortIncomeByAmountCommand;
import seedu.brokeMan.command.SortIncomeByDateCommand;
import seedu.brokeMan.command.ViewBudgetCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parseCommand_validViewBudget_success() {
        final String userFirstInput = "setBudget 500";
        Command firstCommand = Parser.parseCommand(userFirstInput);
        firstCommand.execute();

        final String userSecondInput = "viewBudget";
        Command secondCommand = Parser.parseCommand(userSecondInput);
        secondCommand.execute();

        assertTrue(secondCommand instanceof ViewBudgetCommand);
    }

    @Test
    void parseCommand_validViewBudgetWithDate_success() {
        final String userFirstInput = "setBudget 600 t/ 2023/02";
        Command firstCommand = Parser.parseCommand(userFirstInput);
        firstCommand.execute();

        final String userSecondInput = "viewBudget t/ 2023/02";
        Command secondCommand = Parser.parseCommand(userSecondInput);
        secondCommand.execute();

        assertTrue(secondCommand instanceof ViewBudgetCommand);
    }

    @Test
    void parseCommand_invalidDateForViewBudget_returnInvalidCommand() {
        final String userSecondInput = "viewBudget t/ 203/02";
        Command command = Parser.parseCommand(userSecondInput);
        command.execute();

        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_stringBudget_returnInvalidCommand() {
        final String userFullInput = "setBudget hello";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_duplicatedFlags_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ a/ d/ t/ c/";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_incorrectType_returnInvalidCommand() {
        final String userFullInput = "editExpense i/ 1 t/ WRONG n/ hello";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_negativeAmount_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ -303 d/ drugs?? t/ 2023 02 02 02 02 c/ OTHERS";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_newDescriptionContainFlags_returnInvalidCommand() {
        final String userFullInput = "editIncome i/ 1 t/ info n/ c/";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_wrongFlagOrder_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ t/ d/ c/";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_invalidOptionalTimeFlag_returnInvalidCommand() {
        final String userFullInput = "viewBudget c/";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_invalidTime_returnInvalidCommand() {
        final String userFullInput = "editIncome i/ 1 t/ time n/ random";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_randomInput_returnHelpCommand() {
        final String userFullInput = "random inputs";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof HelpCommand);
    }

    @Test
    void parseCommand_emptyFlagDescription_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ d/ t/ c/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_invalidCategory_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ 200 d/ salary t/ 2023 02 02 02 02 c/ WRONG";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_amountAsString_returnInvalidCommand() {
        final String userFullInput = "addExpense a/ double d/ lunch t/ 2023 02 02 02 02 c/ FOOD";
        Command command = Parser.parseCommand(userFullInput);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_wrongArgumentsForAddExpense_returnInvalidCommand() {
        final String userFullInput = "addExpense wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_stringIndexForDeleteExpense_returnInvalidCommand() {
        final String userFullInput = "deleteExpense wrong";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_emptyDeleteIncomeArguments_returnInvalidCommand() {
        final String userFullInput = "deleteIncome ";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_invalidEditExpenseCommand_returnInvalidCommand() {
        final String userFullInput = "editExpense a/ a/ a/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_invalidEditIncomeCommand_returnInvalidCommand() {
        final String userFullInput = "editIncome wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void parseCommand_validDeleteExpenseCommand_success() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2022 07 21 10 41 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "deleteExpense 1";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof DeleteExpenseCommand);
    }

    @Test
    void parseCommand_validDeleteIncomeCommand_success() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 2012 04 29 01 40 c/ SALARY";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "deleteIncome 1";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof DeleteIncomeCommand);
    }

    @Test
    void parseCommand_validEditExpenseCommand_success() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ info n/ brunch";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void parseCommand_validEditIncomeCommand_success() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 2000 10 19 12 42 c/ SALARY";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ amount n/ 3000";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof EditIncomeCommand);
    }

    @Test
    void parseCommand_validListExpense_success() {
        final String userFullInput = "listExpense";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof ListExpenseCommand);
    }

    @Test
    void parseCommand_validListExpenseWithDate_success() {
        final String userFullInput = "listExpense t/ 2023/04";
        Command command = Parser.parseCommand(userFullInput);
        command.execute();

        assertTrue(command instanceof ListExpenseCommand);
    }

    @Test
    void parseCommand_validListIncomeWithDate_success() {
        final String userFullInput = "listIncome t/ 2023/04";
        Command command = Parser.parseCommand(userFullInput);
        command.execute();

        assertTrue(command instanceof ListIncomeCommand);
    }

    @Test
    void parseCommand_validListIncome_success() {
        final String userFullInput = "listIncome";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof ListIncomeCommand);
    }

    @Test
    void parseCommand_validSortExpenseByAmountCommand_success() {
        final String userFullInput = "sortExpenseByAmount";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortExpenseByAmountCommand);
    }

    @Test
    void parseCommand_validSortIncomeByAmountCommand_success() {
        final String userFullInput = "sortIncomeByAmount";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortIncomeByAmountCommand);
    }

    @Test
    void parseCommand_validSortExpenseByDateCommand_success() {
        final String userFullInput = "sortExpenseByDate";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortExpenseByDateCommand);
    }

    @Test
    void parseCommand_validSortIncomeByDateCommand_success() {
        final String userFullInput = "sortIncomeByDate";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortIncomeByDateCommand);
    }

    @Test
    void parseCommand_validExitCommand_success() {
        final String userFullInput = "exit";
        Command command = Parser.parseCommand(userFullInput);
        assertEquals(ExitCommand.isExit(command), true);
        command.execute();

        assertTrue(command instanceof ExitCommand);
    }
}
