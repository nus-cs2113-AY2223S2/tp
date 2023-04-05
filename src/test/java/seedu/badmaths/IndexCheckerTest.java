package seedu.badmaths;

import org.junit.jupiter.api.Test;
import seedu.badmaths.commands.IndexChecker;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexCheckerTest {
    @Test
    public void testIsInvalidIndex() {

        ArrayList<Note> notes = new ArrayList<>();
        NotesList notesList = new NotesList(notes);
        notesList.add("Note 1");
        notesList.add("Note 2");
        notesList.add("Note 3");

        assertTrue(IndexChecker.isInvalidIndex(-1, notesList));
        assertFalse(IndexChecker.isInvalidIndex(2, notesList));
        assertFalse(IndexChecker.isInvalidIndex(0, notesList));
        assertFalse(IndexChecker.isInvalidIndex(1, notesList));
    }
}
