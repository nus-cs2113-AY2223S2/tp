//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFrequencyException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditConfigCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = { Flags.COMMAND_CONFIG,
        Flags.CONFIG_CHECK_FREQ, Flags.CONFIG_REPEAT_FREQ, Flags.RESET};

    private int checkFrequency;
    private int repeatFrequency;
    private boolean shouldReset;

    public EditConfigCommand(HashMap<Flags, String> args) throws InvalidFrequencyException {
        // Use -1 to represent no value as the flag was not in the command
        checkFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_CHECK_FREQ), 0);
        repeatFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_REPEAT_FREQ), 1);
        shouldReset = args.containsKey(Flags.RESET);
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        if (shouldReset) {
            config.reset();
        } else {
            if (checkFrequency != -1) {
                config.setCheckFrequency(checkFrequency);
            }
            if (repeatFrequency != -1) {
                config.setRepeatFrequency(repeatFrequency);
            }
        }
        String taskString = config.toString();
        if (shouldReset || checkFrequency != -1 || repeatFrequency != -1) {
            ui.printEditConfigMessage(taskString);
        } else {
            ui.printConfigInfo(taskString);
        }
    }
}
