package seedu.badmaths;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NotesFileCleanerTest {

    // create file path for test file
    private static final String filePath = "src/test/java/seedu/badmaths/NotesFileCleanerTestFile.txt";

    // set up test file and write some contents into it
    @Before
    public void setUp() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("This is a test file.");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClearFile(){

        // call clearFile method
        NotesFileCleaner.clearFile(filePath);

        // check that the file exists and is empty
        File file = new File(filePath);
        assertTrue(file.exists());
        assertTrue(file.isFile());
        assertFalse(file.isDirectory());
        assertEquals(file.length(), 0);
    }

    @After
    public void tearDown() {
        // delete the test file
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
