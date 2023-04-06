//@@author WilsonLee2000

package seedu.badmaths;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.ui.Ui;

public class List {

    public NotesList notes;
    protected  String toDo;

    public List(NotesList notes, String toDo) {
        this.notes = notes;
        this.toDo = toDo;
    }
    public  boolean isInvalidIndex(int index, NotesList notes) {
        return (index < 0 || index >= notes.getSize());
    }
    public  boolean isInvalidTodo(String todo) {
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

            if (isInvalidTodo(toDo)) { // means there is no toDo
                Ui.printNotes(notes.getAll()); // print all
                return;
            }
            int index = Integer.parseInt(toDo) - 1;

            if (isInvalidIndex(index, notes)) { // list 10 if got 5 notes only
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
