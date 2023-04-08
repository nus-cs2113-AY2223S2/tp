//@@author WilsonLee2000

package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

public class CommandHistoryTest {

    @Test
    void checkStoreCommand() {
        ArrayList<String> historyCommand = new ArrayList<>();
        String inputCommand = "Hello";
        CommandHistory commandHistoryTest = new CommandHistory(historyCommand);
        commandHistoryTest.storeCommand(inputCommand);
        assertEquals(1, historyCommand.size());
    }

    @Test
    void checkDisplayHistory() {
        String correctDisplayOutput = "";
        ByteArrayOutputStream historyDisplayed = new ByteArrayOutputStream();
        ArrayList<String> historyCommand = new ArrayList<>();
        CommandHistory commandHistoryTest = new CommandHistory(historyCommand);
        commandHistoryTest.displayHistory();
        System.setOut(new PrintStream(historyDisplayed));
        String stringHistoryDisplayed = historyDisplayed.toString();
        assertEquals(correctDisplayOutput, stringHistoryDisplayed); // expected, actual
    }
}
