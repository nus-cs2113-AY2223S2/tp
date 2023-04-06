package seedu.apollo.command.task;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.io.IOException;
import java.rmi.UnexpectedException;

import static seedu.apollo.ui.Parser.COMMAND_DELETE_WORD;
import static seedu.apollo.ui.Parser.COMMAND_MARK_WORD;
import static seedu.apollo.ui.Parser.COMMAND_UNMARK_WORD;

/**
 * Mark and Delete Command class that modifies an existing Task from the TaskList.
 * Handles {@code mark}, {@code unmark}, and {@code delete} commands.
 */
public class ModifyCommand extends Command {

    protected String command;
    protected int idx;

    /**
     * Initialises the class with the type and description of the task given in the command.
     *
     * @param command Type of modification command being executed (mark, unmark, delete).
     * @param param Contains the index of the task to be modified.
     * @param size Current number of tasks in TaskList.
     * @throws NumberFormatException If idx cannot be parsed, or is outside the current range of tasks.
     */
    public ModifyCommand(String command, String param, int size) throws NumberFormatException {
        super("ModifyCommand");
        assert (command.equals(COMMAND_MARK_WORD) | command.equals(COMMAND_UNMARK_WORD) |
                command.equals(COMMAND_DELETE_WORD)) : "ModifyCommand: Invalid Modify Command";
        assert param != null : "ModifyCommand: param cannot be null!";
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= size) {
            throw new NumberFormatException();
        }
        this.command = command;
        this.idx = idx;
    }

    /**
     * Executes the modification of a Task in the TaskList based on data in the class.
     *
     * @param taskList The TaskList of existing Tasks.
     * @param ui Prints success or error message to user.
     * @param storage Gets updated after the TaskList has been modified.
     * @throws UnexpectedException If the command stored is not recognised.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar)
            throws UnexpectedException, IndexOutOfBoundsException, NumberFormatException {
        switch(command) {
        case COMMAND_MARK_WORD:
            markTask(taskList, ui);
            break;
        case COMMAND_UNMARK_WORD:
            unmarkTask(taskList, ui);
            break;
        case COMMAND_DELETE_WORD:
            int initialCount = taskList.size();
            ui.printDeleted(taskList.get(idx), taskList.size());
            taskList.remove(idx);
            int finalCount = taskList.size();
            assert (finalCount == initialCount - 1) : "ModifyCommand : Unsuccessful deletion";
            break;
        default:
            throw new UnexpectedException("Modifying Task");
        }
        try {
            storage.updateTask(taskList);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

    //@@author T-Wan-Lin
    private void markTask(TaskList taskList, Ui ui) throws IndexOutOfBoundsException {
        if (!taskList.get(idx).isDone()){
            taskList.get(idx).setDone(true);
            ui.printMarkDone(taskList.get(idx));
        } else {
            ui.printTaskHasBeenMarkedPreviously();
        }
    }

    private void unmarkTask(TaskList taskList, Ui ui) throws IndexOutOfBoundsException {
        if (taskList.get(idx).isDone()){
            taskList.get(idx).setDone(false);
            ui.printMarkNotDone(taskList.get(idx));
        } else {
            ui.printTaskHasBeenUnmarkedPreviously();
        }
    }

}
