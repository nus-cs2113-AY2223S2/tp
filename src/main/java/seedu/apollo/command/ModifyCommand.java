package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;

import java.io.IOException;
import java.rmi.UnexpectedException;

import static seedu.apollo.Parser.COMMAND_DELETE_WORD;
import static seedu.apollo.Parser.COMMAND_MARK_WORD;
import static seedu.apollo.Parser.COMMAND_UNMARK_WORD;

/**
 * Mark and Delete Command class that modifies an existing Task from the TaskList tasks.
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
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= size) {
            throw new NumberFormatException();
        }
        this.command = command;
        this.idx = idx;
    }

    /**
     * Executes the modification of a Task in the TaskList tasks based on data in the class.
     *
     * @param tasks The TaskList of existing Tasks.
     * @param ui Prints success or error message to user.
     * @param storage Gets updated after the TaskList has been modified.
     * @throws UnexpectedException If the command stored is not recognised.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnexpectedException {
        switch(command) {
        case COMMAND_MARK_WORD:
            tasks.get(idx).setDone(true);
            ui.printMarkDone(tasks.get(idx));
            break;
        case COMMAND_UNMARK_WORD:
            tasks.get(idx).setDone(false);
            ui.printMarkNotDone(tasks.get(idx));
            break;
        case COMMAND_DELETE_WORD:
            ui.printDeleted(tasks.get(idx), tasks.size());
            tasks.remove(idx);
            break;
        default:
            throw new UnexpectedException("Modifying Task");
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

}
