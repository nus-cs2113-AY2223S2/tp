package wellnus.atomichabit.command;

import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.atomichabit.feature.AtomicHabitList;
import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.command.Command;
import wellnus.common.WellNusLogger;
import wellnus.exception.AtomicHabitException;
import wellnus.exception.BadCommandException;
import wellnus.gamification.util.GamificationData;
import wellnus.gamification.util.GamificationUi;
import wellnus.ui.TextUi;

/**
 * The UpdateCommand class is a command class that updates the number of times a habit
 * has been preformed.<br>
 */
public class UpdateCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "update - Update how many times you've done a habit.";
    public static final String COMMAND_USAGE = "usage: update --id habit-index [--by increment_number]";
    public static final String COMMAND_KEYWORD = "update";
    private static final String COMMAND_INCREMENT_ARGUMENT = "by";
    private static final String COMMAND_INDEX_ARGUMENT = "id";
    private static final int COMMAND_MIN_NUM_OF_ARGUMENTS = 2;
    private static final int COMMAND_MAX_NUM_OF_ARGUMENTS = 3;
    private static final String COMMAND_INVALID_COMMAND_MESSAGE = "Wrong command issued, expected 'update'";
    private static final String COMMAND_INVALID_ARGUMENT_MESSAGE = "Wrong argument issued, expected 'id' and 'by'";
    private static final String DOT = ".";
    private static final int DEFAULT_INCREMENT = 1;
    private static final int ZERO = 0;
    private static final String FEEDBACK_STRING = "The following habit has been incremented! Keep up the good work!";
    private static final String FEEDBACK_STRING_NO_INCREMENT = "The following habit has not been updated! "
            + "Enter a positive integer to update your habit!";
    private static final String FEEDBACK_INDEX_NOT_INTEGER_ERROR = "Invalid input! Please enter an integer";
    private static final String FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR = "Index out of Range! Please enter a valid index";
    private static final String FEEDBACK_DECREMENT_ERROR = "Decrement is too large! Please keep it within range";
    private static final int INDEX_OFFSET = 1;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int MINIMUM_INCREMENT = 1;
    private static final String UPDATE_INVALID_ARGUMENTS_MESSAGE = "Invalid arguments for updating, no update shall "
            + "be performed.";
    private static final String UPDATE_INVALID_INCREMENT_COUNT = "Increment with minimum of 1 is expected, no update "
            + "shall be performed.";
    private static final String REGEX_INTEGER_ONLY_PATTERN = "\\s*-?\\d+\\s*";
    private static final Logger LOGGER = WellNusLogger.getLogger("UpdateAtomicHabitLogger");
    private static final String LOG_STR_INPUT_NOT_INTEGER = "Input string is not an integer."
            + "This should be properly handled";

    private static final String LOG_INDEX_OUT_OF_BOUNDS = "Input index is out of bounds."
            + "This should be properly handled";
    private static final String NO_ADDITIONAL_MESSAGE = "";
    private static final int NUM_OF_XP_PER_INCREMENT = 1;
    private final AtomicHabitList atomicHabits;
    private final GamificationData gamificationData;
    private final TextUi textUi;

    /**
     * Constructs an UpdateCommand object with the given arguments and AtomicHabitList.<br>
     *
     * @param arguments    Argument-Payload map generated by CommandParser.
     * @param atomicHabits The AtomicHabitList object containing habit to be updates.
     */
    public UpdateCommand(HashMap<String, String> arguments, AtomicHabitList atomicHabits,
                         GamificationData gamificationData) {
        super(arguments);
        this.atomicHabits = atomicHabits;
        this.gamificationData = gamificationData;
        this.textUi = new TextUi();
    }

    /**
     * Constructs an UpdateCommand object with the given InputStream, arguments and AtomicHabitList.<br>
     *
     * @param inputStream  An InputStream object representing the input stream to be used.
     * @param arguments    Argument-Payload map generated by CommandParser.
     * @param atomicHabits The AtomicHabitList object containing habit to be updates.
     */
    public UpdateCommand(InputStream inputStream, HashMap<String, String> arguments,
                         AtomicHabitList atomicHabits, GamificationData gamificationData) {
        super(arguments);
        this.atomicHabits = atomicHabits;
        this.gamificationData = gamificationData;
        this.textUi = new TextUi(inputStream);
    }

    private AtomicHabitList getAtomicHabits() {
        return this.atomicHabits;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private int getIncrementCountFrom(HashMap<String, String> arguments)
            throws BadCommandException, NumberFormatException {
        assert arguments.containsKey(UpdateCommand.COMMAND_INCREMENT_ARGUMENT)
                : "--by argument missing for 'hb update' command";
        String incrementCountString = arguments.get(UpdateCommand.COMMAND_INCREMENT_ARGUMENT);
        if (Integer.parseInt(incrementCountString) < MINIMUM_INCREMENT
                && isPositive(Integer.parseInt(incrementCountString))) {
            throw new BadCommandException(UpdateCommand.UPDATE_INVALID_INCREMENT_COUNT);
        }
        return Integer.parseInt(incrementCountString);
    }

    private int getIndexFrom(HashMap<String, String> arguments)
            throws BadCommandException, NumberFormatException {
        if (!arguments.containsKey(UpdateCommand.COMMAND_INDEX_ARGUMENT)) {
            throw new BadCommandException(UpdateCommand.UPDATE_INVALID_ARGUMENTS_MESSAGE);
        }
        String indexString = arguments.get(UpdateCommand.COMMAND_INDEX_ARGUMENT);
        return Integer.parseInt(indexString);
    }

    private int getPositive(int changeCount) {
        if (changeCount < 0) {
            return -changeCount;
        } else {
            return changeCount;
        }
    }

    private boolean isPositive(int changeCount) {
        return changeCount > 0;
    }

    /**
     * Identifies this Command's keyword. Override this in subclasses so
     * toString() returns the correct String representation.
     *
     * @return String Keyword of this Command
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Identifies the feature that this Command is associated with. Override
     * this in subclasses so toString() returns the correct String representation.
     *
     * @return String Keyword for the feature associated with this Command
     */
    @Override
    protected String getFeatureKeyword() {
        return AtomicHabitManager.FEATURE_NAME;
    }

    /**
     * Executes the update command for atomic habits.<br>
     * <p>
     * This command is interactive, so user will continue providing arguments via
     * further prompts provided.
     */
    @Override
    public void execute() throws AtomicHabitException {
        try {
            validateCommand(super.getArguments());
        } catch (BadCommandException badCommandException) {
            getTextUi().printErrorFor(badCommandException, NO_ADDITIONAL_MESSAGE);
            return;
        }
        try {
            int changeCount = DEFAULT_INCREMENT;
            boolean hasLevelUp = false;
            if (super.getArguments().containsKey(UpdateCommand.COMMAND_INCREMENT_ARGUMENT)) {
                changeCount = this.getIncrementCountFrom(super.getArguments());
            }
            int index = this.getIndexFrom(super.getArguments()) - INDEX_OFFSET;
            AtomicHabit habit = getAtomicHabits().getHabitByIndex(index);
            if (changeCount > ZERO) {
                habit.increaseCount(changeCount);
                hasLevelUp = gamificationData.addXp(
                        changeCount * NUM_OF_XP_PER_INCREMENT);
            } else {
                if (getPositive(changeCount) > habit.getCount()) {
                    throw new AtomicHabitException(FEEDBACK_DECREMENT_ERROR);
                }
                habit.decreaseCount(getPositive(changeCount));
            }
            String stringOfUpdatedHabit = (index + 1) + DOT + habit + " " + "[" + habit.getCount() + "]"
                    + LINE_SEPARATOR;
            getTextUi().printOutputMessage(FEEDBACK_STRING + LINE_SEPARATOR
                    + stringOfUpdatedHabit);
            if (hasLevelUp) {
                GamificationUi.printCelebrateLevelUp();
            }
        } catch (NumberFormatException numberFormatException) {
            LOGGER.log(Level.INFO, LOG_STR_INPUT_NOT_INTEGER);
            throw new AtomicHabitException(FEEDBACK_INDEX_NOT_INTEGER_ERROR);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.INFO, LOG_INDEX_OUT_OF_BOUNDS);
            throw new AtomicHabitException(FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR);
        } catch (BadCommandException badCommandException) {
            getTextUi().printErrorFor(badCommandException, NO_ADDITIONAL_MESSAGE);
        }
    }

    /**
     * Validate the arguments and payloads from a commandMap generated by CommandParser.<br>
     * <p>
     * If no exceptions are thrown, arguments are valid.
     *
     * @param arguments Argument-Payload map generated by CommandParser
     * @throws BadCommandException If the arguments have any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        if (!arguments.containsKey(UpdateCommand.COMMAND_KEYWORD)) {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
        if (arguments.get(COMMAND_KEYWORD) != "") {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
        if (arguments.size() < UpdateCommand.COMMAND_MIN_NUM_OF_ARGUMENTS) {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
        if (arguments.size() > UpdateCommand.COMMAND_MAX_NUM_OF_ARGUMENTS) {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_COMMAND_MESSAGE);
        }
        if (!arguments.containsKey(UpdateCommand.COMMAND_INDEX_ARGUMENT)) {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_ARGUMENT_MESSAGE);
        }
        if (arguments.size() == UpdateCommand.COMMAND_MAX_NUM_OF_ARGUMENTS
                && !arguments.containsKey(UpdateCommand.COMMAND_INCREMENT_ARGUMENT)) {
            throw new BadCommandException(UpdateCommand.COMMAND_INVALID_ARGUMENT_MESSAGE);
        }
        if (arguments.containsKey(UpdateCommand.COMMAND_INCREMENT_ARGUMENT)) {
            String incrementString = arguments.get(COMMAND_INCREMENT_ARGUMENT);
            if (incrementString.isBlank()) {
                throw new BadCommandException(UpdateCommand.COMMAND_INVALID_COMMAND_MESSAGE);
            }
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
