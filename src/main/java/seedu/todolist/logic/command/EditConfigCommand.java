//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFrequencyException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

/**
 * Command class that will display or edit the configuration settings.
 */
public class EditConfigCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = { Flags.COMMAND_CONFIG,
        Flags.CONFIG_CHECK_FREQ, Flags.CONFIG_REPEAT_FREQ, Flags.RESET};

    private int checkFrequency;
    private int repeatFrequency;
    private boolean shouldReset;

    /**
     * Constructs a EditConfigCommand object by parsing the provided arguments. checkFrequency determines the frequency
     * that the program checks for repeating tasks. repeatFrequency determines that frequency that tasks will repeat.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws InvalidFrequencyException If any of the provided frequency values are invalid. (i.e. less than 0)
     */
    public EditConfigCommand(HashMap<Flags, String> args) throws InvalidFrequencyException {
        // Use -1 to represent no value as the flag was not in the command
        checkFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_CHECK_FREQ), 0);
        repeatFrequency = ParserUtil.parseFrequency(args.get(Flags.CONFIG_REPEAT_FREQ), 1);
        shouldReset = args.containsKey(Flags.RESET);
    }

    /**
     * Edits the configuration for the settings to be changed provided in the constructor.
     *
     * @param config The Configuration object whose settings are displayed/updated.
     * @param ui The Ui object used to display the configuration settings.
     */
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
