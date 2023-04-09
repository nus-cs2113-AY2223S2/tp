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
    public static final String DESCRIPTION = "salary";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    public static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    public static final float INCOME_VALUE = (float) 1000000;
    public static final String VALUE_FIELD = "v";
    public static final String DATE_FIELD = "da";
    public static final String DESCRIPTION_FIELD = "de";
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
            incomes.editIncome(1, DESCRIPTION_FIELD, "bonus");
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
            incomes.editIncome(1, DATE_FIELD, "05-04-2022");
            assertEquals(DESCRIPTION, incomes.get(0).getDescription());
            assertEquals(expectedOutputDate, incomes.get(0).getDate());
            assertEquals(INCOME_VALUE, incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    /**
     * Junit Test to test editIncome method with a date that is in the future.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editFutureDate_exceptionThrown() {
        try {
            incomes.editIncome(1, DATE_FIELD, "05-04-2029");
            fail();
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
            incomes.editIncome(1, DATE_FIELD, "31-02-2022");
            fail();
        } catch (Exception e) {
            assertEquals("Date must be valid and have format: \"DD-MM-YYYY\"", e.getMessage());
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
            incomes.editIncome(1, VALUE_FIELD, "200000");
            assertEquals(DESCRIPTION, incomes.get(0).getDescription());
            assertEquals(DATE, incomes.get(0).getDate());
            assertEquals(expectedOutputValue, incomes.get(0).getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    /**
     * Junit Test to test editIncome method with a value that is negative.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editNegativeValue_exceptionThrown() {
        try {
            incomes.editIncome(1, VALUE_FIELD, "-200000");
            fail();
        } catch (Exception e) {
            assertEquals("Income value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editIncome method with a value that has more than 2 decimal places.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editValueWithMoreThanTwoDecimalPlaces_exceptionThrown() {
        try {
            incomes.editIncome(1, VALUE_FIELD, "200000.1234");
            fail();
        } catch (Exception e) {
            assertEquals("Income value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editIncome method with a value that is not a double.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editValueNotDouble_exceptionThrown() {
        try {
            incomes.editIncome(1, VALUE_FIELD, "200000.1a");
            fail();
        } catch (Exception e) {
            assertEquals("Income value must be a valid positive double that is 2 d.p. or less", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editIncome method with a value that is more than 999999.99.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editValueMoreThan999999d99_exceptionThrown() {
        try {
            incomes.editIncome(1, VALUE_FIELD, "1000000.00");
            fail();
        } catch (Exception e) {
            assertEquals("Income value must be less than 1000000", e.getMessage());
        }
    }
    
    /**
     * Junit Test to test editIncome method with a value that is less than 0.01.
     * ChChing exception should be thrown.
     */
    @Test
    void editIncome_editValueLessThan0d01_exceptionThrown() {
        try {
            incomes.editIncome(1, VALUE_FIELD, "0.00");
            fail();
        } catch (Exception e) {
            assertEquals("Income value must be greater than 0", e.getMessage());
        }
    }
}
