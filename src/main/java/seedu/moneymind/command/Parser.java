package seedu.moneymind.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.moneymind.exceptions.IntegerOverflowException;
import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.exceptions.NegativeNumberException;

import static seedu.moneymind.string.Strings.EDIT;
import static seedu.moneymind.string.Strings.NULL_INPUT_ASSERTION;
import static seedu.moneymind.string.Strings.WHITE_SPACE;
import static seedu.moneymind.string.Strings.INVALID_INPUT;
import static seedu.moneymind.string.Strings.BYE;
import static seedu.moneymind.string.Strings.HELP;
import static seedu.moneymind.string.Strings.VIEW;
import static seedu.moneymind.string.Strings.DELETE;
import static seedu.moneymind.string.Strings.EVENT;
import static seedu.moneymind.string.Strings.CATEGORY;
import static seedu.moneymind.string.Strings.SEARCH;
import static seedu.moneymind.string.Strings.EMPTY_STRING;
import static seedu.moneymind.string.Strings.NO_DESCRIPTION_FOR_BYE;
import static seedu.moneymind.string.Strings.NO_DESCRIPTION_FOR_HELP;
import static seedu.moneymind.string.Strings.DELETE_REGEX;
import static seedu.moneymind.string.Strings.EVENT_REGEX;
import static seedu.moneymind.string.Strings.CATEGORY_REGEX;
import static seedu.moneymind.string.Strings.EMPTY_DELETION;
import static seedu.moneymind.string.Strings.DELETE_FORMAT;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY;
import static seedu.moneymind.string.Strings.POSITIVE_INTEGER_FOR_EVENT_INDEX;
import static seedu.moneymind.string.Strings.POSITIVE_INTEGER_FOR_BUDGET;
import static seedu.moneymind.string.Strings.POSITIVE_INTEGER_FOR_EXPENSE;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.EVENT_EMPTY;
import static seedu.moneymind.string.Strings.EVENT_FORMAT;
import static seedu.moneymind.string.Strings.EDIT_REGEX;
import static seedu.moneymind.string.Strings.EMPTY_DESCRIPTION_FOR_EDIT;
import static seedu.moneymind.string.Strings.CATEGORY_FORMAT;
import static seedu.moneymind.string.Strings.CATEGORY_EMPTY;
import static seedu.moneymind.string.Strings.EDIT_FORMAT;
import static seedu.moneymind.string.Strings.BUDGET_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.INDEX_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.EXPENSE_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.NO_SEARCH_KEYWORD_MESSAGE;
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
        case EDIT:
            return createEditCommand(separatedKeywordAndDescription);
        default:
            throw new InvalidCommandException(INVALID_INPUT);
        }
    }

    private Command createByeCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > 1) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            return new ByeCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(NO_DESCRIPTION_FOR_BYE);
        }
    }

    private Command createHelpCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > 1) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            return new HelpCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(NO_DESCRIPTION_FOR_HELP);
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
                checkBigNumber(matcher.group(2));
                int eventIndex = Integer.parseInt(matcher.group(2));
                checkNegative(eventIndex - 1);
                return new DeleteCommand(categoryName, eventIndex - 1);
            } else {
                throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            }
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(INDEX_LIMIT_MESSAGE);
        } catch (NumberFormatException | NegativeNumberException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_EVENT_INDEX);
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
                checkBigNumber(matcher.group(2));
                int expenseNumber = Integer.parseInt(matcher.group(2));
                String time = matcher.group(3);
                checkNegative(expenseNumber);
                if (matcher.group(3) == null) {
                    return new EventCommand(eventName, expenseNumber);
                }
                return new EventCommand(eventName, expenseNumber, time);
            } else {
                throw new InvalidCommandException(EMPTY_STRING);
            }
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EVENT_EMPTY);
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(EXPENSE_LIMIT_MESSAGE);
        } catch (NegativeNumberException | NumberFormatException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_EXPENSE);
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
                checkBigNumber(matcher.group(2));
                int eventIndex = Integer.parseInt(matcher.group(2));
                checkNegative(eventIndex - 1);
                return new EditCommand(categoryName, eventIndex - 1);
            } else {
                throw new InvalidCommandException(EMPTY_STRING);
            }
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(INDEX_LIMIT_MESSAGE);
        }catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EMPTY_DESCRIPTION_FOR_EDIT);
        }  catch (NegativeNumberException | NumberFormatException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_EXPENSE);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(EDIT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private void checkNegative(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException();
        }
    }

    private void checkBigNumber(String expense) throws IntegerOverflowException {
        if (expense.length() > 9) {
            throw new IntegerOverflowException();
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
                checkBigNumber(matcher.group(2));
                int budget = Integer.parseInt(matcher.group(2));
                checkNegative(budget);
                return new CategoryCommand(categoryName, budget);
            } else {
                throw new InvalidCommandException(EMPTY_STRING);
            }
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(BUDGET_LIMIT_MESSAGE);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(CATEGORY_EMPTY);
        } catch (NegativeNumberException | NumberFormatException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_BUDGET);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(CATEGORY_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private Command createSearchCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        if (separatedKeywordAndDescription.length < 2) {
            throw new InvalidCommandException(NO_SEARCH_KEYWORD_MESSAGE);
        }

        return new SearchCommand(separatedKeywordAndDescription[1]);
    }
}
