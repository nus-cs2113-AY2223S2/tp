package seedu.clialgo;

import seedu.clialgo.command.AddCommand;
import seedu.clialgo.command.Command;
import seedu.clialgo.command.ExitCommand;
import seedu.clialgo.command.ExitTestModeCommand;
import seedu.clialgo.command.ExportCommand;
import seedu.clialgo.command.FilterCommand;
import seedu.clialgo.command.HelpCommand;
import seedu.clialgo.command.InvalidCommand;
import seedu.clialgo.command.InvalidTopicCommand;
import seedu.clialgo.command.InvalidImportanceCommand;
import seedu.clialgo.command.InvalidFilterCommand;
import seedu.clialgo.command.ListCommand;
import seedu.clialgo.command.RemoveCommand;
import seedu.clialgo.command.TestModeCommand;
import seedu.clialgo.command.TopoCommand;
import seedu.clialgo.exceptions.parser.EmptyFieldException;
import seedu.clialgo.exceptions.parser.NullInputException;
import seedu.clialgo.logic.TopicManager;

import java.util.ArrayList;
import java.util.Arrays;

//@@author heejet
/**
 * A <code>Parser</code> object is created to make sense of the commands keyed in by the user. It creates the
 * appropriate <code>Command</code> object to execute the user commands.
 */
public class Parser implements StringManipulation {
    /** Delimiters use to separate inputs within commands */
    public static final String NAME_MARKER = "n/";
    public static final String COMMAND_MARKER = "c/";
    public static final String TOPIC_MARKER = "t/";
    public static final String KEYWORD_MARKER = "k/";
    public static final String IMPORTANCE_MARKER = "i/";
    public static final String WHITE_SPACE = " ";

    /** List of valid commands */
    private static final ArrayList<String> COMMANDS = new ArrayList<>(
            Arrays.asList("help", "add", "remove", "filter", "exit", "list", "start-test-mode", "exit-test-mode",
                    "export", "topo")
    );

    /** List of valid keywords */
    private static final ArrayList<String> KEYWORDS = new ArrayList<>(
            Arrays.asList("topic", "importance")
    );

    /**
     * Checks if the input string is a valid command.
     *
     * @param keyWord The input string.
     * @return True if the input string is a valid command, False otherwise.
     */
    public boolean isValidCommand(String keyWord) {
        assert keyWord != null;
        return COMMANDS.contains(keyWord);
    }

    /**
     * Checks if the input string is a valid command.
     *
     * @param keyWord The input string.
     * @return True if the input string is a valid keyword, False otherwise.
     */
    public boolean isValidKeyword(String keyWord) {
        assert keyWord != null;
        return KEYWORDS.contains(keyWord);
    }

    //@@author nikkiDEEE
    /**
     * Checks if the input string is in the valid importance range (1-10).
     *
     * @param keyWord The input string.
     * @return True if the input string is in the valid importance range, False otherwise.
     */
    private boolean isValidImportance(String keyWord) throws NumberFormatException {
        assert keyWord != null;
        int importance = Integer.parseInt(keyWord);
        return importance >= 1 && importance <= 10;
    }

    //@@author heejet
    /**
     * Checks if the correct marker is used by the user.
     *
     * @param userInput The input string by the user.
     * @param correctMarker The correct marker that is supposed to be used.
     * @return True if the marker used by the user matches correctMarker. False otherwise.
     * @throws IndexOutOfBoundsException If length of userInput < length of correctMarker.
     */
    protected boolean isCorrectMarker(String userInput, String correctMarker) throws IndexOutOfBoundsException {
        String markerPresent = userInput.substring(0, correctMarker.length());
        return markerPresent.equals(correctMarker);
    }

    /**
     * Returns a <code>HelpCommand</code> object that teaches the user how to use CLIAlgo when executed.
     * Returns a <code>HelpCommand</code> using the default constructor if description is empty.
     * Returns <code>InvalidCommand</code> when the user does not follow the input format in the user guide.
     *
     * @param description A string indicating the command the user need help with.
     * @return A Command object that teaches the user how to use CLIAlgo when executed.
     */
    private Command prepareHelpCommand(String description) {
        // No description provided, show generic help message.
        if (description == null) {
            return new HelpCommand();
        }
        String command;
        try {
            if (!isCorrectMarker(description, COMMAND_MARKER)) {
                return new InvalidCommand();
            }

            command = StringManipulation.removeMarker(description, COMMAND_MARKER);

            if (!isValidCommand(command)) {
                return new InvalidCommand();
            }
        } catch (IndexOutOfBoundsException | EmptyFieldException | NullInputException e) {
            return new InvalidCommand();
        }
        assert command.length() > 0;
        return new HelpCommand(command);
    }

    /**
     * Returns a <code>AddCommand</code> object that adds a CS2040CFile to CLIAlgo when executed.
     * Returns <code>InvalidCommand</code> when the user does not follow the input format in the user guide.
     *
     * @param description String containing the information on the CS2040CFile to be added.
     * @param topics The topic manager class containing all topics in CLIAlgo.
     * @return a Command object that adds a CS2040CFile to CLIAlgo when executed.
     */
    private Command prepareAddCommand(String description, TopicManager topics) {
        if (description == null) {
            return new InvalidCommand();
        }
        String cs2040cFileName;
        String topicName;
        String importanceField = "";
        int importance;
        try {
            String cs2040cFileNameAndTopicName = StringManipulation.getFirstWord(description, IMPORTANCE_MARKER);
            importanceField = StringManipulation.removeFirstWord(description, IMPORTANCE_MARKER);
            String cs2040cFileNameWithNameMarker = StringManipulation.getFirstWord(cs2040cFileNameAndTopicName,
                    TOPIC_MARKER);
            topicName = StringManipulation.removeFirstWord(cs2040cFileNameAndTopicName, TOPIC_MARKER);

            if (topicName == null || !isCorrectMarker(cs2040cFileNameWithNameMarker, NAME_MARKER)) {
                return new InvalidCommand();
            }

            if (!topics.isValidTopic(topicName)) {
                return new InvalidTopicCommand(topicName);
            }

            cs2040cFileName = StringManipulation.removeMarker(cs2040cFileNameWithNameMarker, NAME_MARKER);

            if (importanceField != null && !isValidImportance(importanceField)) {
                return new InvalidImportanceCommand(importanceField);
            }

            if (importanceField == null) {
                return new AddCommand(cs2040cFileName, topicName);
            }

            importance = Integer.parseInt(importanceField);
        } catch (NullInputException | EmptyFieldException | IndexOutOfBoundsException e) {
            return new InvalidCommand();
        } catch (NumberFormatException e) {
            return new InvalidImportanceCommand(importanceField);
        }

        assert cs2040cFileName.length() > 0;
        assert topicName.length() > 0;

        return new AddCommand(cs2040cFileName, topicName, importance);
    }

    /**
     * Returns a <code>RemoveCommand</code> object that deletes a CS2040CFile from CLIAlgo when executed.
     * Returns <code>NameNotFoundCommand</code> when the user does not key in an existing CS2040CFile name.
     * Returns <code>InvalidCommand</code> when the user does not follow the input format in the user guide.
     *
     * @param description String containing the information on the CS2040CFile to be removed.
     * @return a Command object that deletes a CS2040CFile from CLIAlgo when executed.
     */
    private Command prepareRemoveCommand(String description) {
        if (description == null) {
            return new InvalidCommand();
        }
        String cs2040cFileName;
        try {
            if (!isCorrectMarker(description, NAME_MARKER)) {
                return new InvalidCommand();
            }

            cs2040cFileName = StringManipulation.removeMarker(description, NAME_MARKER);

        } catch (NullInputException | EmptyFieldException e) {
            return new InvalidCommand();
        }
        assert cs2040cFileName.length() > 0;
        return new RemoveCommand(cs2040cFileName);
    }

    /**
     * Returns a <code>FilterCommand</code> object that lists CS2040CFiles according a certain criteria.
     * Returns <code>InvalidCommand</code> when the user does not follow the input format in the user guide.
     *
     * @param description String containing criteria to filter the CS2040CFiles by.
     * @param topics The topic manager class containing all topics in CLIAlgo.
     * @return a Command object that lists CS2040CFiles according a certain criteria.
     */
    private Command prepareFilterCommand(String description, TopicManager topics) {
        if (description == null) {
            return new InvalidCommand();
        }
        String keyWord;
        String topicName;
        try {
            String fullKeyWord = StringManipulation.getFirstWord(description, TOPIC_MARKER);
            topicName = StringManipulation.removeFirstWord(description, TOPIC_MARKER);
            if (fullKeyWord.equals("") || !isCorrectMarker(fullKeyWord, KEYWORD_MARKER)) {
                return new InvalidCommand();
            }
            if (topicName!= null && !topics.isValidTopic(topicName)) {
                return new InvalidTopicCommand(topicName);
            }

            keyWord = StringManipulation.removeMarker(fullKeyWord, KEYWORD_MARKER);

            if (!isValidKeyword(keyWord)) {
                return new InvalidFilterCommand();
            }
        } catch (NullInputException | EmptyFieldException e) {
            return new InvalidCommand();
        }
        return new FilterCommand(keyWord, topicName);
    }

    /**
     * @return A <code>Command</code> object that list out the CS2040CFiles stored in CLIAlgo.
     */
    private Command prepareListCommand(String description) {
        if (description != null) {
            return new InvalidCommand();
        }
        return new ListCommand();
    }

    /**
     * @return A <code>Command</code> object that exits CLIAlgo.
     */
    private Command prepareExitCommand(String description) {
        if (description != null) {
            return new InvalidCommand();
        }
        return new ExitCommand();
    }

    /**
     * @return A <code>Command</code> object that starts test mode.
     */
    private Command prepareTestModeCommand() {
        return new TestModeCommand();
    }

    /**
     * @return A <code>Command</code> object that ends test mode.
     */
    private Command prepareExitTestModeCommand() {
        return new ExitTestModeCommand();
    }

    /**
     * @return A <code>Command</code> object that exports all CS2040CFiles stored in the buffer.
     */
    private Command prepareExport(String description) {
        if (description != null) {
            return new InvalidCommand();
        }
        return new ExportCommand();
    }

    /**
     * Returns a <code>TopoCommand</code> object that lists notes according a topological sort order.
     * Returns <code>InvalidCommand</code> when the user does not follow the input format in the user guide.
     *
     * @param description String containing criteria to filter the notes by.
     * @return a Command object that lists notes according a certain criteria.
     */
    private Command prepareTopoCommand(String description) {
        if (description == null) {
            return new InvalidCommand();
        }

        String noteName;
        try {
            if (description.equals("") || !isCorrectMarker(description, NAME_MARKER)) {
                return new InvalidCommand();
            }
            noteName = StringManipulation.removeMarker(description, NAME_MARKER);
        } catch (NullInputException | EmptyFieldException e) {
            return new InvalidCommand();
        }
        return new TopoCommand(noteName);
    }


    /**
     * This function takes in the command keyword and description and executes the specified command.
     *
     * @param command The command keyword indicating the type of command to execute.
     * @param description The string specifying the details of the command.
     * @param topics The topic manager class containing all topics in CLIAlgo.
     * @return A Command object that executes the command corresponding to the command keyword keyed in by the user.
     */
    private Command prepareCommand(String command, String description, TopicManager topics) {
        assert command != null;
        switch (command) {
        case "help":
            return prepareHelpCommand(description);
        case "add":
            return prepareAddCommand(description, topics);
        case "remove":
            return prepareRemoveCommand(description);
        case "filter":
            return prepareFilterCommand(description, topics);
        case "list":
            return prepareListCommand(description);
        case "start-test-mode":
            return prepareTestModeCommand();
        case "exit-test-mode":
            return prepareExitTestModeCommand();
        case "export":
            return prepareExport(description);
        case "topo":
            return prepareTopoCommand(description);
        default:
            return prepareExitCommand(description);
        }
    }

    /**
     * Returns the appropriate Command object based on the user input.
     *
     * @param fullCommand The full user input.
     * @param topics The topic manager class containing all topics in CLIAlgo.
     * @return A Command objects that suits the user input.
     */
    public Command parse(String fullCommand, TopicManager topics) {
        String command;
        String description;
        try {
            command = StringManipulation.getFirstWord(fullCommand, WHITE_SPACE);
            if (!isValidCommand(command)) {
                return new InvalidCommand();
            }
            description = StringManipulation.removeFirstWord(fullCommand, WHITE_SPACE);
        } catch (NullInputException e) {
            return new InvalidCommand();
        }
        assert command.length() > 0;
        return prepareCommand(command, description, topics);
    }
}
