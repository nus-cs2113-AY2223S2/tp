package seedu.badMaths;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class HelpManualTest {
    @Test
    public void testReadHelpManual() throws IOException {
        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the readHelpManual() method
        HelpManual.readHelpManual();

        // Check if the output matches the expected output
        String expectedOutput = new String(Files.readAllBytes(Paths.get("docs/HelpManual.txt")));
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

}