package seedu.badMaths;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpManualTest {
    @Test
    public void testReadHelpManual() throws IOException {
        // Call the readHelpManual() method
        HelpManual.readHelpManual();

        // Check if the output matches the expected output
        String expectedOutput = new String(Files.readAllBytes(Paths.get("docs/HelpManual.txt")));
        assertEquals(expectedOutput, HelpManual.getContent());
    }

}
