package seedu.brokeMan.parser;

import seedu.brokeMan.command.AddExpenseCommand;
import seedu.brokeMan.command.AddIncomeCommand;
import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.DeleteExpenseCommand;
import seedu.brokeMan.command.DeleteIncomeCommand;
import seedu.brokeMan.command.EditExpenseCommand;
import seedu.brokeMan.command.EditIncomeCommand;
import seedu.brokeMan.command.ExceedMaximumLengthForAmountException;
import seedu.brokeMan.command.ExitCommand;
import seedu.brokeMan.command.HelpCommand;
import seedu.brokeMan.command.InvalidCommand;
import seedu.brokeMan.command.ListExpenseCommand;
import seedu.brokeMan.command.ListIncomeCommand;
import seedu.brokeMan.command.SetBudgetCommand;
import seedu.brokeMan.command.SortExpenseByAmountCommand;
import seedu.brokeMan.command.SortExpenseByDateCommand;
import seedu.brokeMan.command.SortIncomeByAmountCommand;
import seedu.brokeMan.command.SortIncomeByDateCommand;
import seedu.brokeMan.command.ViewBudgetCommand;
import seedu.brokeMan.entry.Category;
import seedu.brokeMan.exception.AmountIsNotADoubleException;
import seedu.brokeMan.exception.BrokeManException;
import seedu.brokeMan.exception.BudgetNotADoubleException;
import seedu.brokeMan.exception.CategoryNotCorrectException;
import seedu.brokeMan.exception.ContainDuplicatedFlagException;
import seedu.brokeMan.exception.ContainsEmptyFlagException;
import seedu.brokeMan.exception.IncorrectTypeException;
import seedu.brokeMan.exception.IndexNotAnIntegerException;
import seedu.brokeMan.exception.InvalidAddCommandException;
import seedu.brokeMan.exception.InvalidDateTimeException;
import seedu.brokeMan.exception.InvalidEditCommandException;
import seedu.brokeMan.exception.InvalidMonthTimeException;
import seedu.brokeMan.exception.InvalidOptionalTimeFlagException;
import seedu.brokeMan.exception.NegativeAmountException;
import seedu.brokeMan.exception.NewDescriptionContainFlagsException;
import seedu.brokeMan.exception.WrongFlagOrderException;
import seedu.brokeMan.exception.hasNotSetBudgetException;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static seedu.brokeMan.common.Messages.MESSAGE_ARGUMENTS_NOT_SPECIFIED;
import static seedu.brokeMan.common.Messages.MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION;
import static seedu.brokeMan.parser.StringToCategory.convertStringToCategory;


/*
Some parts of the code are copied and adapted from TextUI.java of addressbook-level2
with the link to the original code at:
https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */

public class Parser {

    private static final DecimalFormat df = new DecimalFormat("0.00");


    /**
     * parse the inputs entered by user into command for execution
     *
     * @param userFullInput full line of user input entered by user
     * @return Command class based on the user's input
     */
    public static Command parseCommand(String userFullInput) {

        UserInput userInput = UserInput.splitUserInput(userFullInput);

        switch (userInput.userCommand) {
        case AddExpenseCommand.COMMAND_WORD:
            return prepareAddExpenseCommand(userInput.commandDescription);
        case AddIncomeCommand.COMMAND_WORD:
            return prepareAddIncomeCommand(userInput.commandDescription);
        case ListExpenseCommand.COMMAND_WORD:
            return prepareListExpenseCommand(userInput.commandDescription);
        case ListIncomeCommand.COMMAND_WORD:
            return prepareListIncomeCommand(userInput.commandDescription);
        case EditExpenseCommand.COMMAND_WORD:
            return prepareEditExpenseCommand(userInput.commandDescription);
        case EditIncomeCommand.COMMAND_WORD:
            return prepareEditIncomeCommand(userInput.commandDescription);
        case DeleteExpenseCommand.COMMAND_WORD:
            return prepareDeleteExpenseCommand(userInput.commandDescription);
        case DeleteIncomeCommand.COMMAND_WORD:
            return prepareDeleteIncomeCommand(userInput.commandDescription);
        case SetBudgetCommand.COMMAND_WORD:
            return prepareSetBudgetCommand(userInput.commandDescription);
        case ViewBudgetCommand.COMMAND_WORD:
            return prepareViewBudgetCommand(userInput.commandDescription);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case SortExpenseByAmountCommand.COMMAND_WORD:
            return new SortExpenseByAmountCommand();
        case SortExpenseByDateCommand.COMMAND_WORD:
            return new SortExpenseByDateCommand();
        case SortIncomeByAmountCommand.COMMAND_WORD:
            return new SortIncomeByAmountCommand();
        case SortIncomeByDateCommand.COMMAND_WORD:
            return new SortIncomeByDateCommand();
        case HelpCommand.COMMAND_WORD: // fall through
        default:
            return new HelpCommand();
        }
    }

    /**
     * Prepares the view budget command
     *
     * @return the prepared command
     */
    private static Command prepareViewBudgetCommand(String description) {
        String newDescription;
        try {
            if (description.equals("")) {
                return new ViewBudgetCommand();
            }
            newDescription = checkValidOptionalTimeFlagException(description);
            return new ViewBudgetCommand(newDescription);
        } catch (hasNotSetBudgetException e) {
            return new InvalidCommand(e.getMessage(), SetBudgetCommand.MESSAGE_USAGE);
        } catch (InvalidOptionalTimeFlagException | InvalidMonthTimeException ex) {
            return new InvalidCommand(ex.getMessage(), ViewBudgetCommand.MESSAGE_USAGE);
        }
    }

    /**
     * checks if the input string has a valid time flag and description
     * @param description the input string to be checked
     * @return the time string
     * @throws InvalidOptionalTimeFlagException custom exception to indicate invalid time flag
     * @throws InvalidMonthTimeException custom exception to indicate invalid time description
     */
    private static String checkValidOptionalTimeFlagException(String description)
            throws InvalidOptionalTimeFlagException, InvalidMonthTimeException {
        if (!description.startsWith("t/ ")) {
            throw new InvalidOptionalTimeFlagException();
        }
        String newDescription = description.substring(3).trim();
        try {
            StringToTime.checkIfValidDateString(newDescription);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidMonthTimeException();
        }
        return newDescription;
    }

    /**
     * Parses description in the context of set budget command
     *
     * @param description full command description string
     * @return the prepared command
     */
    private static Command prepareSetBudgetCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand("You did not specify your budget.",
                    SetBudgetCommand.MESSAGE_USAGE);
        }

        assert(!description.equals("")) : "You did not specify your budget\n";
        String[] descriptionByWord = description.split(" t/ ");
        if (descriptionByWord.length > 2 || descriptionByWord.length < 1) {
            return new InvalidCommand("Invalid information entered", SetBudgetCommand.MESSAGE_USAGE);
        }

        double budget;
        String budgetInString = descriptionByWord[0];

        try {
            if (budgetInString.length() > 10) {
                String errorMessage = new ExceedMaximumLengthForAmountException().getMessage();
                return new InvalidCommand(errorMessage, SetBudgetCommand.MESSAGE_USAGE);
            }
            budget = Double.parseDouble(budgetInString);
            budget = Double.parseDouble(df.format(budget));
            if (budget < 0) {
                String errorMessage = new NegativeAmountException().getMessage();
                return new InvalidCommand(errorMessage, SetBudgetCommand.MESSAGE_USAGE);
            }
        } catch (NumberFormatException nfe) {
            String errorMessage = new BudgetNotADoubleException().getMessage();
            return new InvalidCommand(errorMessage, SetBudgetCommand.MESSAGE_USAGE);
        }

        if (descriptionByWord.length == 2) {
            try {
                StringToTime.checkIfValidDateString(descriptionByWord[1]);
            } catch (DateTimeParseException dtpe) {
                return new InvalidCommand("Invalid Date Format!\n" +
                        "|  Years starting from 0001 and months from 01 to 12 are accepted!",
                        SetBudgetCommand.MESSAGE_USAGE);
            }
            descriptionByWord[1] = descriptionByWord[1].trim();
            return (descriptionByWord[1] == "" ? new SetBudgetCommand(budget)
                    : new SetBudgetCommand(budget, descriptionByWord[1]));
        }
        return new SetBudgetCommand(budget);
    }

    /**
     * Parses the description in the context of delete expense command
     *
     * @param description full command description string
     * @return the prepared command
     */
    private static Command prepareDeleteExpenseCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION,
                    DeleteExpenseCommand.MESSAGE_USAGE);
        }

        int index;
        try {
            index = Integer.parseInt(description);
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage, DeleteExpenseCommand.MESSAGE_USAGE);
        }

        return new DeleteExpenseCommand(index);
    }

    /**
     * Parses the command description in the context for delete income command
     *
     * @param description full command description string
     * @return the prepared command
     */
    private static Command prepareDeleteIncomeCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION,
                    DeleteIncomeCommand.MESSAGE_USAGE);
        }

        int index;
        try {
            index = Integer.parseInt(description);
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage, DeleteIncomeCommand.MESSAGE_USAGE);
        }

        return new DeleteIncomeCommand(index);
    }

    /**
     * Parses the command description in the context for add expense command
     *
     * @param description full command description string
     * @return the prepared command
     */
    private static Command prepareAddExpenseCommand(String description) {
        // description in the form of "a/ <amount> d/ <description> t/ time c/ category"

        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    AddExpenseCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;
        try {
            splitDescriptions = checkAddCommandException(description);
        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), AddExpenseCommand.MESSAGE_USAGE);
        }

        double amount = Double.parseDouble(splitDescriptions[0]);
        String newDescription = splitDescriptions[1];
        LocalDateTime time = StringToTime.convertStringToTime(splitDescriptions[2]);
        Category category;
        try {
            category = convertStringToCategory(splitDescriptions[3]);
        } catch (CategoryNotCorrectException e) {
            throw new RuntimeException(e);
        }


        return new AddExpenseCommand(amount, newDescription, time, category);
    }

    /**
     * Parses the command description in the context for add income command
     *
     * @param description full command description string
     * @return the prepared command
     */
    private static Command prepareAddIncomeCommand(String description) {
        // description in the form of "a/ <amount> d/ <description> t/ time c/ category"

        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    AddIncomeCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;
        try {
            splitDescriptions = checkAddCommandException(description);
        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), AddIncomeCommand.MESSAGE_USAGE);
        }

        double amount = Double.parseDouble(splitDescriptions[0]);
        String newDescription = splitDescriptions[1];
        LocalDateTime time = StringToTime.convertStringToTime(splitDescriptions[2]);
        Category category;
        try {
            category = convertStringToCategory(splitDescriptions[3]);
        } catch (CategoryNotCorrectException e) {
            throw new RuntimeException(e);
        }

        return new AddIncomeCommand(amount, newDescription, time, category);
    }

    /**
     * Parses the command description in the context for the context of add command and throws exceptions if found
     *
     * @param description full command description string
     * @return the split command descriptions
     * @throws BrokeManException the custom exception for specific exception case
     */
    public static String[] checkAddCommandException(String description) throws BrokeManException {
        description = " " + description + " ";

        boolean containsAllFlags = description.contains(" a/ ") &&
                description.contains(" d/ ") && description.contains(" t/ ")
                && description.contains(" c/");

        if (!containsAllFlags) {
            throw new InvalidAddCommandException();
        }

        boolean isFlagInCorrectOrder = description.indexOf("a/") < description.indexOf("d/") &&
                description.indexOf("d/") < description.indexOf("t/") &&
                description.indexOf("t/") < description.indexOf("c/");
        if (!isFlagInCorrectOrder) {
            throw new WrongFlagOrderException();
        }

        boolean hasDuplicatedFlags = (description.indexOf(" a/ ") != description.lastIndexOf(" a/ ")) ||
                (description.indexOf(" d/ ") != description.lastIndexOf(" d/ ")) ||
                (description.indexOf(" t/ ") != description.lastIndexOf(" t/ ")) ||
                (description.indexOf(" c/ ") != description.lastIndexOf(" c/ "));
        if (hasDuplicatedFlags) {
            throw new ContainDuplicatedFlagException();
        }

        assert(isFlagInCorrectOrder) : "|  flag is not in order";

        String[] splitDescriptions = new String[4];
        splitDescriptions[0] = description.substring(description.indexOf("a/") + 2,
                description.indexOf("d/"));
        splitDescriptions[1] = description.substring(description.indexOf("d/") + 2,
                description.indexOf("t/"));
        splitDescriptions[2] = description.substring(description.indexOf("t/") + 2,
                description.indexOf("c/"));
        splitDescriptions[3] = description.substring(description.indexOf("c/") + 2,
                description.length());

        splitDescriptions[0] = splitDescriptions[0].trim();
        splitDescriptions[1] = splitDescriptions[1].trim();
        splitDescriptions[2] = splitDescriptions[2].trim();
        splitDescriptions[3] = splitDescriptions[3].trim();

        checkEmptyAddFlag(splitDescriptions);
        checkDoubleException(splitDescriptions[0]);
        splitDescriptions[0] = checkExceedMaxCharForAmount(splitDescriptions[0]);
        checkTimeException(splitDescriptions[2]);
        convertStringToCategory(splitDescriptions[3]);
        return splitDescriptions;
    }

    private static String checkExceedMaxCharForAmount(String description) throws ExceedMaximumLengthForAmountException {
        double amount = Double.parseDouble(description);
        double maxAmountAllowed = 9999999999.99D;
        if (amount > maxAmountAllowed) {
            throw new ExceedMaximumLengthForAmountException();
        }
        return df.format(amount);
    }

    private static Command prepareListExpenseCommand(String description) {
        if (description.equals("")) {
            return new ListExpenseCommand();
        }
        String newDescription;
        try {
            newDescription = checkValidOptionalTimeFlagException(description);
        } catch (InvalidOptionalTimeFlagException | InvalidMonthTimeException e) {
            return new InvalidCommand(e.getMessage(), ListExpenseCommand.MESSAGE_USAGE);
        }
        return new ListExpenseCommand(newDescription);
    }

    private static Command prepareListIncomeCommand(String description) {
        if (description.equals("")) {
            return new ListIncomeCommand();
        }
        String newDescription;
        try {
            newDescription = checkValidOptionalTimeFlagException(description);
        } catch (InvalidOptionalTimeFlagException | InvalidMonthTimeException e) {
            return new InvalidCommand(e.getMessage(), ListIncomeCommand.MESSAGE_USAGE);
        }
        return new ListIncomeCommand(newDescription);
    }

    /**
     * Checks if the descriptions of the flags are empty
     *
     * @param splitDescriptions split command descriptions that contains the description of flags
     * @throws ContainsEmptyFlagException custom exception to indicate flag descriptions is / are empty
     */
    private static void checkEmptyAddFlag(String[] splitDescriptions) throws ContainsEmptyFlagException {
        assert (splitDescriptions.length == 4) : "Invalid input\n";
        for (String description : splitDescriptions) {
            if (description.isEmpty()) {
                throw new ContainsEmptyFlagException();
            }
        }
    }

    private static void checkEmptyFlag(String[] splitDescriptions) throws ContainsEmptyFlagException {
        assert (splitDescriptions.length == 3) : "Invalid input\n";
        for (String description : splitDescriptions) {
            if (description.isEmpty()) {
                throw new ContainsEmptyFlagException();
            }
        }
    }

    /**
     * Parses the command description in the context for edit expense command
     *
     * @param description full command description string
     * @return the prepared edit expense command
     */
    private static Command prepareEditExpenseCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    EditExpenseCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;

        try {
            splitDescriptions = checkEditCommandException(description);
        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), EditExpenseCommand.MESSAGE_USAGE);
        }

        return new EditExpenseCommand(Integer.parseInt(splitDescriptions[0]),
                splitDescriptions[1], splitDescriptions[2]);
    }

    /**
     * Parses the command description in the context for edit income command
     *
     * @param description full command description string
     * @return the prepared edit income command
     */
    private static Command prepareEditIncomeCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    EditIncomeCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;

        try {
            splitDescriptions = checkEditCommandException(description);

        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), EditIncomeCommand.MESSAGE_USAGE);
        }

        return new EditIncomeCommand(Integer.parseInt(splitDescriptions[0]),
                splitDescriptions[1], splitDescriptions[2]);
    }

    /**
     * Checks if the input is a valid date and time
     * @param timeToCheck the input to be checked
     * @throws InvalidDateTimeException custom exception to indicate invalid data and time input
     */
    private static void checkTimeException(String timeToCheck) throws InvalidDateTimeException {
        try {
            StringToTime.convertStringToTime(timeToCheck);
        } catch (DateTimeException dte) {
            throw new InvalidDateTimeException();
        }
    }


    /**
     * Checks if the input string to a double greater than 0
     *
     * @param cost input string to be converted
     * @throws AmountIsNotADoubleException custom exception to indicate string input
     *                                     cannot be converted to a double
     * @throws NegativeAmountException     custom exception to indicate negative double value
     */
    private static void checkDoubleException(String cost) throws AmountIsNotADoubleException,
            NegativeAmountException {
        double amount;

        try {
            amount = Double.parseDouble(cost);
            if (amount <= 0) {
                throw new NegativeAmountException();
            }
        } catch (NumberFormatException nfe) {
            throw new AmountIsNotADoubleException();
        }
    }


    /**
     * Parses the command description in the context for the context of
     * delete command and throws exceptions if found
     *
     * @param description full command description string
     * @return the split command descriptions
     * @throws BrokeManException the custom exception that indicate the specific exception cases
     */
    private static String[] checkEditCommandException(String description) throws BrokeManException {
        description = " " + description + " ";

        boolean containsAllFlags = description.contains(" i/ ") &&
                description.contains(" t/ ") && description.contains(" n/");
        if (!containsAllFlags) {
            throw new InvalidEditCommandException();
        }
        boolean isFlagInOrder = description.indexOf("i/") < description.indexOf("t/") &&
                description.indexOf("t/") < description.indexOf("n/");
        if (!isFlagInOrder) {
            throw new WrongFlagOrderException();
        }

        boolean hasDuplicatedFlags = (description.indexOf(" i/ ") != description.lastIndexOf(" i/ ")) ||
                (description.indexOf(" t/ ") != description.lastIndexOf(" t/ ")) ||
                (description.indexOf(" n/ ") != description.lastIndexOf(" n/ "));
        if (hasDuplicatedFlags) {
            throw new ContainDuplicatedFlagException();
        }

        String[] splitDescriptions = new String[3];
        splitDescriptions[0] = description.substring(description.indexOf("i/") + 2,
                description.indexOf("t/"));
        splitDescriptions[1] = description.substring(description.indexOf("t/") + 2,
                description.indexOf("n/"));
        splitDescriptions[2] = description.substring(description.indexOf("n/") + 2,
                description.length());

        splitDescriptions[0] = splitDescriptions[0].trim();
        splitDescriptions[1] = splitDescriptions[1].trim();
        splitDescriptions[2] = splitDescriptions[2].trim();

        checkEmptyFlag(splitDescriptions);
        checkIsIntegerIndex(splitDescriptions[0]);
        checkCorrectType(splitDescriptions[1]);
        if (splitDescriptions[1].equals("category")) {
            convertStringToCategory(splitDescriptions[2]);
        } else if (splitDescriptions[1].equals("amount")) {
            checkDoubleException(splitDescriptions[2]);
            splitDescriptions[2] = checkExceedMaxCharForAmount(splitDescriptions[2]);
        } else if (splitDescriptions[1].equals("time")) {
            checkTimeException((splitDescriptions[2]));
        } else if (splitDescriptions[1].equals("info")) {
            checkContainFlagsException(splitDescriptions[2]);
        }

        return splitDescriptions;
    }

    private static void checkContainFlagsException(String newDescription) throws NewDescriptionContainFlagsException {
        newDescription = " " + newDescription + " ";
        boolean isContainFlags = newDescription.contains(" c/ ") ||
                newDescription.contains(" a/ ") || newDescription.contains(" d/ ");
        if (isContainFlags) {
            throw new NewDescriptionContainFlagsException();
        }
    }

    /**
     * Checks if string input is an integer
     *
     * @param index the input string to be checked
     * @throws IndexNotAnIntegerException custom exception to indicate that
     *                                    input string cannot be converted to integer
     */
    private static void checkIsIntegerIndex(String index) throws IndexNotAnIntegerException {
        try {
            Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            throw new IndexNotAnIntegerException();
        }
    }

    /**
     * Checks if the type is correct (amount, info, or time)
     * @param type input to be checked
     * @throws IncorrectTypeException custom exception to indicate incorrect type exception
     */
    private static void checkCorrectType(String type) throws IncorrectTypeException {
        if (!type.equals("amount") && !type.equals("info") &&
                !type.equals("time") && !type.equals("category")) {
            throw new IncorrectTypeException();
        }
    }
}
