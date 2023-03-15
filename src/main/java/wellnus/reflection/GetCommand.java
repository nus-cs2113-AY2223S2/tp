package wellnus.reflection;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GetCommand extends Command {
    private static final int NUM_OF_RANDOM_QUESTIONS = 5;
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String COMMAND_KEYWORD = "get";
    private static final String FULL_DESCRIPTION = "";
    private static final String ARGUMENT = "get";
    private static final String PAYLOAD = "";
    private static final int ARGUMENT_PAYLOAD_SIZE = 1;
    private static final int MIN_SIZE = 0;
    private static final String INVALID_COMMAND_MSG = "Command is invalid.";
    private static final String INVALID_COMMAND_NOTES = "Please check the available commands "
            + "and the format of commands.";
    private static final String EMPTY_ARGUMENT_PAYLOAD_ASSERTION = "The argument-payload pair cannot be empty!";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key should be get.";
    private static final String COMMAND_PAYLOAD_ASSERTION = "The payload should be empty.";
    private static final String NUM_SELECTED_QUESTIONS_ASSERTION = "The number of selected questions should be 5.";
    private static final ReflectUi UI = new ReflectUi();
    private HashMap<String, String> argumentPayload;

    public GetCommand(HashMap<String, String> arguments) throws BadCommandException {
        super(arguments);
        this.argumentPayload = getArguments();
        assert !argumentPayload.isEmpty() : EMPTY_ARGUMENT_PAYLOAD_ASSERTION;
    }

    /**
     * Entry point to this command.<br/>
     * Trigger the generation of five random questions and print to users.<br/>
     */
    @Override
    public void execute() {
        try {
            validateCommand(this.argumentPayload);
        } catch (BadCommandException invalidCommandException) {
            UI.printErrorFor(invalidCommandException, INVALID_COMMAND_NOTES);
        }
        assert argumentPayload.containsKey(COMMAND_KEYWORD) : COMMAND_KEYWORD_ASSERTION;
        assert argumentPayload.get(COMMAND_KEYWORD).equals(PAYLOAD) : COMMAND_PAYLOAD_ASSERTION;
        String outputString = convertQuestionsToString();
        UI.printOutputMessage(outputString);
    }

    /**
     * Get a random set of 5 reflection questions.
     *
     * @return The selected sets of random questions
     */
    public ArrayList<ReflectionQuestion> getRandomQuestions() {
        ArrayList<ReflectionQuestion> selectedQuestions = new ArrayList<>();
        ArrayList<ReflectionQuestion> questions = SelfReflection.getQuestions();
        Set<Integer> fiveRandomNumbers = generateRandomNumbers(questions.size());
        for (int index : fiveRandomNumbers) {
            selectedQuestions.add(questions.get(index));
        }
        assert selectedQuestions.size() == NUM_OF_RANDOM_QUESTIONS : NUM_SELECTED_QUESTIONS_ASSERTION;
        return selectedQuestions;
    }

    /**
     * Get the command itself.
     *
     * @return Command: get
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    protected HashMap<String, String> getArgumentPayload() {
        return argumentPayload;
    }

    /**
     * Only one supported argument for get command.
     *
     * @return Argument: get
     */
    @Override
    protected String getSupportedCommandArguments() {
        return ARGUMENT;
    }

    /**
     * Get detailed description of a get command.
     * TODO: FULL_DESCRIPTION is not completed yet.
     *
     * @return Full description of get command
     */
    @Override
    protected String getDetailedDescription() {
        return FULL_DESCRIPTION;
    }

    /**
     * Get the name of the feature in which this get command is generated.
     *
     * @return Self reflection
     */
    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
    }

    /**
     * Validate the command and return a boolean value.<br/>
     * <br/>
     * Conditions for command to be valid:<br/>
     * <li>Only one argument-payload pair
     * <li>The pair contains key: get
     * <li>Payload is empty
     * Whichever mismatch will cause the command to be invalid.
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
    }

    /**
     * Generate an array of 5 random numbers, <br/>
     * <br/>
     * Each number num: num >= 0 and num <= (maxSize - 1)
     *
     * @param maxSize Number of questions available to be chosen
     * @return Array of 5 random numbers
     */
    private Set<Integer> generateRandomNumbers(int maxSize) {
        Set<Integer> randomNumbers = new Random().ints(MIN_SIZE, maxSize - 1)
                .distinct()
                .limit(NUM_OF_RANDOM_QUESTIONS)
                .boxed()
                .collect(Collectors.toSet());
        assert randomNumbers.size() == NUM_OF_RANDOM_QUESTIONS : NUM_SELECTED_QUESTIONS_ASSERTION;
        return randomNumbers;
    }

    /**
     * Convert all five questions to a single string to be printed.
     *
     * @return Single string that consists of all questions
     */
    private String convertQuestionsToString() {
        ArrayList selectedQuestions = getRandomQuestions();
        String questionString = "";
        for (int i = 0; i < selectedQuestions.size(); i += 1) {
            questionString += (Integer.toString(i + 1) + selectedQuestions.get(i).toString()
                    + System.lineSeparator());
        }
        return questionString;
    }
}

