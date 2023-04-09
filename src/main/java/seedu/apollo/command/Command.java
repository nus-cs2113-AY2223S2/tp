package seedu.apollo.command;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.LessonType;
import seedu.apollo.storage.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;
import seedu.apollo.utils.LoggerInterface;

import java.rmi.UnexpectedException;
import java.util.logging.Logger;

/**
 * Parent class of all types of Commands.
 */
public abstract class Command implements LoggerInterface {

    protected static Logger logger;
    public Boolean isExit = false;

    public Command(String commandName) {
        logger = Logger.getLogger(commandName);
        setUpLogger(logger);
    }

    /**
     * Executes the command.
     *
     * @param taskList TaskList containing all currently saved tasks.
     * @param ui Prints output messages to user.
     * @param storage Updates the local save file if the TaskList is modified.
     * @throws UnexpectedException If something unexpected occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) throws UnexpectedException {
    }

    protected void setExit() {
        isExit = true;
    }

    /**
     * Returns the LessonType of the command.
     *
     * @param arg Command to be checked.
     * @return LessonType of the command.
     * @throws IllegalCommandException If the command is invalid.
     */
    protected LessonType getLessonType(String arg) throws IllegalCommandException {
        switch (arg) {
        case "-lec":
            return LessonType.LECTURE;
        case "-plec":
            return LessonType.PACKAGED_LECTURE;
        case "-st":
            return LessonType.SECTIONAL_TEACHING;
        case "-dlec":
            return LessonType.DESIGN_LECTURE;
        case "-tut":
            return LessonType.TUTORIAL;
        case "-ptut":
            return LessonType.PACKAGED_TUTORIAL;
        case "-rcit":
            return LessonType.RECITATION;
        case "-lab":
            return LessonType.LABORATORY;
        case "-ws":
            return LessonType.WORKSHOP;
        case "-smc":
            return LessonType.SEMINAR_STYLE_MODULE_CLASS;
        case "-mp":
            return LessonType.MINI_PROJECT;
        case "-tt2":
            return LessonType.TUTORIAL_TYPE_2;
        default:
            throw new IllegalCommandException();
        }
    }

}
