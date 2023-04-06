package utils;

import data.Expense;
import data.Time;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YearFilterTest {

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    void getYearlyExpenses_successful() {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        expenses.add(new Expense(new BigDecimal("10"),
                new Time(LocalDate.parse("02-01-2012", formatter)),
                "travel", "USD", new BigDecimal(1)));
        ArrayList<Expense> testExpenses = new ArrayList<>();
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("10"),
                new Time(LocalDate.parse("02-01-2012", formatter)),
                "travel", "USD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("10"),
                new Time(LocalDate.parse("02-01-2015", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("10"),
                new Time(LocalDate.parse("02-01-2016", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        YearFilter yearFilter = new YearFilter(testExpenses, "2012");
        assertEquals(yearFilter.getYearlyExpenses(), expenses);

        expenses.clear();
        testExpenses.clear();
    }
}
