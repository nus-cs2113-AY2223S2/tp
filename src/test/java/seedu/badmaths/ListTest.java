//@@author WilsonLee2000
package seedu.badmaths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;

public class ListTest {

    @Test
    void checkIsInvalidIndex() {
        int index = 1;
        String todo = "1";
        String itemOne = "One";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        notes.add(itemOne);
        List listTest = new List(notes, todo);
        boolean test = listTest.isInvalidIndex(index, notes);
        assertEquals(true, test);
    }

    @Test
    void checkIsAnInt() {
        String todo = "1";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        List listTest = new List(notes, todo);
        boolean test = listTest.isAnInt(todo);
        assertEquals(true, test);
    }

    @Test
    void checkIsInvalidToDo() {
        String todo = "82738232";
        ArrayList<Note> notesArray = new ArrayList<>();
        NotesList notes = new NotesList(notesArray);
        List listTest = new List(notes, todo);
        boolean test = listTest.isInvalidTodo(todo);
        assertEquals(false, test);
    }

    @Test
    void testListNotes() {
        ByteArrayOutputStream listDisplayed = new ByteArrayOutputStream();
        ArrayList<Note> notesArray = new ArrayList<>();
        String toDo = "index";
        NotesList notes = new NotesList(notesArray);
        List listTest = new List(notes, toDo);
        listTest.listNotes();
        String correctListOutput = "";
        System.setOut(new PrintStream(listDisplayed));
        String stringListDisplayed = listDisplayed.toString();
        assertEquals(correctListOutput, stringListDisplayed); // (expected, actual)
    }
}
