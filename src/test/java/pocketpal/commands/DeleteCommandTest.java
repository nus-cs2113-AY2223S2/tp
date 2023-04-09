package pocketpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.backend.BackendTestUtil;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.frontend.commands.DeleteCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidEntryIdException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test delete command")
public class DeleteCommandTest extends BackendTestUtil {
    private final Entry testEntry1 = new Entry("Chicken Rice", 8.50, Category.FOOD);
    private final Entry testEntry2 = new Entry("Duck Rice", 8.50, Category.FOOD);

    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
        addEntry(testEntry1);
        addEntry(testEntry2);
    }

    @Test
    @DisplayName("Test constructor for DeleteCommand")
    void testDeleteCommand() {
        try {
            DeleteCommand testCommand = new DeleteCommand(new Integer[]{1});
            assertEquals(1, testCommand.getEntryId(0));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in DeleteCommand")
    void testExecute() {
        try {
            DeleteCommand testCommand = assertDoesNotThrow(() -> new DeleteCommand(new Integer[]{2}));
            testCommand.execute(TEST_UI, TEST_BACKEND);
            assertNull(getEntryById(2));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test multiple deletion of ")
    void testDeleteMultiple() {
        try {
            DeleteCommand testCommand = assertDoesNotThrow(() -> new DeleteCommand(new Integer[]{1, 2}));
            testCommand.execute(TEST_UI, TEST_BACKEND);
            assertNull(getEntryById(1));
            assertNull(getEntryById(2));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test multiple deletion with invalid IDs ")
    void testDeleteMultiple_withNegativeID_throwsException() {
        DeleteCommand testCommand = assertDoesNotThrow(() -> new DeleteCommand(new Integer[]{-1}));
        Exception exception = assertThrows(InvalidEntryIdException.class, () -> {
            testCommand.execute(TEST_UI, TEST_BACKEND);
        });
        String expectedMessage = MessageConstants.MESSAGE_NON_EXISTENT_ID + "-1" + System.lineSeparator()
                + MessageConstants.MESSAGE_ID_NOT_FOUND + "2.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
