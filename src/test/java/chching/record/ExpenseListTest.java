package chching.record;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseListTest {
    static final int EXPECTED_SIZE = 1;
    static final String CATEGORY = "entertainment";
    static final String DESCRIPTION = "beach party";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("23-05-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 50;

    @Test
    void getExpenseCount_one_expectOne() {

        Expense expenseOne = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        ExpenseList list = new ExpenseList();
        list.addRecord(expenseOne);
        assertEquals(EXPECTED_SIZE, list.getRecordCount());
    }
}
