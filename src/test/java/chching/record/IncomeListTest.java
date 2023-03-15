package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeListTest {
    @Test
    void getExpenseCount_one_expectOne() {

        Income income0 = new Income("salary", "1st apr 2023", 1000000);
        IncomeList list = new IncomeList();
        list.addRecord(income0);
        assertEquals(1, list.getRecordCount());
    }

}
