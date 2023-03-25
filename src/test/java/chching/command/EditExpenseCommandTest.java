package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.TargetStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EditExpenseCommandTest {
    static final String GROCERIES_CATEGORY = "grocery";
    static final String GROCERIES_DESCRIPTION = "too much groceries";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate GROCERIES_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float GROCERIES_VALUE = 500;
    static final String INDEX_FIELD = "in";
    static final String INDEX_VALUE = "1";
    static final String CATEGORY_FIELD = "c";
    static final String CATEGORY_VALUE = "food";
    static final String DESCRIPTION_FIELD = "de";
    static final String DESCRIPTION_VALUE = "too much food";
    static final String DATE_FIELD = "da";
    static final String DATE_VALUE = "02-04-2023";
    static final String VALUE_FIELD = "v";
    static final String VALUE_VALUE = "50";
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList incomeList;
    private ExpenseList expenses;
    private Expense groceries;
    
    @BeforeEach
    void setUp() {
        ui = new Ui();
        
        groceries = new Expense(GROCERIES_CATEGORY, GROCERIES_DESCRIPTION, GROCERIES_DATE, GROCERIES_VALUE);
    
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(groceries);
        expenses = new ExpenseList(expenseList);
    }
    
    @Test
    void execute_normalScenario_success() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, INDEX_VALUE);
            argumentsByField.put(CATEGORY_FIELD, CATEGORY_VALUE);
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editExpenseCommand = new EditExpenseCommand(argumentsByField);
            editExpenseCommand.execute(incomeList, expenses, ui, storage, selector, converter, targetStorage);
            
            assertEquals(CATEGORY_VALUE, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION_VALUE, expenses.get(0).getDescription());
            assertEquals(LocalDate.parse(DATE_VALUE, FORMATTER), expenses.get(0).getDate());
            assertEquals(Float.parseFloat(VALUE_VALUE), expenses.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(); // test should not reach this line
        }
    }
    
    @Test
    void execute_noIndex_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(CATEGORY_FIELD, CATEGORY_VALUE);
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editExpenseCommand = new EditExpenseCommand(argumentsByField);
            // exception should be thrown before execute even happens
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing/invalid index", e.getMessage());
        }
    }
    
    @Test
    void execute_negativeIndex_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, "-1");
            argumentsByField.put(CATEGORY_FIELD, CATEGORY_VALUE);
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editExpenseCommand = new EditExpenseCommand(argumentsByField);
            editExpenseCommand.execute(incomeList, expenses, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Negative/Zero index", e.getMessage());
        }
    }
    
    @Test
    void execute_indexOutOfBounds_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, "2");
            argumentsByField.put(CATEGORY_FIELD, CATEGORY_VALUE);
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editExpenseCommand = new EditExpenseCommand(argumentsByField);
            editExpenseCommand.execute(incomeList, expenses, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("The index is too big", e.getMessage());
        }
    }
    
    @Test
    void execute_noFields_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, INDEX_VALUE);
            
            Command editExpenseCommand = new EditExpenseCommand(argumentsByField);
            editExpenseCommand.execute(incomeList, expenses, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("No fields to edit", e.getMessage());
        }
    }
}
