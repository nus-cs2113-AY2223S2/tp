package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Config;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditConfigCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_CONFIG, Flags.CONFIG_CHECK_FREQ, Flags.CONFIG_REPEAT_FREQ, Flags.DEFAULT};

    private int checkFrequency = -1;
    private int repeatFrequency = -1;

    public EditConfigCommand(HashMap<Flags, String> args) {
        if (args.containsKey(Flags.COMMAND_CONFIG)) {
            if (args.containsKey(Flags.CONFIG_CHECK_FREQ)) {
                checkFrequency = Integer.parseInt(args.get(Flags.CONFIG_CHECK_FREQ));
            }
            if (args.containsKey(Flags.CONFIG_REPEAT_FREQ)) {
                repeatFrequency = Integer.parseInt(args.get(Flags.CONFIG_REPEAT_FREQ));
            }
        }
    }
    @Override
    public void execute (TaskList Tasklist, Ui ui) {}
    public void execute(Config config, Ui ui) {
        if (checkFrequency != -1) {
            config.setCheckFrequency(checkFrequency);
        }
        if (repeatFrequency != -1) {
            config.setRepeatFrequency(repeatFrequency);
        }
        String taskString = config.toString();
        ui.printEditConfigMessage(taskString);
    }
}
