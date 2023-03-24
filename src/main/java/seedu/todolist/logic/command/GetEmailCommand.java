package seedu.todolist.logic.command;

import seedu.todolist.exception.EmptyEmailException;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GetEmailCommand extends Command{
    public static final String KEYWORD = "get_email";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD));
    private int index;

    public GetEmailCommand(HashMap<String, String> args) throws InvalidIndexException {
        try {
            index = Integer.parseInt(args.get(KEYWORD)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
        assert index >= 0 : "Invalid index contained in variable";
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        try {
            String email = taskList.getEmail(index);
            ui.printGetTaskEmailMessage(email);
        } catch (NullPointerException e) {
            throw new EmptyEmailException();
        }
    }
}
