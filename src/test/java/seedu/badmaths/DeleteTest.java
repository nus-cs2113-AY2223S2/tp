package seedu.badmaths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;
import java.util.ArrayList;

public class DeleteTest {

    @Test
    void checkIsInvalidIndex() {
        int index = 1;
        String todo = "1";
        String itemOne = "One";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        notes.add(itemOne);
        Delete deleteTest = new Delete(notes, todo);
        boolean test = deleteTest.isInvalidIndex(index, notes);
        assertEquals(true, test);
    }

    @Test
    void checkIsAnInt() {
        String todo = "1";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        Delete deleteTest = new Delete(notes, todo);
        boolean test = deleteTest.isAnInt(todo);
        assertEquals(true, test);
    }

    @Test
    void checkDeleteNotes() {
        String todo = "1";
        String itemOne = "One";
        String itemTwo = "Two";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        notes.add(itemOne);
        notes.add(itemTwo);
        Delete deleteTest = new Delete(notes, todo);
        deleteTest.deleteNotes();
        assertEquals(1, notes.getSize());
    }
}
