package seedu.duke.general;

import seedu.duke.commands.*;
import seedu.duke.exceptions.DukeException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * The code is adapted from
     * https://github.com/se-edu/addressbook-level2/blob/
     * 157fcf19c6b73289dc4cc7b2dd1152bc2b8e197a/src/seedu/addressbook/parser/Parser
     */

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String ADD_COMMAND_PATTERN_1 =
            "^\\s+-n\\s+\\w+(\\s+\\w+)*\\s+-e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}$";
    private static final String ADD_COMMAND_PATTERN_2 =
            "^\\s+-e\\s+\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}\\s+-n\\s+\\w+(\\s+\\w+)*$";


    public static Command parse(String userInput) throws DukeException {

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

        default:
            return new IncorrectCommand();
        }
    }

    private static Command addFood(String args) {
        boolean isMatched1 = Pattern.matches(ADD_COMMAND_PATTERN_1, args);
        boolean isMatched2 = Pattern.matches(ADD_COMMAND_PATTERN_2, args);

        if (!isMatched1 && !isMatched2) {
            return new IncorrectCommand();
        } else {
            return new AddCommand(args);
        }
    }


}







