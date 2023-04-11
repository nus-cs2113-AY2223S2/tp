package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test
    public void showLine_onEachMethod_displayLine() {
        Ui ui = new Ui();
        ui.showLine();
        assertEquals("____________________________________________________________",
                out.toString().trim());
    }

    @Test
    public void showWelcome_atStartup_displayWelcomeMessage() {
        Ui ui = new Ui();
        ui.showWelcome();
        String expected = ("____________________________________________________________\nHello! Welcome to EveNtUS!" +
                "\nType <help> to get started" + "\n" + "____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected, out.toString().trim());
    }

    @Test
    public void showExitMessage_atExit_displayExitMessage() {
        Ui ui = new Ui();
        ui.showExitMessage();
        String expected = ("____________________________________________________________\nBye!" +
                "\n" + "____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected, out.toString().trim());
    }

    @Test
    public void showSuccessfulDeletionMessage_afterDeleteCommand_showDeletionMessage() {
        Ui ui = new Ui();
        ui.showSuccessfulDeletionMessage();
        String expected = ("____________________________________________________________\n" +
                "Company information successfully deleted!" +
                "\n" + "____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected,
                out.toString().trim());
    }

    @Test
    public void showSuccessfulPurgingMessage_afterPurgeCommand_showPurgeMessage() {
        Ui ui = new Ui();
        ui.showSuccessfulPurgingMessage();
        String expected = ("____________________________________________________________\n" +
                "Data has been deleted successfully!" +
                "\n" + "____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected,
                out.toString().trim());
    }

    @Test
    public void showSampleDataLoadedMessage_afterLoadingSampleData_showDataLoadedMessage() {
        Ui ui = new Ui();
        ui.showSampleDataLoadedMessage();
        String expected = ("____________________________________________________________\n" +
                "Sample data has been loaded into the list!" +
                "\n" + "____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected,
                out.toString().trim());
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
