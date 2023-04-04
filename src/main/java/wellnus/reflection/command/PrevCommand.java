package wellnus.reflection.command;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectUi;

/**
 * Get the previous set of question generated for review.
 */
public class PrevCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "prev - Get the previously generated set of questions.";
    public static final String COMMAND_USAGE = "usage: prev";
    public static final String COMMAND_KEYWORD = "prev";
    private static final String FEATURE_NAME = "reflect";
    private static final String INVALID_COMMAND_MSG = "Command is invalid.";
    private static final String INVALID_COMMAND_NOTES = "Please check the available commands "
            + "and the format of commands.";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key should be prev.";
    private static final String COMMAND_PAYLOAD_ASSERTION = "The payload should be empty.";
    private static final String MISSING_SET_QUESTIONS = "A set of questions has not been gotten";
    private static final String MISSING_SET_QUESTIONS_NOTES = "Please get a set of questions before viewing them.";
    private static final String INDEX_OUT_OF_BOUND_MSG = "Index is out of bound!!";
    private static final String INDEX_OUT_OF_BOUND_NOTES = "Question index is out of bound (e.g. negative integers, 0)!"
            + "Your data file might be corrupted!!";
    private static final String PAYLOAD = "";
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final Logger LOGGER = Logger.getLogger("ReflectPrevCommandLogger");
    private static final ReflectUi UI = new ReflectUi();
    private QuestionList questionList;

    /**
     * Set up the argument-payload pairs for this command.<br/>
     * Pass in a questionList object from ReflectionManager to access the indexes of the previous set of questions.
     *
     * @param arguments Argument-payload pairs from users
     * @param questionList Object that contains the data about questions
     */
    public PrevCommand(HashMap<String, String> arguments, QuestionList questionList) {
        super(arguments);
        this.questionList = questionList;
    }

    /**
     * Get the command itself.
     *
     * @return Command: prev
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Get the name of the feature in which this prev command is generated.
     *
     * @return Feature name: reflect
     */
    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
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

    /**
     * Validate the command.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: prev
     * <li>Payload must be empty
     * Whichever mismatch will cause the command to be invalid.
     *
     * @param commandMap Argument-Payload map generated by CommandParser
     * @throws BadCommandException If an invalid command is given
     */
    @Override
    public void validateCommand(HashMap<String, String> commandMap) throws BadCommandException {
        if (commandMap.size() != ARGUMENT_PAYLOAD_SIZE) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.containsKey(COMMAND_KEYWORD)) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        } else if (!commandMap.get(COMMAND_KEYWORD).equals(PAYLOAD)) {
            throw new BadCommandException(INVALID_COMMAND_MSG);
        }
        assert getArguments().containsKey(COMMAND_KEYWORD) : COMMAND_KEYWORD_ASSERTION;
        assert getArguments().get(COMMAND_KEYWORD).equals(PAYLOAD) : COMMAND_PAYLOAD_ASSERTION;
    }

    /**
     * Entry point to this command.<br/>
     * Check the validity of commands and add into favorite list.<br/>
     */
    @Override
    public void execute() {
        try {
            validateCommand(getArguments());
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.INFO, INVALID_COMMAND_MSG);
            UI.printErrorFor(badCommandException, INVALID_COMMAND_NOTES);
            return;
        }
        try {
            getPrevSetQuestions();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            LOGGER.log(Level.WARNING, INDEX_OUT_OF_BOUND_MSG);
            UI.printErrorFor(indexOutOfBoundsException, INDEX_OUT_OF_BOUND_NOTES);
        } catch (BadCommandException badCommandException) {
            LOGGER.log(Level.WARNING, MISSING_SET_QUESTIONS);
            UI.printErrorFor(badCommandException, MISSING_SET_QUESTIONS_NOTES);
        }
    }

    /**
     * Get and print the previous set of question generated.
     */
    public void getPrevSetQuestions() throws BadCommandException {
        if (!questionList.hasRandomQuestionIndexes()) {
            UI.printOutputMessage(MISSING_SET_QUESTIONS);
            return;
        }
        String prevSetQuestions = this.questionList.getPrevSetQuestions();
        UI.printOutputMessage(prevSetQuestions);
    }
}
