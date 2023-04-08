//@@author WilsonLee2000
package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class StoreTest {

    @Test
    void checkIsInvalidToDo() throws IOException {
        Path temporaryFile = Files.createTempFile("TempFile", ".txt");
        String contentsFile = "HIGH\tN\t0\tThis is a test \nMEDIUM\tY\t2\tThi is another test\n";
        Files.writeString(temporaryFile, contentsFile);
        String todo = "82738232";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        Store storeTest = new Store(notes, todo);
        boolean test = storeTest.isInvalidTodo(todo);
        assertEquals(false, test);
        Files.delete(temporaryFile);
    }

    @Test
    void storeValidInputCommand() throws IOException {
        Path temporaryFile = Files.createTempFile("TempFile", ".txt");
        String contentsFile = "HIGH\tN\t0\tThis is a test \nMEDIUM\tY\t2\tThi is another test\n";
        Files.writeString(temporaryFile, contentsFile);
        ArrayList<Note> notesArray = new ArrayList<>();
        String toDo = "index";
        NotesList notes = new NotesList(notesArray);
        Store storeTest = new Store(notes, toDo);
        storeTest.storeNotes();
        assertEquals(1, notes.getSize()); // (expected, actual)
        Files.delete(temporaryFile);
    }
}
