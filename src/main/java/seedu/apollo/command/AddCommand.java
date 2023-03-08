package seedu.apollo.command;

import seedu.apollo.Parser;
import seedu.apollo.Storage;
import seedu.apollo.exception.DateOrderException;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;
import seedu.apollo.exception.InvalidDeadline;
import seedu.apollo.exception.InvalidEvent;

import java.io.IOException;
import java.rmi.UnexpectedException;

import static seedu.apollo.Parser.COMMAND_DEADLINE_WORD;
import static seedu.apollo.Parser.COMMAND_EVENT_WORD;
import static seedu.apollo.Parser.COMMAND_TODO_WORD;

/**
 * Add Command class that adds a Task to the existing TaskList tasks.
 * Handles {@code todo}, {@code deadline}, and {@code event} commands.
 */
public class AddCommand extends Command {

    protected String type;
    protected String desc;
    protected String by;
    protected String from;
    protected String to;

    /**
     * Initialises the class with the type and description of the task given in the command.
     *
     * @param type Type of task being added (ToDo, Deadline, or Event).
     * @param param Description of task given by user (including date(s) for Deadline, Event).
     * @throws InvalidDeadline If the Deadline being added has the wrong format.
     * @throws InvalidEvent If the Event being added has the wrong format.
     * @throws UnexpectedException If the command word cannot be understood.
     */
    public AddCommand(String type, String param) throws InvalidDeadline, InvalidEvent, UnexpectedException {
        this.type = type;
        switch (type) {
        case COMMAND_TODO_WORD:
            this.desc = param;
            break;
        case COMMAND_DEADLINE_WORD:
            String[] paramAndBy = Parser.parseDeadline(param);
            this.desc = paramAndBy[0];
            this.by = paramAndBy[1];
            break;
        case COMMAND_EVENT_WORD:
            String[] paramAndFromTo = Parser.parseEvent(param);
            this.desc = paramAndFromTo[0];
            this.from = paramAndFromTo[1];
            this.to = paramAndFromTo[2];
            break;
        default:
            throw new UnexpectedException("Adding Task");
        }
    }

    /**
     * Executes the adding of a Task to the TaskList tasks based on data in the class.
     *
     * @param tasks The TaskList to be added to.
     * @param ui Prints success or error message to user.
     * @param storage Gets updated after the Task has been added.
     * @throws UnexpectedException If the command stored is not recognised.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnexpectedException {
        switch(type) {
        case COMMAND_TODO_WORD:
            tasks.addToDo(desc);
            break;
        case COMMAND_DEADLINE_WORD:
            tasks.addDeadline(desc, by);
            break;
        case COMMAND_EVENT_WORD:
            try {
                tasks.addEvent(desc, from, to);
            } catch (DateOrderException e) {
                ui.printDateOrderException();
                return;
            }
            break;
        default:
            throw new UnexpectedException("Adding Task");
        }
        ui.printAddMessage(tasks.allTasks.get(tasks.getSize() - 1));
        try {
            storage.update(tasks);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

}
