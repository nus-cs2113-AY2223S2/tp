package seedu.badmaths.help;

import org.junit.jupiter.api.Test;
import seedu.badmaths.HelpManual;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.Handler;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpManualTest {
    @Test
    public void testReadHelpManual() throws IOException {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Logger logger = Logger.getLogger(HelpManual.class.getName());
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        Handler handler = new java.util.logging.StreamHandler(outContent, new java.util.logging.SimpleFormatter());
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        // Call the readHelpManual() method
        HelpManual.readHelpManual();

        // Check that the content is not null
        assertNotNull(outContent.toString());

        // Check if the output matches the expected output
        String expectedOutput = new String(Files.readAllBytes(Paths.get("src/main/resources/HelpManual.txt")));
        assertEquals(expectedOutput, outContent.toString());
    }
}
