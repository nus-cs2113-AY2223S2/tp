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
    static final int EXPECTED_SIZE = 1;
    static final String CATEGORY = "entertainment";
    static final String DESCRIPTION = "beach party";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("23-05-2021", FORMATTER);
    static final float EXPENSE_VALUE = (float) 50;
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
            expenses.editExpense(1, "c", "food and drinks");
            System.out.println(expenses.getRecordCount());
            assertEquals(expectedOutputCategory, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail(); // test should not fail
        }
    }
    
    /**
     * JUnit test to test method to edit description of expense
     */
    @Test
    void editExpense_editDescription_success() {
        String expectedOutputDescription = "birthday party";
        try {
            expenses.editExpense(1, "de", "birthday party");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(expectedOutputDescription, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail(); // test should not fail
        }
    }
    
    /**
     * JUnit test to test method to edit date of expense
     */
    @Test
    void editExpense_editDate_success() {
        LocalDate expectedOutputDate = LocalDate.parse("23-05-2022", FORMATTER);
        try {
            expenses.editExpense(1, "da", "23-05-2022");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(expectedOutputDate, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
    
    /**
     * Junit Test to test editExpense method with a date that is in the future.
     * ChChing exception should be thrown.
     */
    @Test
    void editExpense_editFutureDate_exceptionThrown() {
        try {
            expenses.editExpense(1, "da", "05-04-2029");
            fail(); // test should not reach this line
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
            expenses.editExpense(1, "da", "30-02-2022");
            fail(); // test should not reach this line
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
            expenses.editExpense(1, "v", "100");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(DATE, expenses.get(0).getDate());
            assertEquals(expectedOutputValue, expenses.get(0).getValue());
        } catch (Exception e) {
            fail(); // test should not fail
        }
    }
}
