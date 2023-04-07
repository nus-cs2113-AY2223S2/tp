package chching.command;

import chching.Ui;
import chching.Storage;
import chching.record.Income;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Target;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit Test for ClearTargetCommand
 */
public class ClearTargetCommandTest {
    static final String CATEGORY = "transport";
    static final String EXPENSE_DESCRIPTION = "public transport";
    static final String INCOME_DESCRIPTION = "ALLOWANCE";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 1.50;
    static final float INCOME_VALUE = (float) 200;
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Expense expenseDemo;
    private Income incomeDemo;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        converter = new Converter();
        targetStorage = new TargetStorage();
        selector = new Selector();

        ArrayList<Expense> expenseList = new ArrayList<>();
        expenseDemo = new Expense(CATEGORY, EXPENSE_DESCRIPTION, DATE, EXPENSE_VALUE);
        expenseList.add(expenseDemo);
        defaultExpenseList = new ExpenseList(expenseList);

        ArrayList<Income> incomeList = new ArrayList<>();
        incomeDemo = new Income(INCOME_DESCRIPTION, DATE, INCOME_VALUE);
        incomeList.add(incomeDemo);
        defaultIncomeList = new IncomeList(incomeList);

        Target defaultTarget = new Target(350);
        targetStorage.addTarget(defaultTarget);
    }

    /**
     * Junit Test for clearTarget command to clear target
     */
    @Test
    public void execute_clearTargetCommand_success() {
        try {
            Command command = new ClearTargetCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertTrue(targetStorage.getTarget().getValue() == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}