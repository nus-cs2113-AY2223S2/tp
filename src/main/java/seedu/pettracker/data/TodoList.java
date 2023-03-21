package seedu.pettracker.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoList {
    private static final Logger logger = Logger.getLogger("TodoListLogger");
    private static ArrayList<Todo> todoList = new ArrayList<>();
    private static int numberOfTodos;

    public TodoList() {
        numberOfTodos = 0;
    }

    /**
     * Adds a new task to the todo list
     *
     * @param todoDescription Description of task to be added
     */
    public static void addTodo(String todoDescription) {
        Todo newTodo = new Todo(todoDescription);
        todoList.add(newTodo);
        numberOfTodos += 1;
    }
}
