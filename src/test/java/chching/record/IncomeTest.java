package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IncomeTest {
    static final String DESCRIPTION = "salary";
    static final String DATE = "1st apr 2023";
    static final float INCOME_VALUE = (float) 1000000;

    @Test
    void getIncomeDescription_expected() {
        String test = "salary";
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("salary", income0.getDescription());
    }

    @Test
    void getIncomeDate_expected() {
        String test = "1st apr 2023";
        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        assertEquals("1st apr 2023", income0.getDate());
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
        String expected = "Description - salary | Date - 1st apr 2023 | Value - 1000000.00";
        assertEquals(expected, income0.toString());
    }

}
