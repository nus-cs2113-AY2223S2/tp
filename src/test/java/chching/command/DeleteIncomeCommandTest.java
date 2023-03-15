package chching.command;

import chching.record.IncomeList;
import chching.record.Income;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteIncomeCommandTest {

    @Test
    void addDeleteIncome_positiveIntegerWithinSize_Success(){
        Income income0 = new Income("salary","1st apr 2023",5000);
        IncomeList incomes = new IncomeList();
        incomes.addIncome(income0);
        incomes.deleteIncome(0);
        assertEquals(0,incomes.size(),"Delete income working");

    }

    @Test
    void addDeleteIncome_positiveIntegerOutsideSize_Fail() {
        Income income0 = new Income("salary", "1st apr 2023", 5000);
        IncomeList incomes = new IncomeList();
        incomes.addIncome(income0);
        try {
            incomes.deleteIncome(3);
        } catch (Exception e) {
            assertEquals("The number is too big", e.getMessage(), "Delete income with integer outside size is not captured");
        }
    }

    void addDeleteIncome_NegativeInteger_Fail() {
        Income income0 = new Income("salary", "1st apr 2023", 5000);
        IncomeList incomes = new IncomeList();
        incomes.addIncome(income0);
        try {
            incomes.deleteIncome(-5);
        } catch (Exception e) {
            assertEquals("Negative index", e.getMessage(), "Delete income with negative integer is not captured");
        }
    }
}