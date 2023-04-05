//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFrequencyException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditConfigCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = { Flags.COMMAND_CONFIG, Flags.CONFIG_CHECK_FREQ,
        Flags.CONFIG_REPEAT_FREQ, Flags.DEFAULT};

    private int checkFrequency = 0;
    private int repeatFrequency = 7;
    private boolean isEditing = false;

    public EditConfigCommand(HashMap<Flags, String> args) throws ToDoListException {
        if (args.containsKey(Flags.COMMAND_CONFIG)) {
            if (args.containsKey(Flags.CONFIG_CHECK_FREQ)) {
                checkFrequency = Integer.parseInt(args.get(Flags.CONFIG_CHECK_FREQ));
                isEditing = true;
            }
            if (args.containsKey(Flags.CONFIG_REPEAT_FREQ)) {
                repeatFrequency = Integer.parseInt(args.get(Flags.CONFIG_REPEAT_FREQ));
                isEditing = true;
            }
            if (checkFrequency < 0 || repeatFrequency < 0) {
                throw new InvalidFrequencyException();
            }
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        if (isEditing) {
            config.setCheckFrequency(checkFrequency);
            config.setRepeatFrequency(repeatFrequency);
            String taskString = config.toString();
            ui.printEditConfigMessage(taskString);
        } else {
            String taskString = config.toString();
            ui.printConfigInfo(taskString);
        }
    }
}
