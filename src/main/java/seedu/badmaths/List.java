//@@author WilsonLee2000

/**
 * Takes in an index that is being input by users, for displaying of items from notes list.
 * Prints out an item from the notes based on the index that is being provided by users.
 * Alternatively, if index is not provided by users, prints out a list of all items from the notes.
 *
 * @param notes
 * @param toDo
 */

package seedu.badmaths;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.ui.Ui;

public class List {

    public NotesList notes;
    protected String toDo;

    public List(NotesList notes, String toDo) {
        this.notes = notes;
        this.toDo = toDo;
    }

    public boolean isInvalidIndex(int index, NotesList notes) {
        return (index < 0 || index >= notes.getSize());
    }

    public boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }

    public  boolean isAnInt(String todo) {
        try {
            Integer.parseInt(todo);
        } catch (NumberFormatException numberException) {
            Ui.printInvalidNumberEntered();
            return false;
        }
        return true;
    }

    public void listNotes() {
        try {
            if ((!isInvalidTodo(toDo)) && (!isAnInt(toDo))) {
                throw new IllegalTodoException();
            }
            if (isInvalidTodo(toDo)) {
                Ui.printNotes(notes.getAll());
                return;
            }
            int index = Integer.parseInt(toDo) - 1;

            if (isInvalidIndex(index, notes)) {
                throw new IllegalIndexException();
            }
            notes.review(index);
            Ui.printSpecificNote(index, notes.getAll());
        } catch (IllegalIndexException exceptionIndex) {
            System.out.println("Oops! This note does not exist. Please try again.");
        } catch (IllegalTodoException exceptionTodo) {
            Ui.printIncorrectFormatEntered();
        }
    }
}
