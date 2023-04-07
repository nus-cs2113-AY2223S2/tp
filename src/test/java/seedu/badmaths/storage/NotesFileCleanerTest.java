package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NotesFileCleanerTest {

    @Test
    public void testClearFile() throws IOException{
        // set up test file and write some contents into it
        Path tempFile = Files.createTempFile("temp", ".txt");
        String fileContents = "This is a test file.";
        Files.writeString(tempFile, fileContents);

        // call clearFile method
        NotesFileCleaner.clearFile(tempFile.toString());

        // check that the file exists and is empty
        File file = new File(tempFile.toString());
        assertTrue(file.exists());
        assertTrue(file.isFile());
        assertFalse(file.isDirectory());
        assertEquals(file.length(), 0);

        // clean up
        Files.delete(tempFile);
        System.setIn(System.in);
        System.setOut(System.out);
    }

}
