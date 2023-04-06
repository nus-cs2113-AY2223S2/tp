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

    private Integer checkFrequency;
    private Integer repeatFrequency;
    private boolean shouldReset;

    public EditConfigCommand(HashMap<Flags, String> args) throws InvalidFrequencyException {
        checkFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_CHECK_FREQ));
        repeatFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_REPEAT_FREQ));
        shouldReset = args.containsKey(Flags.RESET);
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        if (shouldReset) {
            config.reset();
        } else {
            if (checkFrequency != null) {
                config.setCheckFrequency(checkFrequency);
            }
            if (repeatFrequency != null) {
                config.setRepeatFrequency(repeatFrequency);
            }
        }
        String taskString = config.toString();
        if (shouldReset || checkFrequency != null || repeatFrequency != null) {
            ui.printEditConfigMessage(taskString);
        } else {
            ui.printConfigInfo(taskString);
        }
    }
}
