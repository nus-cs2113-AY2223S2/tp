//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.InvalidIdException;

import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

import static seedu.todolist.logic.ParserUtil.parseId;

public class DeleteTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_DELETE};

    private boolean isDeletingAll = false;
    //@@author jeromeongithub
    private HashSet<Integer> idHashSet;

    public DeleteTaskCommand(HashMap<Flags, String> args) throws InvalidIdException, InvalidFlagException {
        String idList = args.get(Flags.COMMAND_DELETE);
        if (idList.equals("all")) {
            isDeletingAll = true;
        } else {
            idHashSet = parseId(idList);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        if (isDeletingAll) {
            String confirmationMessage = ui.printConfirmationMessage();
            if (confirmationMessage.equals("Yes")) {
                taskList.deleteAllTasks();
                ui.printDeleteAllMessage();
            } else {
                ui.printCancelDeleteAllMessage();
            }
        } else {
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            for (int id : idHashSet) {
                String taskString = taskList.deleteTask(id);
                stringJoiner.add(taskString);
            }
            ui.printDeleteTaskMessage(stringJoiner.toString());
        }
    }
}
