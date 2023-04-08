package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;
import seedu.badmaths.InvalidFormatException;

import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoteParserTest {
    @Test
    public void testParseNoteString() throws InvalidFormatException {
        String noteString1 = "HIGH\tY\t1\tThis is a test note.";
        Note note1 = NoteParser.parseNoteString(noteString1);
        assertTrue(note1.getIsDone());
        assertEquals(note1.getIsDoneIcon(), "Y");
        assertEquals(note1.getReviewCount(), 1);
        assertEquals(note1.getPriority(), NotePriority.Priority.HIGH);
        assertEquals(note1.getText(), "This is a test note.");

        String noteString2 = "MEDIUM\tN\t2\tThis is another test note.";
        Note note2 = NoteParser.parseNoteString(noteString2);
        assertFalse(note2.getIsDone());
        assertEquals(note2.getIsDoneIcon(), "N");
        assertEquals(note2.getReviewCount(), 2);
        assertEquals(note2.getPriority(), NotePriority.Priority.MEDIUM);
        assertEquals(note2.getText(), "This is another test note.");
    }

}
