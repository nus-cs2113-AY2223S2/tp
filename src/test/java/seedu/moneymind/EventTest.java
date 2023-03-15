package seedu.moneymind;

import org.junit.jupiter.api.Test;
import seedu.moneymind.event.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    Event salad = new Event("salad", 100);
    Event pizza = new Event("pizza", 200);
    Event harryPotter = new Event("Harry Potter", 70, 80);

    @Test
    void getBudget_twoEvent_exptOutput() {
        assertEquals(100, salad.getBudget());
        assertEquals(100, salad.getBudget());
    }

    @Test
    void getExpense_twoEvent_exptOutput() {
        assertEquals(0, salad.getExpense());
        assertEquals(80, harryPotter.getExpense());
    }

    @Test
    void setBudget_oneBudget_exptBudgetSet() {
        salad.setBudget(200);
        assertEquals(200, salad.getBudget());
    }

    @Test
    void remainingBudget_twoEvent_successCalculation() {
        assertEquals(100, salad.remainingBudget());
        assertEquals(200, pizza.remainingBudget());
    }
}
