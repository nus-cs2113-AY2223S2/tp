package seedu.moneymind.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.exceptions.NegativeNumberException;


import static seedu.moneymind.string.Strings.WHITE_SPACE;
import static seedu.moneymind.string.Strings.BYE;
import static seedu.moneymind.string.Strings.HELP;
import static seedu.moneymind.string.Strings.VIEW;
import static seedu.moneymind.string.Strings.DELETE;
import static seedu.moneymind.string.Strings.EVENT;
import static seedu.moneymind.string.Strings.CATEGORY;
import static seedu.moneymind.string.Strings.INVALID_INPUT;
import static seedu.moneymind.string.Strings.DELETE_FORMAT;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.EVENT_REGEX;
import static seedu.moneymind.string.Strings.EVENT_FORMAT;
import static seedu.moneymind.string.Strings.EVENT_EMPTY;
import static seedu.moneymind.string.Strings.CATEGORY_EMPTY;
import static seedu.moneymind.string.Strings.NULL_INPUT_ASSERTION;
import static seedu.moneymind.string.Strings.NULL_DESCRIPTION;
import static seedu.moneymind.string.Strings.DELETE_REGEX;
import static seedu.moneymind.string.Strings.EMPTY_DELETION;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER;

/**
 * A class to parse the user input.
 */
public class Parser {
    /**
     * Parses the next line and returns a command object, or throws.
     */
    public Command parseNextCommand(String input) throws Exception {
        assert input != null : NULL_INPUT_ASSERTION;
        String[] separatedKeywordAndDescription = input.split(WHITE_SPACE, 2);
        String keyword = separatedKeywordAndDescription[0];

        assert separatedKeywordAndDescription != null : NULL_DESCRIPTION;
        switch (keyword) {
        case BYE:
            return createByeCommand(separatedKeywordAndDescription);
        case HELP:
            return createHelpCommand(separatedKeywordAndDescription);
        case VIEW:
            return createViewCommand(separatedKeywordAndDescription);
        case DELETE:
            return createDeleteCommand(separatedKeywordAndDescription);
        case EVENT:
            return createEventCommand(separatedKeywordAndDescription);
        case CATEGORY:
            return createCategoryCommand(separatedKeywordAndDescription);
        default:
            throw new InvalidCommandException(INVALID_INPUT);
        }
    }

    private Command createByeCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > 1) {
                throw new InvalidCommandException("");
            }
            return new ByeCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException("Bye command should not have any description");
        }
    }

    private Command createHelpCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > 1) {
                throw new InvalidCommandException("");
            }
            return new HelpCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException("Help command should not have any description");
        }
    }

    private Command createViewCommand(String[] separatedKeywordAndDescription) {
        if (separatedKeywordAndDescription.length == 1) {
            return new ViewCommand();
        } else {
            return new ViewCommand(separatedKeywordAndDescription[1]);
        }
    }

    private Command createDeleteCommand(String[] separatedKeywordAndDescription) throws Exception {
        Pattern pattern = Pattern.compile(DELETE_REGEX);
        if (separatedKeywordAndDescription.length == 1) {
            throw new InvalidCommandException(EMPTY_DELETION);
        }
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
        if (matcher.find()) {
            String categoryName = matcher.group(1);
            String eventName = matcher.group(2);
            if (eventName == null) {
                return new DeleteCommand(categoryName);
            } else if (eventName.isEmpty()) {
                throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            }
            return new DeleteCommand(categoryName, eventName);
        } else {
            throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        }
    }

    private Command createEventCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(EVENT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String eventName = matcher.group(1);
                String budgetNumber = matcher.group(2);
                String expenseNumber = matcher.group(3);
                checkNegativeBudgetAndExpense(Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
                return new EventCommand(eventName, Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
            } else {
                throw new InvalidCommandException("");
            }
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EVENT_EMPTY);
        }  catch (NegativeNumberException error) {
            throw new InvalidCommandException(REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(EVENT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private void checkNegativeBudgetAndExpense(int budget, int expense) throws NegativeNumberException {
        if (budget < 0 || expense < 0) {
            throw new NegativeNumberException();
        }
    }

    private Command createCategoryCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            return new CategoryCommand(separatedKeywordAndDescription[1]);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(CATEGORY_EMPTY);
        }
    }
}
