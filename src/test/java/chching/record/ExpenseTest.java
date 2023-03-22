package chching.record;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {
    static final String CATEGORY = "transport";
    static final String DESCRIPTION = "public transport";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 1.50;
    @Test
    void getExpenseDescription_expected() {
        String test = "public transport";
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals("public transport", exp.getDescription());
    }

    @Test
    void getExpenseDate_expected() {
        String test = "01-Apr-2023";
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals("01-Apr-2023", exp.getDateString());
    }

    @Test
    void getExpenseValue_expected() {
        float test = (float) 1.50;
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals((float) 1.50, exp.getValue());
    }

    @Test
    void getValue_expected() {
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        String expected = "Category - transport | Description - public transport | Date - 01-Apr-2023 | Value - 1.50";
        assertEquals(expected, exp.toString());
    }
}

