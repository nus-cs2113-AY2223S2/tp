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
    public void showWelcome_atStartup_showWelcomeMessage() {
        Ui ui = new Ui();
        ui.showWelcome();
        assertEquals("____________________________________________________________" + "\r\n" +
                "Hello! Welcome to EveNtUS!" + "\r\n" +
                "____________________________________________________________\r\n", out.toString());
    }

    @Test
    public void showSuccessfulDeletionMessage_afterDeleteCommand_showDeletionMessage() {
        Ui ui = new Ui();
        ui.showSuccessfulDeletionMessage();
        assertEquals("Company information successfully deleted!",
                out.toString().trim());
    }

    @Test
    public void showSuccessfulPurgingMessage_afterPurgeCommand_showPurgeMessage() {
        Ui ui = new Ui();
        ui.showSuccessfulPurgingMessage();
        assertEquals("Data has been deleted successfully!",
                out.toString().trim());
    }

    @Test
    public void showSampleDataLoadedMessage_afterLoadingSampleData_showDataLoadedMessage() {
        Ui ui = new Ui();
        ui.showSampleDataLoadedMessage();
        assertEquals("Sample data has been loaded into the list!",
                out.toString().trim());
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
