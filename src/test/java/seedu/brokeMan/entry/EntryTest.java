package seedu.brokeMan.entry;

import org.junit.jupiter.api.Test;
import seedu.brokeMan.entry.expense.Expense;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryTest {
    @Test
    public void testGetInfo() {
        Entry entry = new Expense(10, "test", LocalDateTime.now(), Category.FOOD);
        assertTrue(entry instanceof Entry);
        entry.getTime();
        entry.getInfo();
    }
}
