package chching.record;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for IncomeList
 */
class IncomeListTest {

    static final String DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 1000000;
    private Income income;
    private IncomeList incomes;
    
    /**
     * Set up the test environment.
     * creates IncomeList incomes with one Income income.
     * income has description DESCRIPTION, date DATE, value INCOME_VALUE.
     */
    @BeforeEach
    void setUp() {
        income = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(income);
        incomes = new IncomeList(incomeList);
    }

    /**
     * Junit Test to test method that returns the size of IncomeList
     */
    @Test
    void getExpenseCount_one_expectOne() {

        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        IncomeList list = new IncomeList();
        list.addRecord(income0);
        assertEquals(1, list.getRecordCount());
    }

    /**
     * Junit Test to test if editIncome method can edit description of income.
     * income should only have its description changed to "bonus"
     */
    @Test
    void editIncome_editDescription_success() {
        String expectedOutputDescription = "bonus";
        try {
            incomes.editIncome(1, "de", "bonus");
            assertEquals(expectedOutputDescription, incomes.get(0).getDescription());
            assertEquals(DATE, incomes.get(0).getDate());
            assertEquals(INCOME_VALUE, incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    /**
     * Junit Test to test if editIncome method can edit date of income.
     * income should only have its date changed to 05-04-2024.
     */
    @Test
    void editIncome_editDate_success() {
        LocalDate expectedOutputDate = LocalDate.parse("05-04-2022", FORMATTER);
        try {
            incomes.editIncome(1, "da", "05-04-2022");
            assertEquals(DESCRIPTION, incomes.get(0).getDescription());
            assertEquals(expectedOutputDate, incomes.get(0).getDate());
            assertEquals(INCOME_VALUE, incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(); // test should not reach this line
        }
    }
    
    /**
     * Junit Test to test editIncome method with a date that is in the future.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editFutureDate_exceptionThrown() {
        try {
            incomes.editIncome(1, "da", "05-04-2029");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Date cannot be in the future", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editIncome method with a date that is not possible.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editInvalidDate_exceptionThrown() {
        try {
            incomes.editIncome(1, "da", "31-02-2022");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Date must be valid with format: \"DD-MM-YYYY\"", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test if editIncome method can edit value of income.
     * income should only have its value changed to 200000.
     */
    @Test
    void editIncome_editValue_success() {
        float expectedOutputValue = (float) 200000;
        try {
            incomes.editIncome(1, "v", "200000");
            assertEquals(DESCRIPTION, incomes.get(0).getDescription());
            assertEquals(DATE, incomes.get(0).getDate());
            assertEquals(expectedOutputValue, incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(); // test should not reach this line
        }
    }
}
