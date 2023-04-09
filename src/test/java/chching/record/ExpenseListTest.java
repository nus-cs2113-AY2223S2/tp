package chching.record;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for ExpenseList
 */
class ExpenseListTest {
    public static final int EXPECTED_SIZE = 1;
    public static final String CATEGORY = "entertainment";
    public static final String DESCRIPTION = "beach party";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    public static final LocalDate DATE = LocalDate.parse("23-05-2021", FORMATTER);
    public static final float EXPENSE_VALUE = (float) 50;
    public static final String CATEGORY_FIELD = "c";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    private Expense expense;
    private ExpenseList expenses;
    
    /**
     * Sets up ExpenseList expenses with one expense for each JUnit testing
     * expense contains the following details:
     * category: entertainment
     * description: beach party
     * date: 23-05-2023
     * value: 50
     */
    @BeforeEach
    void setUp() {
        expense = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(expense);
        expenses = new ExpenseList(expenseList);
    }

    /**
     * Junit Test to test method that returns the size of ExpenseList
     */
    @Test
    void getExpenseCount_one_expectOne() {

        Expense expenseOne = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        ExpenseList list = new ExpenseList();
        list.addRecord(expenseOne);
        assertEquals(EXPECTED_SIZE, list.getRecordCount());
    }
    
    /**
     * JUnit test to test method to edit category of expense
     */
    @Test
    void editExpense_editCategory_success() {
        String expectedOutputCategory = "food and drinks";
        try {
            expenses.editExpense(1, CATEGORY_FIELD, "food and drinks");
            System.out.println(expenses.getRecordCount());
            assertEquals(expectedOutputCategory, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * JUnit test to test method to edit description of expense
     */
    @Test
    void editExpense_editDescription_success() {
        String expectedOutputDescription = "birthday party";
        try {
            expenses.editExpense(1, DESCRIPTION_FIELD, "birthday party");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(expectedOutputDescription, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * JUnit test to test method to edit date of expense
     */
    @Test
    void editExpense_editDate_success() {
        LocalDate expectedOutputDate = LocalDate.parse("23-05-2022", FORMATTER);
        try {
            expenses.editExpense(1, DATE_FIELD, "23-05-2022");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(expectedOutputDate, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Junit Test to test editExpense method with a date that is in the future.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editFutureDate_exceptionThrown() {
        try {
            expenses.editExpense(1, DATE_FIELD, "05-04-2029");
            fail();
        } catch (Exception e) {
            assertEquals("Date cannot be in the future", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editExpense method with a date that is not possible.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editInvalidDate_exceptionThrown() {
        try {
            expenses.editExpense(1, DATE_FIELD, "30-02-2022");
            fail();
        } catch (Exception e) {
            assertEquals("Date must be valid and have format: \"DD-MM-YYYY\"", e.getMessage());
        }
    }
    
    /**
     * JUnit test to test method to edit value of expense
     */
    @Test
    void editExpense_editValue_success() {
        float expectedOutputValue = (float) 100;
        try {
            expenses.editExpense(1, VALUE_FIELD, "100");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(expectedOutputValue, expenses.get(0).getValue());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Junit Test to test editExpense method with a value that is negative.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editNegativeValue_exceptionThrown() {
        try {
            expenses.editExpense(1, VALUE_FIELD, "-100");
            fail();
        } catch (Exception e) {
            assertEquals("Expense value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editExpense method with a value that has more than 2 decimal places.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editValueWithMoreThanTwoDecimalPlaces_exceptionThrown() {
        try {
            expenses.editExpense(1, VALUE_FIELD, "100.123");
            fail();
        } catch (Exception e) {
            assertEquals("Expense value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editExpense method with a value that is not a double.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editValueNotDouble_exceptionThrown() {
        try {
            expenses.editExpense(1, VALUE_FIELD, "abc");
            fail();
        } catch (Exception e) {
            assertEquals("Expense value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * JUnit Test to test editExpense method with a value that is more than 999999.99
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editValueMoreThan999999d99_exceptionThrown() {
        try {
            expenses.editExpense(1, VALUE_FIELD, "1000000");
            fail();
        } catch (Exception e) {
            assertEquals("Expense value must be less than 1000000", e.getMessage());
        }
    }
    
    /**
     * JUnit Test to test editExpense method with a value that is less than 0.01
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editValueLessThan0d01_exceptionThrown() {
        try {
            expenses.editExpense(1, VALUE_FIELD, "0.00");
            fail();
        } catch (Exception e) {
            assertEquals("Expense value must be greater than 0", e.getMessage());
        }
    }
}
