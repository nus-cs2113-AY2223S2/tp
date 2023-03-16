package seedu.badMaths;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpManualTest {
    @Test
    public void testReadHelpManual() throws IOException {
        // Call the readHelpManual() method
        HelpManual.readHelpManual();

        // Check that the content is not null
        assertNotNull(HelpManual.getContent());

        // Check if the output matches the expected output
        String expectedOutput = new String(Files.readAllBytes(Paths.get("docs/HelpManual.txt")));
        assertEquals(expectedOutput, HelpManual.getContent());
    }
}
