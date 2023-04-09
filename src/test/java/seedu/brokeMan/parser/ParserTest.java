package seedu.brokeMan.parser;

import org.junit.jupiter.api.Test;
import seedu.brokeMan.command.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void testViewBudget_currentMonthWithNoBudgetSet_success() {
        final String userInput1 = "viewBudget";
        Command command = Parser.parseCommand(userInput1);
        assertTrue(command instanceof InvalidCommand);
        command.execute();
    }
    @Test
    void validViewBudgetShouldReturnViewBudgetCommand() {
        final String userFirstInput = "setBudget 500";
        Command firstCommand = Parser.parseCommand(userFirstInput);
        firstCommand.execute();

        final String userSecondInput = "viewBudget";
        Command secondCommand = Parser.parseCommand(userSecondInput);

        assertTrue(secondCommand instanceof ViewBudgetCommand);
    }

    @Test
    void testViewBudget_validDateWithNoBudgetSet_success() {
        final String userInput1 = "viewBudget t/ 2002/12";
        Command command = Parser.parseCommand(userInput1);
        assertTrue(command instanceof ViewBudgetCommand);
        command.execute();
    }

    @Test
    void testViewBudget_validDate_success() {
        final String userInput = "viewBudget t/ 2000/12";
        Command command = Parser.parseCommand(userInput);
        command.execute();

        assertTrue(command instanceof ViewBudgetCommand);
    }

    @Test
    void testViewBudget_validDateWithSetBudget_success() {
        final String userInput1 = "setBudget 100 t/ 2000/12";
        Command firstCommand = Parser.parseCommand(userInput1);
        assertTrue(firstCommand instanceof SetBudgetCommand);
        firstCommand.execute();

        final String userInput2 = "viewBudget t/ 2000/12";
        Command secondCommand = Parser.parseCommand(userInput2);
        assertTrue(secondCommand instanceof ViewBudgetCommand);
        secondCommand.execute();
    }

    @Test
    void testViewBudget_validDateWithOverspendBudget_success() {
        final String userInput1 = "setBudget 0 t/ 2000/12";
        Command firstCommand = Parser.parseCommand(userInput1);
        firstCommand.execute();

        final String userInput2 = "addExpense a/ 100 d/ eat t/ 2000 12 10 10 10 c/ FOOD";
        Command secondCommand = Parser.parseCommand(userInput2);
        secondCommand.execute();

        final String userInput3 = "viewBudget t/ 2000/12";
        Command thirdCommand = Parser.parseCommand(userInput3);
        thirdCommand.execute();

        assertTrue(firstCommand instanceof SetBudgetCommand);
        assertTrue(secondCommand instanceof AddExpenseCommand);
        assertTrue(thirdCommand instanceof ViewBudgetCommand);

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();
    }

    @Test
    void validSetBudgetShouldReturnSetBudgetCommand() {
        final String userFullInput = "setBudget 500";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SetBudgetCommand);
    }

    @Test
    void testSetBudget_invalidBudgetAmount_returnInvalidCommand() {
        final String userFullInput = "setBudget five";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void randomCommandShouldReturnHelpCommand() {
        final String userFullInput = "random inputs";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof HelpCommand);
    }

    @Test
    void wrongAddIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "addIncome a/ d/ t/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongAddExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "addExpense wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongDeleteExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "deleteExpense wrong";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongDeleteIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "deleteIncome ";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongEditExpenseCommandShouldReturnInvalidCommand() {
        final String userFullInput = "editExpense a/ a/ a/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void wrongEditIncomeCommandShouldReturnInvalidCommand() {
        final String userFullInput = "editIncome wrong arguments";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validAddExpenseCommandShouldReturnAddExpenseCommand() {
        final String userFullInput = "addExpense a/ 4.00 d/ lunch t/ 2022 09 08 12 14 c/ FOOD";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof AddExpenseCommand);
    }

    @Test
    void testAddExpense_invalidAmount_returnInvalidCommand() {
        final String userFullInput = "addExpense a/ four d/ lunch t/ 2022 09 08 12 14 c/ FOOD";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void testAddExpense_invalidCategory_returnInvalidCommand() {
        final String userFullInput = "addExpense a/ 4 d/ lunch t/ 2022 09 08 12 14 c/ LUNCH";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void testAddExpense_emptyFlag_returnInvalidCommand() {
        final String userFullInput = "addExpense a/ 4 d/ lunch t/ 2022 09 08 12 14 c/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validAddIncomeCommandShouldReturnAddIncomeCommand() {
        final String userFullInput = "addIncome a/ 400 d/ stocks t/ 2023 12 11 12 41 c/ INVESTMENT";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteIncome 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof AddIncomeCommand);
    }

    @Test
    void testAddIncome_invalidAmount_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ four d/ stock earnings t/ 2022 09 08 12 14 c/ STOCK";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void testAddIncome_invalidCategory_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ 4 d/ allowance t/ 2022 09 08 12 14 c/ ALLOWANCE";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void testAddIncome_emptyFlag_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ 4 d/ allowance t/ 2022 09 08 12 14 c/";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void testAddIncome_invalidDate_returnInvalidCommand() {
        final String userFullInput = "addIncome a/ 4 d/ allowance t/ 1999 09 08 12 14 c/ OTHERS";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validDeleteExpenseCommandShouldReturnDeleteExpenseCommand() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2022 07 21 10 41 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "deleteExpense 1";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof DeleteExpenseCommand);
    }

    @Test
    void validDeleteIncomeCommandShouldReturnDeleteIncomeCommand() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 2012 04 29 01 40 c/ SALARY";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "deleteIncome 1";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof DeleteIncomeCommand);
    }

    @Test
    void validEditExpenseCommandShouldReturnEditExpenseCommand() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ info n/ brunch c/ FOOD";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void testEditExpense_editAmount_success() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ amount n/ 100";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void testEditExpense_editTime_success() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ time n/ 2000 10 10 10 10";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void testEditExpense_editCategory_success() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ category n/ ENTERTAINMENT";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditExpenseCommand);
    }

    @Test
    void testEditExpense_incorrectType_returnInvalidCommand() {
        final String userFirstInput = "addExpense a/ 4.0 d/ lunch t/ 2017 08 19 13 29 c/ FOOD";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editExpense i/ 1 t/ date n/ 2000 10 10 10 10";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validEditIncomeCommandShouldReturnEditIncomeCommand() {
        final String userFirstInput = "addIncome a/ 4000 d/ salary t/ 2000 10 19 12 42 c/ SALARY";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ amount n/ 3000";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteExpense 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();
        assertTrue(actualCommand instanceof EditIncomeCommand);
    }

    @Test
    void testEditIncome_editDescription_success() {
        final String userFirstInput = "addIncome a/ 4.0 d/ bonus t/ 2017 08 19 13 29 c/ SALARY";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ info n/ earning from stock";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteIncome 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditIncomeCommand);
    }

    @Test
    void testEditIncome_editTime_success() {
        final String userFirstInput = "addIncome a/ 4.0 d/ bonus t/ 2017 08 19 13 29 c/ SALARY";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ time n/ 2000 10 10 10 10";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteIncome 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditIncomeCommand);
    }

    @Test
    void testEditIncome_editCategory_success() {
        final String userFirstInput = "addIncome a/ 4.0 d/ bonus t/ 2017 08 19 13 29 c/ SALARY";
        Command addIncomeCommand = Parser.parseCommand(userFirstInput);
        addIncomeCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ category n/ OTHERS";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteIncome 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof EditIncomeCommand);
    }

    @Test
    void testEditIncome_incorrectType_returnInvalidCommand() {
        final String userFirstInput = "addIncome a/ 4.0 d/ stock t/ 2017 08 19 13 29 c/ Microsoft";
        Command addExpenseCommand = Parser.parseCommand(userFirstInput);
        addExpenseCommand.execute();
        final String userFullInput = "editIncome i/ 1 t/ date n/ 2000 10 10 10 10";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        final String cleanUp = "deleteIncome 1";
        Command cleanUpCommand = Parser.parseCommand(cleanUp);
        cleanUpCommand.execute();

        assertTrue(actualCommand instanceof InvalidCommand);
    }

    @Test
    void validListExpenseCommandShouldReturnListExpenseCommand() {
        final String userFullInput = "listExpense";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof ListExpenseCommand);
    }

    @Test
    void testExpenseList_validDate_success() {
        final String userFullInput = "listExpense t/ 2022/01";
        Command command = Parser.parseCommand(userFullInput);
        command.execute();

        assertTrue(command instanceof ListExpenseCommand);
    }

    @Test
    void validListIncomeCommandShouldReturnListIncomeCommand() {
        final String userFullInput = "listIncome";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof ListIncomeCommand);
    }

    @Test
    void testIncomeList_validDate_success() {
        final String userFullInput = "listIncome t/ 2022/01";
        Command command = Parser.parseCommand(userFullInput);
        command.execute();

        assertTrue(command instanceof ListIncomeCommand);
    }

    @Test
    void validSortExpenseByAmountCommandShouldReturnValidCommand() {
        final String userFullInput = "sortExpenseByAmount";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortExpenseByAmountCommand);
    }

    @Test
    void validSortIncomeByAmountCommandShouldReturnValidCommand() {
        final String userFullInput = "sortIncomeByAmount";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortIncomeByAmountCommand);
    }

    @Test
    void validSortExpenseByDateCommandShouldReturnValidCommand() {
        final String userFullInput = "sortExpenseByDate";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortExpenseByDateCommand);
    }

    @Test
    void validSortIncomeByDateCommandShouldReturnValidCommand() {
        final String userFullInput = "sortIncomeByDate";
        Command actualCommand = Parser.parseCommand(userFullInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof SortIncomeByDateCommand);
    }

    @Test
    public void testExit_validExit_success() {
        final String userInput = "exit";
        Command actualCommand = Parser.parseCommand(userInput);
        actualCommand.execute();

        assertTrue(actualCommand instanceof ExitCommand);
    }
}
