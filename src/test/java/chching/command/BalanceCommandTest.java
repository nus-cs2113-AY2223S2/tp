package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.currency.Selector;
import chching.currency.Converter;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


class BalanceCommandTest {
    static final String INCOME_DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate INCOME_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 5000;
    static final String EXPENSE_CATEGORY = "grocery";
    static final String EXPENSE_DESCRIPTION = "too much groceries";
    static final LocalDate EXPENSE_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 500;

    private Ui ui;
    private Storage storage;
    private Income salary;
    private Expense groceries;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    
    @BeforeEach
    void setup() {
        ui = new Ui();
        
        salary = new Income(INCOME_DESCRIPTION, INCOME_DATE, INCOME_VALUE);
        groceries = new Expense(EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE, EXPENSE_VALUE);
        selector = new Selector();
        converter = new Converter();
        targetStorage = new TargetStorage();

        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        defaultIncomeList = new IncomeList(incomeList);
        
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }
}
