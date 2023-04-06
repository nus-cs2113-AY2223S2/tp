package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

public class CommandHistoryTest {
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
