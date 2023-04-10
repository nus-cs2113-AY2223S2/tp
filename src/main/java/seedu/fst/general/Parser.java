package seedu.fst.general;

import seedu.fst.commands.AddCommand;
import seedu.fst.commands.Command;
import seedu.fst.commands.ExitCommand;
import seedu.fst.commands.FindCommand;
import seedu.fst.commands.HelpCommand;
import seedu.fst.commands.IncorrectCommand;
import seedu.fst.commands.ListCommand;
import seedu.fst.commands.RemoveCommand;
import seedu.fst.commands.UpdateCommand;
import seedu.fst.commands.ClearCommand;
import seedu.fst.exceptions.FSTException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The code is adapted from
 * https://github.com/se-edu/addressbook-level2/blob/
 * 157fcf19c6b73289dc4cc7b2dd1152bc2b8e197a/src/seedu/addressbook/parser/Parser
 */
public class Parser {


    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String ADD_COMMAND_PATTERN_1 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}$";

    private static final String ADD_COMMAND_PATTERN_2 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}" +
                    "\\s+--c\\s+\\w+(\\s+\\w+)*$";
    private static final String ADD_COMMAND_PATTERN_3 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}" +
                    "\\s+--c\\s+\\w+(\\s+\\w+)*" +
                    "\\s+--q\\s+\\d+(\\.\\d+)?$";

    private static final String ADD_COMMAND_PATTERN_4 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}" +
                    "\\s+--q\\s+\\d+(\\.\\d+)?";
    private static final String ADD_COMMAND_PATTERN_5 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}" +
                    "\\s+--q\\s+\\d+(\\.\\d+)?\\s+--u\\s+\\w+(\\s+\\w+)*$";

    private static final String ADD_COMMAND_PATTERN_6 =
            "^\\s+--n\\s+\\w+(\\s+\\w+)*\\s+--e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}" +
                    "\\s+--c\\s+\\w+(\\s+\\w+)*" +
                    "\\s+--q\\s+\\d+(\\.\\d+)?\\s+--u\\s+\\w+(\\s+\\w+)*$";

    /**
     * Returns a Command object according to the user input
     * This methods parse the user input and based on the first command word to return a Command object accordingly
     *
     * @param userInput a String of userInput
     * @return a Command object
     * @throws FSTException throws FSTException if some errors are caught
     */
    public static Command parse(String userInput) throws FSTException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return addFood(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommand(arguments);

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommand(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        default:
            return new IncorrectCommand();
        }
    }

    /**
     * Returns a Command object according to the input string
     * The methods will check the correctness of add command
     * If it is correct it will return a AddCommand object
     * Else it will return a IncorrectCommand object
     *
     * @param args the string of user input
     * @return a Command object
     */
    private static Command addFood(String args) {
        boolean isMatched1 = Pattern.matches(ADD_COMMAND_PATTERN_1, args);
        boolean isMatched2 = Pattern.matches(ADD_COMMAND_PATTERN_2, args);
        boolean isMatched3 = Pattern.matches(ADD_COMMAND_PATTERN_3, args);
        boolean isMatched4 = Pattern.matches(ADD_COMMAND_PATTERN_4, args);
        boolean isMatched5 = Pattern.matches(ADD_COMMAND_PATTERN_5, args);
        boolean isMatched6 = Pattern.matches(ADD_COMMAND_PATTERN_6, args);

        if (!isMatched1 && !isMatched2 && !isMatched3 && !isMatched4 && !isMatched5 && !isMatched6) {
            return new IncorrectCommand();
        } else {
            return new AddCommand(args);
        }
    }
}
