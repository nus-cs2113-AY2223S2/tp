package seedu.badmaths;

import org.junit.After;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotesFileWriterTest {

    private static final String NotesFileWriterTestPath = "src/test/java/seedu/badmaths/NotesFileWriterTestFile.txt";
    private ArrayList<Note> testNotes = new ArrayList<>();

    @Test
    public void testSaveFile() {

        Note note1 = new Note("Note 1", NotePriority.Priority.HIGH);
        note1.markAsDone();
        note1.incrementReviewCount();

        Note note2 = new Note("Note 2", NotePriority.Priority.MEDIUM);

        Note note3 = new Note("Note 3", NotePriority.Priority.LOW);
        note3.markAsDone();
        note3.incrementReviewCount();
        note3.incrementReviewCount();

        testNotes.add(note1);
        testNotes.add(note2);
        testNotes.add(note3);

        // save the test notes to the test file
        NotesFileWriter.saveFile(NotesFileWriterTestPath, testNotes);

        // check that the file exists
        File file = new File(NotesFileWriterTestPath);
        assertTrue(file.exists());

        // check that the actual file content matches the expected string
        String expectedContent = "HIGH\tY\t1\tNote 1\n" +
                "MEDIUM\tN\t0\tNote 2\n" +
                "LOW\tY\t2\tNote 3\n";

        expectedContent = expectedContent.replace("\r\n", "\n").replace("\r", "\n");

        String actualContent = "";
        try {
            actualContent = new String(Files.readAllBytes(file.toPath()));
            actualContent = actualContent.replace("\r\n", "\n").replace("\r", "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expectedContent, actualContent);
    }

    @After
    public void tearDown() throws IOException {
        FileWriter writer = new FileWriter(NotesFileWriterTestPath);
        writer.write("");
        writer.close();
    }
}
