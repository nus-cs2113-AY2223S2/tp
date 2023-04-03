package seedu.badmaths;
import seedu.badmaths.ui.Ui;

public class Store {
    private static String toDo;
    private static NotesList notes;
    private static final String filePath = "data/notes.txt";

    public static boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }
    public Store() {
        this.toDo = toDo;
    }

    public static void storeNotes(String toDo) { // this is a method
        try {
            if (isInvalidTodo(toDo)) {
                throw new IllegalTodoException();
            }
            if (toDo.equals("null")) {
                throw new IllegalTodoException();
            }
            notes.add(toDo);
            Ui.printAddNote(toDo, notes.getSize());
            Storage.saveFile(filePath, notes.getAll());
        } catch (IllegalTodoException exception) {
            Ui.printIncorrectFormatEntered();
        }
    }
}

