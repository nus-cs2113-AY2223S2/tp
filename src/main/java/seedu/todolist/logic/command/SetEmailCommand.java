package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEmailFormatException;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.exception.ToDoListException;

import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.util.regex.Pattern;

public class SetEmailCommand extends Command {
    public static final String KEYWORD = "set_email";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD, Flags.EMAIL.FLAG));
    public String email;
    private int index;

    public SetEmailCommand(HashMap<String, String> args)  throws ToDoListException {
        email = args.get(Flags.EMAIL.FLAG);
        if (!isValidEmail(email)) {
           throw new InvalidEmailFormatException();
        }
        try {
            index = Integer.parseInt(args.get(KEYWORD)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        String taskString = taskList.setEmail(index, email);
        assert !taskString.isEmpty() : "Conversion of task string failed";
        ui.printSetEmailMessage(taskString);
    }
}
