package chching.command;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearCommandTest {
    static final String CATEGORY = "transport";
    static final String EXPENSE_DESCRIPTION = "public transport";
    static final String INCOME_DESCRIPTION = "ALLOWANCE";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
    }
    @Test
    public void execute_ClearAllCommand_success() {
        try {
            Command command = new ClearAllCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertTrue(defaultExpenseList.size() == 0 && defaultIncomeList.size() == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void execute_ClearExpenseCommand_success() {
        try {
            Command command = new ClearExpenseCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertTrue(defaultExpenseList.size() == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void executeClear_IncomeCommand_success() {
        try {
            Command command = new ClearIncomeCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertTrue(defaultIncomeList.size() == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
