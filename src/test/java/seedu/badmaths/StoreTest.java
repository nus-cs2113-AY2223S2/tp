//@@author WilsonLee2000
package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;
// import java.nio.file.Path;
// import java.nio.file.Files;

public class StoreTest {
    /*
    Path tempFile = Files.createTempFile("temp", ".txt");
    String fileContents = "HIGH\tN\t0\tThis is a test note\nMEDIUM\tY\t2\tThi is another test note\n";
    Files.writeString(tempFile, fileContents);
    Files.delete(tempFile);
    */

    @Test
    void checkIsInvalidToDo() {
        String todo = "82738232";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        Store storeTest = new Store(notes, todo);
        boolean test = storeTest.isInvalidTodo(todo);
        assertEquals(false, test);
    }

    @Test
    void storeValidInputCommand() {
        ArrayList<Note> notesArray = new ArrayList<>();
        String toDo = "index";
        NotesList notes = new NotesList(notesArray);
        Store storeTest = new Store(notes, toDo);
        storeTest.storeNotes();
        assertEquals(1, notes.getSize()); // (expected, actual)
    }
}
