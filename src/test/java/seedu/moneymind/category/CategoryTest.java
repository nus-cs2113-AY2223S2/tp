package seedu.moneymind.category;

import org.junit.jupiter.api.Test;

import seedu.moneymind.event.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Event event1 = new Event("McDonalds", 10);
    Event event2 = new Event("KFC", 20);
    Category food = new Category("food");
    Category transport = new Category("transport", 100);

    /**
     * Sets up the test data.
     */
    private void setup() {
        food.addEvent(event1);
        food.addEvent(event2);
    }

    @Test
    void getName() {
        setup();
        assertEquals("food", food.getName());
    }

    @Test
    void getBudget() {
        setup();
        assertEquals(0, food.getBudget());
        assertEquals(100, transport.getBudget());
    }

    @Test
    void getTotalExpense() {
        setup();
        assertEquals(30, food.getTotalOneTimeExpense());
    }
}
