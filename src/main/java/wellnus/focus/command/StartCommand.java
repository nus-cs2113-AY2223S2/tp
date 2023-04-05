package wellnus.focus.command;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.focus.feature.FocusManager;
import wellnus.focus.feature.FocusUi;
import wellnus.focus.feature.Session;

/**
 * Represents a command to start the current session.
 * Also used to start different countdowns timers in the session.
 */
public class StartCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "start - Start your focus session!";
    public static final String COMMAND_USAGE = "usage: start";
    public static final String COMMAND_KEYWORD = "start";
    private static final int COMMAND_NUM_OF_ARGUMENTS = 1;
    private static final int FIRST_COUNTDOWN_INDEX = 0;
    private static final String COMMAND_INVALID_ARGUMENTS_MESSAGE = "Invalid command, expected 'start'";
    private static final String NO_ADDITIONAL_MESSAGE = "";
    private static final String START_MESSAGE = "Your session has started. All the best!";
    private static final String ERROR_NOT_READY = "Nothing to start - your session has started!";
    private final Session session;
    private final FocusUi focusUi;

    /**
     * Constructor for StartCommand object.
     * Session in FocusManager is passed into this class.
     *
     * @param arguments Argument-Payload Hashmap generated by CommandParser
     * @param session   Session object which is an arraylist of Countdowns
     */
    public StartCommand(HashMap<String, String> arguments, Session session) {
        super(arguments);
        this.session = session;
        this.focusUi = new FocusUi();
    }

    /**
     * Identifies this Command's keyword.
     * Override this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword of this Command
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Identifies the feature that this Command is associated with.
     * Override this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword for the feature associated with this Command
     */
    @Override
    protected String getFeatureKeyword() {
        return FocusManager.FEATURE_NAME;
    }

    /**
     * Outputs unique description of the countdown.
     * Starts the session by starting the first countdown.
     * If the session has already started, it will start the next countdown.
     */
    @Override
    public void execute() {
        try {
            validateCommand(super.getArguments());
        } catch (BadCommandException badCommandException) {
            focusUi.printErrorFor(badCommandException, NO_ADDITIONAL_MESSAGE);
            return;
        }
        if (!session.isSessionReady()) {
            focusUi.printOutputMessage(ERROR_NOT_READY);
            return;
        }
        // Forcefully initialise the session again for repeated countdowns
        focusUi.printOutputMessage(START_MESSAGE);
        session.startTimer();
        focusUi.printOutputMessage(session.getSession().get(FIRST_COUNTDOWN_INDEX).getDescription());
    }

    /**
     * Validate the arguments and payloads from a commandMap generated by CommandParser.
     * User input is valid if no exceptions are thrown.
     *
     * @param arguments Argument-Payload map generated by CommandParser
     * @throws BadCommandException If the arguments have any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        if (arguments.size() != COMMAND_NUM_OF_ARGUMENTS) {
            throw new BadCommandException(COMMAND_INVALID_ARGUMENTS_MESSAGE);
        }
        if (!arguments.containsKey(COMMAND_KEYWORD)) {
            throw new BadCommandException(COMMAND_INVALID_ARGUMENTS_MESSAGE);
        }
        if (!arguments.get(COMMAND_KEYWORD).equals("")) {
            throw new BadCommandException(COMMAND_INVALID_ARGUMENTS_MESSAGE);
        }
    }

    /**
     * Method to ensure that developers add in a command usage.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "usage: add --name (name of habit)"
     *
     * @return String of the proper usage of the habit
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Method to ensure that developers add in a description for the command.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "add - add a habit to your list"
     *
     * @return String of the description of what the command does
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
