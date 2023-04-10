// @@author leonghuenweng
package pocketpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.backend.BackendTestUtil;
import pocketpal.frontend.commands.EditCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test edit command")
public class EditCommandTest extends BackendTestUtil {
    private final EditCommand expectedEditCommand = new EditCommand("1", "Lunch", "Food", "5");
    private final Entry originalEntry = new Entry("Dinner", 7.50, Category.FOOD);
    private final String[] proposedChanges = {"1", "Lunch", null, null};

    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
    }

    @Test
    @DisplayName("Test constructor for EditCommand")
    void testEditCommand() {
        try {
            EditCommand editCommand = new EditCommand("1", "Lunch", "Food", "5");
            assertEquals(editCommand.getAttributes()[0], expectedEditCommand.getAttributes()[0]);
            assertEquals(editCommand.getAttributes()[1], expectedEditCommand.getAttributes()[1]);
            assertEquals(editCommand.getAttributes()[2], expectedEditCommand.getAttributes()[2]);
            assertEquals(editCommand.getAttributes()[3], expectedEditCommand.getAttributes()[3]);
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in editCommand")
    void testExecuteMethod() {
        EditCommand editCommand = new EditCommand(proposedChanges[0], proposedChanges[1], proposedChanges[2]
                , proposedChanges[3]);
        addEntry(originalEntry);
        assertDoesNotThrow(() -> editCommand.execute(TEST_UI, TEST_BACKEND),
                MessageConstants.MESSAGE_MISSING_ID_EDIT);
        Entry changedEntry = getEntryById(1);
        assertEquals(changedEntry.getDescription(), "Lunch");
        assertEquals(changedEntry.getCategoryString(), "Food");
        assertEquals(changedEntry.getAmount(), 7.50);
    }


}
