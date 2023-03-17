package seedu.pocketpal.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.pocketpal.frontend.constants.MessageConstants;
import seedu.pocketpal.frontend.constants.UIConstants;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.frontend.ui.UI;
import seedu.pocketpal.frontend.util.UIUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Test UI")
public class UITest {
    private final UI ui = new UI();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void init() {
        // setup streams
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void teardown() {
        // restore streams
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Nested
    @DisplayName("Test basic output methods")
    class BasicOutputTest {
        @Test
        void testPrint() {
            final String testMessage = "Lorem ipsum 123";
            ui.print(testMessage);
            assertEquals(testMessage, outContent.toString());
        }

        @Test
        void testPrintLine() {
            ui.print(UIConstants.LINE);
            assertEquals(UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintAwaitUserInput() {
            ui.printAwaitUserInput();
            assertEquals(UIConstants.USER_INPUT_PROMPT, outContent.toString());
        }

        @Test
        void testPrintExit() {
            ui.printExit();
            assertEquals(MessageConstants.MESSAGE_EXIT + UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintHelp() {
            ui.printHelp();
            assertEquals(MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_ADD_COMMAND
                    + MessageConstants.MESSAGE_DELETE_COMMAND + MessageConstants.MESSAGE_EDIT_COMMAND
                    + MessageConstants.MESSAGE_VIEW_COMMAND + MessageConstants.MESSAGE_HELP_COMMAND
                    + MessageConstants.MESSAGE_BYE_COMMAND + UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintWelcome() {
            ui.printWelcome();
            assertEquals(MessageConstants.MESSAGE_WELCOME + UIConstants.LINE, outContent.toString());
        }
    }

    @Nested
    @DisplayName("Test methods involving entries")
    class EntryOutputTest {
        private final Entry testEntry = new Entry("Mango juice", 2, Category.FOOD);

        @Test
        void testFloatingPointPadding() {
            assertEquals(UIUtil.formatPrice(2), "2.00");
            assertEquals(UIUtil.formatPrice(0.123), "0.12");
            assertEquals(UIUtil.formatPrice(3.1), "3.10");
        }

        @Test
        void testAddExpenditure() {
            ui.printExpenditureAdded(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString());
            assertEquals(MessageConstants.MESSAGE_EXPENDITURE_ADDED
                            + UIUtil.formatExpenditure(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString())
                            + UIConstants.LINE,
                    outContent.toString());
        }

        @Test
        void testDeleteExpenditure() {
            ui.printExpenditureDeleted(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString());
            assertEquals(MessageConstants.MESSAGE_EXPENDITURE_DELETED
                            + UIUtil.formatExpenditure(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString())
                            + UIConstants.LINE,
                    outContent.toString());
        }
    }

}
