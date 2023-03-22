package seedu.moneymind.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.exceptions.NegativeNumberException;

import static seedu.moneymind.string.Strings.*;

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
        case SEARCH:
            return createSearchCommand(separatedKeywordAndDescription);
        case "edit":
            return createEditCommand(separatedKeywordAndDescription);
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
        try {
            if (matcher.find()) {
                String categoryName = matcher.group(1);
                if (matcher.group(2) == null) {
                    return new DeleteCommand(categoryName);
                }
                int eventIndex = Integer.parseInt(matcher.group(2));
                checkNegative(eventIndex - 1);
                return new DeleteCommand(categoryName, eventIndex - 1);
            } else {
                throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            }
        } catch (NumberFormatException error) {
            throw new InvalidCommandException("Please give a positive integer for event index");
        } catch (NegativeNumberException error) {
            throw new InvalidCommandException("Please give a positive integer for event index");
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private Command createEventCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(EVENT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String eventName = matcher.group(1);
                int expenseNumber = Integer.parseInt(matcher.group(2));
                String time = matcher.group(3);
                checkNegative(expenseNumber);
                if (matcher.group(3) == null) {
                    return new EventCommand(eventName, expenseNumber);
                }
                return new EventCommand(eventName, expenseNumber, time);
            } else {
                throw new InvalidCommandException("");
            }
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EVENT_EMPTY);
        }  catch (NegativeNumberException error) {
            throw new InvalidCommandException("Please give a positive integer for expense");
        } catch (NumberFormatException error) {
            throw new InvalidCommandException("Please give a positive integer for expense");
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(EVENT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private Command createEditCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(EDIT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String categoryName = matcher.group(1);
                int eventIndex = Integer.parseInt(matcher.group(2));
                checkNegative(eventIndex - 1);
                return new EditCommand(categoryName, eventIndex - 1);
            } else {
                throw new InvalidCommandException("");
            }
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException("OOPS!!! The description of a edit cannot be empty.");
        }  catch (NegativeNumberException error) {
            throw new InvalidCommandException("Please give a positive integer for expense");
        } catch (NumberFormatException error) {
            throw new InvalidCommandException("Please give a positive integer for expense");
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException("wrong format for edit" + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private void checkNegative(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException();
        }
    }

    private Command createCategoryCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(CATEGORY_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String categoryName = matcher.group(1);
                if (matcher.group(2) == null) {
                    return new CategoryCommand(categoryName, 0);
                }
                int budget = Integer.parseInt(matcher.group(2));
                checkNegative(budget);
                return new CategoryCommand(categoryName, budget);
            } else {
                throw new InvalidCommandException("");
            }
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(CATEGORY_EMPTY);
        } catch (NegativeNumberException error) {
            throw new InvalidCommandException("Please give a positive integer for budget");
        } catch (NumberFormatException error) {
            throw new InvalidCommandException("Please give a positive integer for budget");
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(CATEGORY_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private Command createSearchCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        return new SearchCommand(separatedKeywordAndDescription[1]);
    }
}
