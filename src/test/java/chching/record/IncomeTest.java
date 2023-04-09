package chching.record;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit Test for Income
 */
class IncomeTest {
    static final String DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 1000000;

    /**
     * Junit Test to get income description
     */
    @Test
    void getIncomeDescription_expected() {
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("salary", income0.getDescription());
    }

    /**
     * Junit Test to get income date
     */
    @Test
    void getIncomeDate_expected() {
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("01-Apr-2023", income0.getDateString());
    }

    /**
     * Junit Test to get income value
     */
    @Test
    void getIncomeValue_expected() {
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals((float) 1000000, income0.getValue());
    }

    /**
     * Junit Test to get income entry in String
     */
    @Test
    void getValue_expected() {
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        String expected = "Description - salary | Date - 01-Apr-2023 | Value - 1000000.00";
        assertEquals(expected, income0.toString());
    }

}
