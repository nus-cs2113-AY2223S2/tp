package seedu.moneymind.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private static final String DD_MM_YYYY = "dd/MM/yyyy HH:mm";
    Event salad = new Event("salad", 100);
    Event pizza = new Event("pizza", 200, "10/10/2010");
    Event harryPotter = new Event("Harry Potter", 70);
    Event lordOfTheRings = new Event("Lord of the Rings", 90);

    @Test
    void getDescription() {
        assertEquals("10/10/2010", pizza.getTime());
    }

    @Test
    void getExpense() {
        assertEquals(100, salad.getExpense());
        assertEquals(200, pizza.getExpense());
        assertEquals(70, harryPotter.getExpense());
        assertEquals(90, lordOfTheRings.getExpense());
    }

    @Test
    void setExpense() {
        harryPotter.setExpense(100);
        assertEquals(100, harryPotter.getExpense());
        lordOfTheRings.setExpense(200);
        lordOfTheRings.setExpense(300);
        assertEquals(300, lordOfTheRings.getExpense());
    }

    @Test
    void getTime() {
        assertEquals("10/10/2010", pizza.getTime());
    }

    @Test
    void testToString() {
        LocalDateTime myDateObject = LocalDateTime.now();
        DateTimeFormatter myFormatObject = DateTimeFormatter.ofPattern(DD_MM_YYYY);
        String time = myDateObject.format(myFormatObject);
        assertEquals("salad (expense: 100)" + " (time added: " + time + ")", salad.toString());
        assertEquals("pizza (expense: 200) (start time: 10/10/2010)", pizza.toString());
    }
}
