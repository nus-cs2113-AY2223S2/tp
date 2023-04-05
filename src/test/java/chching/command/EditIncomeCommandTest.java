package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for EditIncomeCommand
 */
public class EditIncomeCommandTest {
    public static final String SALARY_DESCRIPTION = "salary";
    public static final int SALARY_VALUE = 5000;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    public static final LocalDate SALARY_DATE = LocalDate.parse("02-04-2023", FORMATTER);
    public static final String DESCRIPTION_VALUE = "bonus";
    public static final String DATE_VALUE = "03-04-2023";
    public static final String VALUE_VALUE = "600";
    public static final String INDEX_FIELD = "in";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    public static final String INDEX_VALUE = "1";
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList incomes;
    private ExpenseList expenseList;
    private Income salary;
    
    /**
     * Set up the test environment.
     * creates IncomeList incomes with one Income salary.
     * salary has description SALARY_DESCRIPTION, date SALARY_DATE, value SALARY_VALUE.
     */
    @BeforeEach
    void setUp() {
        ui = new Ui();
        
        salary = new Income(SALARY_DESCRIPTION, SALARY_DATE, SALARY_VALUE);
        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        incomes = new IncomeList(incomeList);
    }
    
    /**
     * JUnit Test for EditIncomeCommand.execute() when the command is valid.
     * The description, date and value of the income should be updated.
     */
    @Test
    void execute_normalScenario_success() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, INDEX_VALUE);
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editIncomeCommand = new EditIncomeCommand(argumentsByField);
            editIncomeCommand.execute(incomes, expenseList, ui, storage, selector, converter, targetStorage);
            
            assertEquals(DESCRIPTION_VALUE, incomes.get(0).getDescription());
            assertEquals(LocalDate.parse(DATE_VALUE, FORMATTER), incomes.get(0).getDate());
            assertEquals(Float.parseFloat(VALUE_VALUE), incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(); // test should not reach this line
        }
    }
    
    /**
     * JUnit Test for EditIncomeCommand.execute() when there is no index given.
     * ChChingException should be thrown indicating "Missing/Invalid index".
     */
    @Test
    void execute_noIndex_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editIncomeCommand = new EditIncomeCommand(argumentsByField);
            editIncomeCommand.execute(incomes, expenseList, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Index field not found", e.getMessage());
        }
    }
    
    /**
     * JUnit Test for EditIncomeCommand.execute() when the index given is negative.
     * ChChingException should be thrown indicating "Negative/Zero index".
     */
    @Test
    void execute_negativeIndex_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, "-1");
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editIncomeCommand = new EditIncomeCommand(argumentsByField);
            editIncomeCommand.execute(incomes, expenseList, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Negative/Zero index", e.getMessage());
        }
    }
    
    /**
     * JUnit Test for EditIncomeCommand.execute() when the index given more than size of incomes.
     * ChChingException should be thrown indicating "The index is too big".
     */
    @Test
    void execute_indexOutOfBounds_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, "2");
            argumentsByField.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
            argumentsByField.put(DATE_FIELD, DATE_VALUE);
            argumentsByField.put(VALUE_FIELD, VALUE_VALUE);
            
            Command editIncomeCommand = new EditIncomeCommand(argumentsByField);
            editIncomeCommand.execute(incomes, expenseList, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("The index is too big", e.getMessage());
        }
    }
    
    /**
     * JUnit Test for EditIncomeCommand.execute() when there are no fields given to be edited.
     * ChChingException should be thrown indicating "No fields to edit".
     */
    @Test
    void execute_noFields_exceptionThrown() {
        try {
            HashMap<String, String> argumentsByField = new HashMap<String, String>();
            argumentsByField.put(INDEX_FIELD, INDEX_VALUE);
            
            Command editIncomeCommand = new EditIncomeCommand(argumentsByField);
            editIncomeCommand.execute(incomes, expenseList, ui, storage, selector, converter, targetStorage);
            
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("No fields to edit", e.getMessage());
        }
    }
}
