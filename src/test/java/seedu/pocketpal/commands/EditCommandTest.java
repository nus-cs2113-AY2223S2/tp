package seedu.pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.pocketpal.frontend.commands.EditCommand;
import seedu.pocketpal.frontend.constants.MessageConstants;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.data.entrylog.EntryLog;
import seedu.pocketpal.frontend.ui.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test edit command")
public class EditCommandTest {
    private final EditCommand expectedEditCommand = new EditCommand("1", "Lunch", "Food", "5");
    private final Entry originalEntry = new Entry("Dinner", 7.50, Category.FOOD);
    private final EntryLog originalEntries = new EntryLog();
    private final UI ui = new UI();
    private final String[] proposedChanges = {"1", "Lunch", "", ""};

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
        originalEntries.addEntry(originalEntry);
        assertDoesNotThrow(() -> editCommand.execute(ui, null), MessageConstants.MESSAGE_MISSING_ARGS_EDIT);
        Entry changedEntry = originalEntries.getEntriesList().get(0);
        assertEquals(changedEntry.getDescription(), "Lunch");
        assertEquals(changedEntry.getCategoryString(), "Food");
        assertEquals(changedEntry.getAmount(), 7.50);
    }


}
