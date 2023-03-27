package chching.command;

import chching.ChChingException;
import chching.Ui;
import chching.Storage;
import chching.record.*;
import chching.currency.Converter;
import chching.currency.Selector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ListCommandTest {
    static final String CATEGORY = "transport";
    static final String EXPENSE_DESCRIPTION = "public transport";
    static final String INCOME_DESCRIPTION = "ALLOWANCE";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 1.50;
    static final float INCOME_VALUE = (float) 200;
    private Ui ui = new Ui();
    private Storage storage;
    private Selector selector = new Selector();
    private Converter converter = new Converter();
    private TargetStorage targetStorage = new TargetStorage();
    private IncomeList defaultIncomeList = new IncomeList();
    private ExpenseList defaultExpenseList = new ExpenseList();
    private Expense expenseDemo = new Expense(CATEGORY, EXPENSE_DESCRIPTION, DATE, EXPENSE_VALUE);
    private Income incomeDemo = new Income(INCOME_DESCRIPTION, DATE, INCOME_VALUE);

    @Test
    void execute_ListCommand_empty_NoException() throws ChChingException {
            Command command = new ListCommand();
            assertDoesNotThrow( () ->command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage));
    }

    @Test
    void execute_ListCommand_expenseAndIncome_NoException() throws ChChingException {
        defaultIncomeList.addIncome(incomeDemo);
        defaultExpenseList.addExpense(expenseDemo);
        Command command = new ListCommand();
        assertDoesNotThrow( () ->command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage));
    }
}
