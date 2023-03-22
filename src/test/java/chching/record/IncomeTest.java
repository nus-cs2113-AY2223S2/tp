package chching.record;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IncomeTest {
    static final String DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 1000000;

    @Test
    void getIncomeDescription_expected() {
        String test = "salary";
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("salary", income0.getDescription());
    }

    @Test
    void getIncomeDate_expected() {
        String test = "01-Apr-2023";
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("01-Apr-2023", income0.getDateString());
    }

    @Test
    void getIncomeValue_expected() {
        float test = (float) 1000000;
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals((float) 1000000, income0.getValue());
    }

    @Test
    void getValue_expected() {
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        String expected = "Description - salary | Date - 01-Apr-2023 | Value - 1000000.00";
        assertEquals(expected, income0.toString());
    }

}
