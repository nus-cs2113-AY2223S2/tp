package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;

import java.rmi.UnexpectedException;

/**
 * Parent class of all types of Commands.
 */
public abstract class Command {

    public Boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing all currently saved tasks.
     * @param ui Prints output messages to user.
     * @param storage Updates the local save file if the TaskList is modified.
     * @throws UnexpectedException If something unexpected occurs.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, ModuleList moduleList) throws UnexpectedException {
    }

    void setExit() {
        isExit = true;
    }

}
