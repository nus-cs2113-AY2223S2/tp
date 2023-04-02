package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.task.Task;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

//@@author KedrianLoh
/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    // @@author clement559
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST,
        Flags.FILTER_DONE, Flags.FILTER_UNDONE, Flags.FILTER_OVERDUE};

    private TaskList filteredTaskList;
    private Predicate<Task> predicate = task -> true;

    /**
     * Constructs an ListTaskCommand object by parsing the provided arguments.
     * Optional parameters are allowed to be null.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If any of the provided arguments are invalid.
     */
    public ListTasksCommand(HashMap<Flags, String> args) throws ToDoListException {
        if (args.containsKey(Flags.FILTER_DONE)) {
            predicate = predicate.and(Task.isDonePredicate());
        }
        if (args.containsKey(Flags.FILTER_UNDONE)) {
            predicate = predicate.and(not(Task.isDonePredicate()));
        }
        if (args.containsKey(Flags.FILTER_OVERDUE)) {
            predicate = predicate.and(Task.isOverdue());
        }
    }

    /**
     * Displays the full or filtered task list, depending on filters chosen.
     */
    public void execute(TaskList taskList, Ui ui) {
        if (predicate == null) {
            ui.printTaskList(taskList.size(), taskList.toString(Task.deadlineComparator));
        } else {
            filteredTaskList = taskList.getFilteredTasks(predicate);
            ui.printTaskList(filteredTaskList.size(), filteredTaskList.toString(Task.deadlineComparator));
        }
    }
}
