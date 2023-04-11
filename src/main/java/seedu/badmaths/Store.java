//@@author WilsonLee2000

/**
 * Takes in a String toDo and stores the toDo String provided by users into the notes list.
 *
 * @param notes
 * @param toDo
 */

package seedu.badmaths;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileWriter;
import seedu.badmaths.ui.Ui;

public class Store {

    private static final String filePath = "data/notes.txt";
    public NotesList notes;
    protected String toDo;

    public Store(NotesList notes, String toDo) {
        this.toDo = toDo;
        this.notes = notes;
    }

    public static boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }

    public void storeNotes() {
        try {
            if (isInvalidTodo(toDo)) {
                throw new IllegalTodoException();
            }
            if (toDo.equals("null")) {
                throw new IllegalTodoException();
            }
            notes.add(toDo);
            Ui.printAddNote(toDo, notes.getSize());
            NotesFileWriter.saveFile(filePath, notes.getAll());
        } catch (IllegalTodoException exception) {
            Ui.printIncorrectFormatEntered();
        }
    }
}

