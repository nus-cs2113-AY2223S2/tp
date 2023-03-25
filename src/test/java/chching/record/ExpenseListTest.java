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
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("23-05-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 50;
    private Expense expense;
    private ExpenseList expenses;
    
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
    
    @Test
    void editExpense_editDate_success() {
        LocalDate expectedOutputDate = LocalDate.parse("23-05-2024", FORMATTER);
        try {
            expenses.editExpense(1, "da", "23-05-2024");
            assertEquals(CATEGORY, expenses.get(0).getCategory());
            assertEquals(DESCRIPTION, expenses.get(0).getDescription());
            assertEquals(expectedOutputDate, expenses.get(0).getDate());
            assertEquals(EXPENSE_VALUE, expenses.get(0).getValue());
        } catch (Exception e) {
            fail(); // test should not fail
        }
    }
    
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
