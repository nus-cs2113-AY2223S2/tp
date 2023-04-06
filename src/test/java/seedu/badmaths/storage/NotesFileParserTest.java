package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;
import seedu.badmaths.storage.NotesFileParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotesFileParserTest {

    @Test
    public void testLoadFile() {

        ArrayList<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(new Note("Note 1", NotePriority.Priority.HIGH));
        expectedNotes.add(new Note("Note 2", NotePriority.Priority.LOW));
        expectedNotes.add(new Note("Note 3", NotePriority.Priority.MEDIUM));
        expectedNotes.get(0).markAsDone();
        expectedNotes.get(0).setReviewCount(2);
        expectedNotes.get(1).setReviewCount(1);
        expectedNotes.get(2).setReviewCount(0);
        expectedNotes.get(2).markAsDone();

        String filePath = "src/test/resources/TestNotes.txt";

        ArrayList<Note> actualNotes = NotesFileParser.loadFile(filePath);
        assertEquals(expectedNotes.size(), actualNotes.size());

        for (int i = 0; i < expectedNotes.size(); i++) {
            Note expectedNote = expectedNotes.get(i);
            Note actualNote = actualNotes.get(i);
            assertEquals(expectedNote.getText(), actualNote.getText());
            assertEquals(expectedNote.getPriority(), actualNote.getPriority());
            assertEquals(expectedNote.getIsDone(), actualNote.getIsDone());
            assertEquals(expectedNote.getReviewCount(), actualNote.getReviewCount());
        }
    }
}
