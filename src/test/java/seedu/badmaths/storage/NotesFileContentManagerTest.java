package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;
import seedu.badmaths.storage.NotesFileContentManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotesFileContentManagerTest {

    @Test
    public void testFileContent() {

        //create three notes
        Note note1 = new Note("Note 1", NotePriority.Priority.HIGH);
        note1.markAsDone();
        note1.incrementReviewCount();

        Note note2 = new Note("Note 2", NotePriority.Priority.MEDIUM);

        Note note3 = new Note("Note 3", NotePriority.Priority.LOW);
        note3.markAsDone();
        note3.incrementReviewCount();
        note3.incrementReviewCount();

        // add notes to an ArrayList
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);

        // call the fileContent method and check the result
        String expectedContent = "HIGH\tY\t1\tNote 1\n" +
                "MEDIUM\tN\t0\tNote 2\n" +
                "LOW\tY\t2\tNote 3\n";
        expectedContent = expectedContent.replace("\r\n", "\n").replace("\r", "\n");

        String actualContent = NotesFileContentManager.fileContent(notes);
        actualContent = actualContent.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(expectedContent, actualContent);
    }
}
