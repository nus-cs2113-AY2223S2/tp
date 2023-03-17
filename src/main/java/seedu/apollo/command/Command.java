package seedu.apollo.command;

import seedu.apollo.storage.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.rmi.UnexpectedException;

/**
 * Parent class of all types of Commands.
 */
public abstract class Command {

    public Boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param taskList TaskList containing all currently saved tasks.
     * @param ui Prints output messages to user.
     * @param storage Updates the local save file if the TaskList is modified.
     * @throws UnexpectedException If something unexpected occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules)
            throws UnexpectedException {
    }

    protected void setExit() {
        isExit = true;
    }

}
