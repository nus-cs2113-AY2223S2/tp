package seedu.brokeMan.entry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.brokeMan.entry.income.Income;
import seedu.brokeMan.entry.income.IncomeList;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeListTest {
    private static Income income1;
    private static Income income2;

    @BeforeAll
    public static void setUp() {
        income1 = new Income(2000.0, "Salary", LocalDateTime.of(2023, Month.APRIL,
                1, 10, 0), Category.SALARY);
        income2 = new Income(500.0, "Bonus", LocalDateTime.of(2023, Month.APRIL,
                15, 15, 30), Category.SALARY);
        IncomeList.addIncome(income1);
        IncomeList.addIncome(income2);
    }

    @Test
    public void testAddIncome_validIncome_success() {
        Income income3 = new Income(100.0, "Allowance", LocalDateTime.of(2023, Month.APRIL,
                25, 12, 0), Category.OTHERS);
        IncomeList.addIncome(income3);
        LinkedList<Income> expectedIncomeList = new LinkedList<>();
        expectedIncomeList.add(income1);
        expectedIncomeList.add(income2);
        expectedIncomeList.add(income3);
        assertEquals(expectedIncomeList, IncomeList.incomeList);
    }

    @Test
    public void testDeleteIncome_validIncome_success() {
        IncomeList.deleteIncome(1);
        IncomeList.deleteIncome(1);
        IncomeList.deleteIncome(1);
        LinkedList<Income> expectedIncomeList = new LinkedList<>();
        assertEquals(expectedIncomeList, IncomeList.incomeList);
    }
}
