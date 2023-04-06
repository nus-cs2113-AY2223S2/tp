package utils;

import data.Expense;
import data.Time;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MonthFilterTest {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Test
    void convertMonthName_successful() {
        MonthFilter monthFilterOneDigit = new MonthFilter(new ArrayList<>(), "March");
        assertEquals(monthFilterOneDigit.convertMonthName(), "03");
        MonthFilter monthFilterTwoDigit = new MonthFilter(new ArrayList<>(), "November");
        assertEquals(monthFilterTwoDigit.convertMonthName(), "11");
    }

    @Test
    void covertMonthName_unsuccessful() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> {
            MonthFilter negativeMonthFilter = new MonthFilter(new ArrayList<>(), "gibberish");
            negativeMonthFilter.convertMonthName();
        });
    }

    @Test
    void getMonthFromExpenseTime_successful() {
        MonthFilter monthFilter = new MonthFilter(new ArrayList<>(), "June");
        assertEquals(monthFilter.getMonthFromExpenseTime("05/06/2021"), "06");
    }

    @Test
    void getMonthlyExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        ArrayList<Expense> testExpenses = new ArrayList<>();
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        MonthFilter monthFilter = new MonthFilter(testExpenses, "March");
        assertEquals(monthFilter.getMonthlyExpenses(), expenses);

        expenses.clear();
        testExpenses.clear();
    }
}
