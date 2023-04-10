package seedu.todolist;

/**
 * The main entry point of the program, only used to start the controller.
 */
public class Main {
    public static void main(String[] args) {
        ToDoListManager controller = new ToDoListManager();
        controller.run();
    }
}

