package seedu.pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.pocketpal.entries.Category;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.ui.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test add command")
public class AddCommandTest {

    private final Entry expectedEntry = new Entry("Rice", 8.50, Category.FOOD);
    private final EntryLog testEntries = new EntryLog();
    private final UI ui = new UI();

    @Test
    @DisplayName("Test constructor for AddCommand")
    void testAddCommand(){
        try {
            AddCommand addCommand= new AddCommand("Rice", 8.50, "Food");
            assertEquals(addCommand.getEntryObj().getDescription(), expectedEntry.getDescription());
            assertEquals(addCommand.getEntryObj().getAmount(), expectedEntry.getAmount());
            assertEquals(addCommand.getEntryObj().getCategory(), expectedEntry.getCategory());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in AddCommand")
    void testExecute(){
        AddCommand testCommand = assertDoesNotThrow(() -> new AddCommand("Rice", 8.50, "Food"));
        testCommand.executor(testEntries, ui);
        assertTrue(testEntries.getEntriesList().contains(testCommand.getEntryObj()));
    }

}
