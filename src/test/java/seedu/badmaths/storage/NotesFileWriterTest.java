package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotesFileWriterTest {
    @Test
    public void testSaveFile() throws IOException{

        Path tempFile = Files.createTempFile("temp", ".txt");
        String fileContents = "This is a test file.";
        Files.writeString(tempFile, fileContents);

        ArrayList<Note> testNotes = new ArrayList<>();

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
        NotesFileWriter.saveFile(tempFile.toString(), testNotes);

        // check that the file exists
        File file = new File(tempFile.toString());
        assertTrue(file.exists());

        // check that the actual file content matches the expected string
        String expectedContent = "HIGH\tY\t1\tNote 1\n" +
                "MEDIUM\tN\t0\tNote 2\n" +
                "LOW\tY\t2\tNote 3\n";

        expectedContent = expectedContent.replace("\r\n", "\n").replace("\r", "\n");

        String actualContent = "";
        actualContent = new String(Files.readAllBytes(file.toPath()));
        actualContent = actualContent.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(expectedContent, actualContent);

        // clean up
        Files.delete(tempFile);
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
