package seedu.brokeMan.entry;

import org.junit.jupiter.api.Test;
import seedu.brokeMan.entry.expense.Expense;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryTest {
    @Test
    public void testGetters() {
        Entry entry = new Expense(10, "test", LocalDateTime.now(), Category.FOOD);
        assertTrue(entry instanceof Entry);
        entry.getTime();
        entry.getInfo();
    }

    @Test
    public void testComparator() {
        Entry entry1 = new Expense(10, "test", LocalDateTime.now(), Category.FOOD);
        Entry entry2 = new Expense(11, "test", LocalDateTime.of(2023, 10,
                10, 10, 10), Category.FOOD);
        new EntryDateComparator().compare(entry1, entry2);
    }
}
