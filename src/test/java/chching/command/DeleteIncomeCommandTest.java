package chching.command;

import chching.record.IncomeList;
import chching.record.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteIncomeCommandTest {
    private Income salary;
    private Income bonus;
    private IncomeList defaultIncomeList;
    
    @BeforeEach
    void setup() {
        salary = new Income("salary","1st apr 2023",5000);
        bonus = new Income("bonus", "5/2/23", 3000);
        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        incomeList.add(bonus);
        defaultIncomeList = new IncomeList(incomeList);
    }

    @Test
    void deleteIncome_positiveIntegerWithinSize_success(){
        int defaultIncomeListSize = defaultIncomeList.size();
        defaultIncomeList.deleteIncome(1);
        assertEquals(defaultIncomeListSize - 1, defaultIncomeList.size(),"Delete income working");
    }

    @Test
    void deleteIncome_positiveIntegerOutsideSize_fail() {
        String expectedOutput = "There is no income with this index";
        try {
            defaultIncomeList.deleteIncome(3);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with integer outside size is not captured");
        }
    }

    @Test
    void deleteIncome_negativeInteger_fail() {
        String expectedOutput = "There is no income with this index";
        try {
            defaultIncomeList.deleteIncome(-5);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with negative integer is not captured");
        }
    }
}
