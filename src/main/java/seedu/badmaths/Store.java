//@@author WilsonLee2000

package seedu.badmaths;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileWriter;
import seedu.badmaths.ui.Ui;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.io.IOException;
import java.io.File;

public class Store {

    protected static String logFilePath = "Store";
    private static Logger log = Logger.getLogger("Store");
    private static final String filePath = "data/notes.txt";
    public NotesList notes;
    protected String toDo;

    public Store(NotesList notes, String toDo) {
        this.toDo = toDo;
        this.notes = notes;
    }

    public static void implementLogger() {
        LogManager.getLogManager().reset();
        log.setLevel(Level.INFO);
        try {
            if (!new File(logFilePath).exists()) {
                new File(logFilePath).createNewFile();
            }
            throw new IOException();
        } catch (IOException ioException) {
            log.log(Level.SEVERE, "File logger is not working", ioException);
        }
    }

    public static boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }

    public void storeNotes() {
        implementLogger();
        try {
            log.log(Level.INFO, "Storing in inputs by users");
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
            log.severe("Incorrect Format Entered");
        }
    }
}

