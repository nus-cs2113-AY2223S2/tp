package seedu.moneymind.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.moneymind.command.ByeCommand;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.command.Command;
import seedu.moneymind.command.DeleteCommand;
import seedu.moneymind.command.EditCommand;
import seedu.moneymind.command.EventCommand;
import seedu.moneymind.command.HelpCommand;
import seedu.moneymind.command.SummaryCommand;
import seedu.moneymind.command.SearchCommand;
import seedu.moneymind.command.ViewCommand;
import seedu.moneymind.exceptions.IntegerOverflowException;
import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.exceptions.InvalidTimeFormatException;
import seedu.moneymind.exceptions.NegativeNumberException;
import seedu.moneymind.exceptions.NonPositiveNumberException;
import static seedu.moneymind.UserDate.isValidDate;

import static seedu.moneymind.string.Strings.BYE;
import static seedu.moneymind.string.Strings.CATEGORY;
import static seedu.moneymind.string.Strings.DELETE;
import static seedu.moneymind.string.Strings.EDIT;
import static seedu.moneymind.string.Strings.EVENT;
import static seedu.moneymind.string.Strings.HELP;
import static seedu.moneymind.string.Strings.INVALID_INPUT;
import static seedu.moneymind.string.Strings.NULL_INPUT_ASSERTION;
import static seedu.moneymind.string.Strings.SEARCH;
import static seedu.moneymind.string.Strings.SUMMARY;
import static seedu.moneymind.string.Strings.VIEW;
import static seedu.moneymind.string.Strings.WHITE_SPACE;
import static seedu.moneymind.string.Strings.EMPTY_STRING;
import static seedu.moneymind.string.Strings.NO_DESCRIPTION_FOR_BYE;
import static seedu.moneymind.string.Strings.NO_DESCRIPTION_FOR_HELP;
import static seedu.moneymind.string.Strings.NO_DESCRIPTION_FOR_SUMMARY;
import static seedu.moneymind.string.Strings.DELETE_REGEX;
import static seedu.moneymind.string.Strings.EDIT_REGEX;
import static seedu.moneymind.string.Strings.CATEGORY_REGEX;
import static seedu.moneymind.string.Strings.EVENT_REGEX;
import static seedu.moneymind.string.Strings.NEGATIVE_INTEGER_DETECTING_REGEX;
import static seedu.moneymind.string.Strings.INTEGER_DETECTING_REGEX;
import static seedu.moneymind.string.Strings.ZERO_MATCHING_REGEX;
import static seedu.moneymind.string.Strings.EMPTY_DELETION;
import static seedu.moneymind.string.Strings.INDEX_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.BUDGET_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.POSITIVE_INTEGER_FOR_EVENT_INDEX;
import static seedu.moneymind.string.Strings.DELETE_FORMAT;
import static seedu.moneymind.string.Strings.EDIT_FORMAT;
import static seedu.moneymind.string.Strings.CATEGORY_FORMAT;
import static seedu.moneymind.string.Strings.EVENT_FORMAT;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY;
import static seedu.moneymind.string.Strings.EMPTY_DESCRIPTION_FOR_CATEGORY;
import static seedu.moneymind.string.Strings.EMPTY_DESCRIPTION_FOR_EVENT;
import static seedu.moneymind.string.Strings.EMPTY_DESCRIPTION_FOR_EDIT;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.EXPENSE_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_INTEGER_FOR_BUDGET;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_INTEGER_FOR_EXPENSE;
import static seedu.moneymind.string.Strings.ENTERING_VALID_TIME_FORMAT_MESSAGE;
import static seedu.moneymind.string.Strings.NO_SEARCH_KEYWORD_MESSAGE;


/**
 * Parses user input strings into corresponding command objects that can be executed by the MoneyMind application.
 */
public class Parser {

    private static final int KEYWORD_INDEX = 0;
    private static final int DESCRIPTION_INDEX = 1;
    private static final int LENGTH_FOR_SINGLE_WORD_COMMAND = 1;
    private static final int MAXIMUM_INDEX = 2;
    private static final int LENGTH_FOR_BYE_COMMAND = 1;
    private static final int LENGTH_FOR_HELP_COMMAND = 1;
    private static final int LENGTH_FOR_SUMMARY_COMMAND = 1;
    private static final int LENGTH_FOR_VIEW_ALL_COMMAND = 1;
    private static final int MINIMUM_LENGTH_FOR_SEARCH_COMMAND = 2;
    private static final int VIEW_CATEGORY_INDEX = 1;
    private static final int DEFAULT_BUDGET = 0;

    /**
     * Parses user input and returns a command object that can be executed by the application.
     *
     * @param input the user input string to parse.
     * @return a command object that corresponds to the keyword in user input.
     * @throws Exception if there was an error while parsing the user input or constructing the corresponding command.
     */
    public Command parseNextCommand(String input) throws Exception {
        assert input != null : NULL_INPUT_ASSERTION;
        String[] separatedKeywordAndDescription = input.split(WHITE_SPACE, MAXIMUM_INDEX);
        String keyword = separatedKeywordAndDescription[KEYWORD_INDEX];

        switch (keyword) {
        case BYE:
            return prepareByeCommand(separatedKeywordAndDescription);
        case HELP:
            return prepareHelpCommand(separatedKeywordAndDescription);
        case SUMMARY:
            return prepareSummaryCommand(separatedKeywordAndDescription);
        case VIEW:
            return prepareViewCommand(separatedKeywordAndDescription);
        case DELETE:
            return prepareDeleteCommand(separatedKeywordAndDescription);
        case EVENT:
            return prepareEventCommand(separatedKeywordAndDescription);
        case CATEGORY:
            return prepareCategoryCommand(separatedKeywordAndDescription);
        case SEARCH:
            return prepareSearchCommand(separatedKeywordAndDescription);
        case EDIT:
            return prepareEditCommand(separatedKeywordAndDescription);
        default:
            throw new InvalidCommandException(INVALID_INPUT);
        }
    }

    /**
     * Constructs a bye command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "bye" keyword,
     *     and the second string should be empty or null.
     * @return a bye command object.
     * @throws InvalidCommandException if the user input contains additional text beyond the "bye" keyword.
     */
    private Command prepareByeCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > LENGTH_FOR_BYE_COMMAND) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            return new ByeCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(NO_DESCRIPTION_FOR_BYE);
        }
    }

    /**
     * Constructs a help command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "help" keyword,
     *     and the second string should be empty or null.
     * @return a help command object.
     * @throws InvalidCommandException if the user input contains additional text beyond the "help" keyword.
     */
    private Command prepareHelpCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > LENGTH_FOR_HELP_COMMAND) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            return new HelpCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(NO_DESCRIPTION_FOR_HELP);
        }
    }

    /**
     * Constructs a summary command object if the user input is valid.
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "summary" keyword,
     *                                       and the second string should be empty or null.
     * @return a summary command object.
     * @throws InvalidCommandException
     */
    private Command prepareSummaryCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            if (separatedKeywordAndDescription.length > LENGTH_FOR_SUMMARY_COMMAND) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            return new SummaryCommand();
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(NO_DESCRIPTION_FOR_SUMMARY);
        }
    }

    /**
     * Constructs a view command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "view" keyword,
     *     and the second string may contain additional text specifying a category name.
     * @return a view command object to view one category if the user input contains a category name, or a view command
     *     object to view all categories if the user input does not contain a category name.
     */
    private Command prepareViewCommand(String[] separatedKeywordAndDescription) {
        if (separatedKeywordAndDescription.length == LENGTH_FOR_VIEW_ALL_COMMAND) {
            return new ViewCommand();
        } else {
            return new ViewCommand(separatedKeywordAndDescription[VIEW_CATEGORY_INDEX]);
        }
    }

    /**
     * Constructs a delete command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "delete" keyword,
     *     and the second string should contain additional text specifying a category name
     *     and an optional event index to delete.
     * @return a delete command object to delete a whole category if the user input contains a category name
     *     but no event index, or a delete command object to delete an event in a category if the user input contains a
     *     category name and an event index.
     * @throws InvalidCommandException if the user input is not in the correct format,
     *     or if there is a bug in the application.
     */
    private Command prepareDeleteCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(DELETE_REGEX);
        if (separatedKeywordAndDescription.length == LENGTH_FOR_SINGLE_WORD_COMMAND) {
            throw new InvalidCommandException(EMPTY_DELETION);
        }
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[DESCRIPTION_INDEX]);
        try {
            if (!matcher.find()) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            String categoryName = matcher.group(1);
            if (matcher.group(2) == null) {
                return new DeleteCommand(categoryName);
            }
            checkNonPositive(matcher.group(2));
            checkBigNumber(matcher.group(2));
            int eventIndex = Integer.parseInt(matcher.group(2));
            // the operation below switches the eventIndex to 0-based indexing
            return new DeleteCommand(categoryName, eventIndex - 1);
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(INDEX_LIMIT_MESSAGE);
        } catch (NumberFormatException | NonPositiveNumberException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_EVENT_INDEX);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    /**
     * Constructs an event command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "event" keyword,
     *     and the second string should contain additional text specifying an event
     *     description, expense, and optional time for recurring events.
     * @return a one time expense event command object if the user input does not contain a time, or a monthly
     *     recurring expense event command object if the user input contains a time.
     * @throws InvalidCommandException if the user input is not in the correct format,
     *     or if there is a bug in the application.
     */
    private Command prepareEventCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(EVENT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[DESCRIPTION_INDEX]);
            if (!matcher.find()) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            String eventName = matcher.group(1);
            checkNegative(matcher.group(2));
            checkBigNumber(matcher.group(2));
            int expenseNumber = Integer.parseInt(matcher.group(2));
            String time = matcher.group(3);
            if (matcher.group(3) == null) {
                return new EventCommand(eventName, expenseNumber);
            }
            checkValidTimeFormat(time);
            return new EventCommand(eventName, expenseNumber, time);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EMPTY_DESCRIPTION_FOR_EVENT);
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(EXPENSE_LIMIT_MESSAGE);
        } catch (NegativeNumberException | NumberFormatException error) {
            throw new InvalidCommandException(NON_NEGATIVE_INTEGER_FOR_EXPENSE);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(EVENT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (InvalidTimeFormatException error) {
            throw new InvalidCommandException(ENTERING_VALID_TIME_FORMAT_MESSAGE);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    /**
     * Constructs a category command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "category" keyword,
     *     and the second string should contain additional text specifying a category name and an optional budget.
     * @return a category command object to add a category with default budget 0
     *     if the user input contains a category name but no budget, or a category command object to add a category
     *     with a budget if the user input contains a category name and a budget.
     * @throws InvalidCommandException if the user input is not in the correct format,
     *     or if there is a bug in the application.
     */
    private Command prepareCategoryCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(CATEGORY_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[DESCRIPTION_INDEX]);
            if (!matcher.find()) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            String categoryName = matcher.group(1);
            if (matcher.group(2) == null) {
                return new CategoryCommand(categoryName, DEFAULT_BUDGET);
            }
            checkNegative(matcher.group(2));
            checkBigNumber(matcher.group(2));
            int budget = Integer.parseInt(matcher.group(2));
            return new CategoryCommand(categoryName, budget);
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(BUDGET_LIMIT_MESSAGE);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCommandException(EMPTY_DESCRIPTION_FOR_CATEGORY);
        } catch (NegativeNumberException | NumberFormatException error) {
            throw new InvalidCommandException(NON_NEGATIVE_INTEGER_FOR_BUDGET);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(CATEGORY_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    /**
     * Constructs a search command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "search" keyword,
     *     and the second string should contain additional text specifying a keyword to search for.
     * @return a search command object to search for a keyword.
     * @throws InvalidCommandException if the user input is not in the correct format,
     *     or if there is a bug in the application.
     */
    private Command prepareSearchCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        if (separatedKeywordAndDescription.length < MINIMUM_LENGTH_FOR_SEARCH_COMMAND) {
            throw new InvalidCommandException(NO_SEARCH_KEYWORD_MESSAGE);
        }
        return new SearchCommand(separatedKeywordAndDescription[DESCRIPTION_INDEX]);
    }

    /**
     * Constructs an edit command object if the user input is valid.
     *
     * @param separatedKeywordAndDescription an array of two strings: the first string should be the "edit" keyword,
     *     and the second string should contain additional text specifying a category name
     *     and an optional event index to edit.
     * @return an edit command object to edit a budget of a category if the user input contains a category name
     *     but no event index, or an edit command object to edit an event expense in a category if the user input
     *     contains a category name and an event index.
     * @throws InvalidCommandException if the user input is not in the correct format,
     *     or if there is a bug in the application.
     */
    private Command prepareEditCommand(String[] separatedKeywordAndDescription) throws Exception {
        Pattern pattern = Pattern.compile(EDIT_REGEX);
        if (separatedKeywordAndDescription.length == LENGTH_FOR_SINGLE_WORD_COMMAND) {
            throw new InvalidCommandException(EMPTY_DESCRIPTION_FOR_EDIT);
        }
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[DESCRIPTION_INDEX]);
        try {
            if (!matcher.find()) {
                throw new InvalidCommandException(EMPTY_STRING);
            }
            String categoryName = matcher.group(1);
            if (matcher.group(2) == null) {
                return new EditCommand(categoryName);
            }
            checkNonPositive(matcher.group(2));
            checkBigNumber(matcher.group(2));
            int eventIndex = Integer.parseInt(matcher.group(2));
            // the operation below switches the eventIndex to 0-based indexing
            return new EditCommand(categoryName, eventIndex - 1);
        } catch (IntegerOverflowException error) {
            throw new InvalidCommandException(INDEX_LIMIT_MESSAGE);
        } catch (NumberFormatException | NonPositiveNumberException error) {
            throw new InvalidCommandException(POSITIVE_INTEGER_FOR_EVENT_INDEX);
        } catch (InvalidCommandException error) {
            throw new InvalidCommandException(EDIT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        } catch (Exception error) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private void checkValidTimeFormat(String time) throws InvalidTimeFormatException {
        if (!isValidDate(time)) {
            throw new InvalidTimeFormatException();
        }
    }

    private void checkNegative(String number) throws NegativeNumberException {
        if (number.matches(NEGATIVE_INTEGER_DETECTING_REGEX)) {
            throw new NegativeNumberException();
        }
    }

    private void checkNonPositive(String number) throws NonPositiveNumberException {
        if (number.matches(NEGATIVE_INTEGER_DETECTING_REGEX) || number.matches(ZERO_MATCHING_REGEX)) {
            throw new NonPositiveNumberException();
        }
    }

    private void checkBigNumber(String number) throws IntegerOverflowException {
        if (number.matches(INTEGER_DETECTING_REGEX) && number.length() > 9) {
            throw new IntegerOverflowException();
        }
    }

}
