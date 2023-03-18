package seedu.pocketpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.frontend.commands.DeleteCommand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test delete command")
public class DeleteCommandTest extends CommandTest {
    private final Entry testEntry = new Entry("Rice", 8.50, Category.FOOD);

    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
        addEntry(testEntry);
    }

    @Test
    @DisplayName("Test constructor for DeleteCommand")
    void testDeleteCommand() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(1);
            assertEquals(1, deleteCommand.getEntryId());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in DeleteCommand")
    void testExecute() {
        try {
            DeleteCommand testCommand = assertDoesNotThrow(() -> new DeleteCommand(1));
            testCommand.execute(TEST_UI, TEST_BACKEND);
            assertNull(getEntryById(1));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }
}
