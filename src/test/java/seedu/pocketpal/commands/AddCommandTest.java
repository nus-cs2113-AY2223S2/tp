package seedu.pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.pocketpal.backend.BackendTestUtil;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.frontend.commands.AddCommand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test add command")
public class AddCommandTest extends BackendTestUtil {

    private final Entry expectedEntry = new Entry("Rice", 8.50, Category.FOOD);

    @Test
    @DisplayName("Test constructor for AddCommand")
    void testAddCommand() {
        try {
            AddCommand addCommand = new AddCommand("Rice", 8.50, "Food");
            assertEquals(addCommand.getEntryObj().getDescription(), expectedEntry.getDescription());
            assertEquals(addCommand.getEntryObj().getAmount(), expectedEntry.getAmount());
            assertEquals(addCommand.getEntryObj().getCategory(), expectedEntry.getCategory());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in AddCommand")
    void testExecute() {
        Entry testEntry = new Entry("Rice", 8.50, Category.FOOD);
        AddCommand testCommand = assertDoesNotThrow(() -> new AddCommand(testEntry));
        testCommand.execute(TEST_UI, TEST_BACKEND);
        Entry expectedEntry = getEntryById(getNumEntries());
        assertTrue(isSameEntry(expectedEntry, testEntry));
    }
}
