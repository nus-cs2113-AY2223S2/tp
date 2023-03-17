package seedu.brokeMan.parser;

import seedu.brokeMan.command.AddExpenseCommand;
import seedu.brokeMan.command.AddIncomeCommand;
import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.DeleteExpenseCommand;
import seedu.brokeMan.command.DeleteIncomeCommand;
import seedu.brokeMan.command.EditExpenseCommand;
import seedu.brokeMan.command.EditIncomeCommand;
import seedu.brokeMan.command.ExitCommand;
import seedu.brokeMan.command.HelpCommand;
import seedu.brokeMan.command.InvalidCommand;
import seedu.brokeMan.command.ListExpenseCommand;
import seedu.brokeMan.command.ListIncomeCommand;
import seedu.brokeMan.command.SetBudgetCommand;
import seedu.brokeMan.command.ViewBudgetCommand;
import seedu.brokeMan.exception.AmountIsNotADoubleException;
import seedu.brokeMan.exception.BrokeManException;
import seedu.brokeMan.exception.BudgetNotADoubleException;
import seedu.brokeMan.exception.ContainsEmptyFlagException;
import seedu.brokeMan.exception.IncorrectTypeException;
import seedu.brokeMan.exception.IndexNotAnIntegerException;
import seedu.brokeMan.exception.InvalidAddCommandException;
import seedu.brokeMan.exception.InvalidEditCommandException;
import seedu.brokeMan.exception.NegativeAmountException;
import seedu.brokeMan.exception.hasNotSetBudgetException;

import static seedu.brokeMan.common.Messages.MESSAGE_ARGUMENTS_NOT_SPECIFIED;
import static seedu.brokeMan.common.Messages.MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION;


/*
Some parts of the code are copied and adapted from TextUI.java of addressbook-level2
with the link to the original code at:
https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */

public class Parser {

    public static Command parseCommand(String userFullInput) {

        UserInput userInput = UserInput.splitUserInput(userFullInput);

        switch(userInput.userCommand) {
        case AddExpenseCommand.COMMAND_WORD:
            return prepareAddExpenseCommand(userInput.commandDescription);
        case AddIncomeCommand.COMMAND_WORD:
            return prepareAddIncomeCommand(userInput.commandDescription);
        case ListExpenseCommand.COMMAND_WORD:
            return new ListExpenseCommand();
        case ListIncomeCommand.COMMAND_WORD:
            return new ListIncomeCommand();
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
            return prepareViewBudgetCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD: // fall through
        default:
            return new HelpCommand();
        }
    }

    private static Command prepareViewBudgetCommand() {
        try {
            return new ViewBudgetCommand();
        } catch (hasNotSetBudgetException e) {
            return new InvalidCommand(e.getMessage(), SetBudgetCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareSetBudgetCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand("You did not specify your budget.",
                    SetBudgetCommand.MESSAGE_USAGE);
        }

        double budget;
        try {
            budget = Double.parseDouble(description);
            if (budget < 0) {
                String errorMessage = new NegativeAmountException().getMessage();
                return new InvalidCommand(errorMessage, SetBudgetCommand.MESSAGE_USAGE);
            }
        } catch (NumberFormatException nfe) {
            String errorMessage = new BudgetNotADoubleException().getMessage();
            return new InvalidCommand(errorMessage, SetBudgetCommand.MESSAGE_USAGE);
        }

        return new SetBudgetCommand(budget);
    }

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

    private static Command prepareAddExpenseCommand(String description) {
        // description in the form of "a/ <amount> d/ <description> t/ time"

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

        double amount = Double.parseDouble(splitDescriptions[1]);
        String newDescription = splitDescriptions[2];
        String time = splitDescriptions[3];

        return new AddExpenseCommand(amount, newDescription, time);
    }

    private static Command prepareAddIncomeCommand(String description) {
        // description in the form of "a/ <amount> d/ <description> t/ time"

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

        double amount = Double.parseDouble(splitDescriptions[1]);
        String newDescription = splitDescriptions[2];
        String time = splitDescriptions[3];

        return new AddIncomeCommand(amount, newDescription, time);
    }

private static String[] checkAddCommandException(String description) throws BrokeManException {

        boolean containsAllFlags = description.contains("a/ ") &&
                description.contains(" d/ ") && description.contains(" t/");

        if (!containsAllFlags) {
            throw new InvalidAddCommandException();
        }

        String[] splitDescriptions = description.split("/");

        try {
            int length = splitDescriptions[1].length();
            int length1 = splitDescriptions[2].length();

            splitDescriptions[1] = splitDescriptions[1].substring(0, length - 1).trim();
            splitDescriptions[2] = splitDescriptions[2].substring(0, length1 - 1).trim();
            checkEmptyFlag(splitDescriptions);
            splitDescriptions[3] = splitDescriptions[3].trim();
            Double.parseDouble(splitDescriptions[1]);
        } catch (ContainsEmptyFlagException e) {
            throw new ContainsEmptyFlagException();
        } catch (NumberFormatException nfe) {
            throw new AmountIsNotADoubleException();
        }

        return splitDescriptions;
    }

    private static void checkEmptyFlag(String[] splitDescriptions) throws BrokeManException {
        if (splitDescriptions.length == 3) {
            throw new ContainsEmptyFlagException();
        }

        assert(splitDescriptions.length == 4) : "Invalid input\n";
        for (String description : splitDescriptions) {
            if (description.isEmpty()) {
                throw new ContainsEmptyFlagException();
            }
        }
    }

    private static Command prepareEditExpenseCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    EditExpenseCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;
        double amount = -1;

        try {
            splitDescriptions = checkEditCommandException(description);
            if (splitDescriptions[2].equals("amount")) {
                amount = checkDoubleException(splitDescriptions[3]);
            }
        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), EditExpenseCommand.MESSAGE_USAGE);
        }

        if (amount != -1) {
            return new EditExpenseCommand(Integer.parseInt(splitDescriptions[1]),
                    splitDescriptions[2], amount);
        }
        return new EditExpenseCommand(Integer.parseInt(splitDescriptions[1]),
                splitDescriptions[2], splitDescriptions[3]);
    }

    private static Command prepareEditIncomeCommand(String description) {
        if (description.equals("")) {
            return new InvalidCommand(MESSAGE_ARGUMENTS_NOT_SPECIFIED,
                    EditIncomeCommand.MESSAGE_USAGE);
        }

        String[] splitDescriptions;
        double amount = -1;

        try {
            splitDescriptions = checkEditCommandException(description);
            if (splitDescriptions[2].equals("amount")) {
                amount = checkDoubleException(splitDescriptions[3]);
            }
        } catch (BrokeManException bme) {
            return new InvalidCommand(bme.getMessage(), EditIncomeCommand.MESSAGE_USAGE);
        }

        if (amount != -1) {
            return new EditIncomeCommand(Integer.parseInt(splitDescriptions[1]),
                    splitDescriptions[2], amount);
        }
        return new EditIncomeCommand(Integer.parseInt(splitDescriptions[1]),
                splitDescriptions[2], splitDescriptions[3]);
    }

    private static double checkDoubleException(String cost) throws AmountIsNotADoubleException,
            NegativeAmountException {
        double amount;

        try {
            amount = Double.parseDouble(cost);
        } catch (NumberFormatException nfe) {
            throw new AmountIsNotADoubleException();
        }

        if (amount < 0) {
            throw new NegativeAmountException();
        }

        return amount;
    }

private static String[] checkEditCommandException(String description) throws BrokeManException {

        boolean containsAllFlags = description.contains("i/ ") &&
                description.contains(" t/ ") && description.contains(" n/");

        if (!containsAllFlags) {
            throw new InvalidEditCommandException();
        }

        String[] splitDescriptions = description.split("/");

        int length1 = splitDescriptions[1].length();
        int length2 = splitDescriptions[2].length();

        splitDescriptions[1] = splitDescriptions[1].substring(0, length1 - 1).trim();
        splitDescriptions[2] = splitDescriptions[2].substring(0, length2 - 1).trim();

        checkEmptyFlag(splitDescriptions);
        checkIsIntegerIndex(splitDescriptions[1]);
        checkCorrectType(splitDescriptions[2]);
        splitDescriptions[3] = splitDescriptions[3].trim();

        return splitDescriptions;
    }

    private static void checkIsIntegerIndex(String index) throws BrokeManException {
        try {
            Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            throw new IndexNotAnIntegerException();
        }
    }

    private static void checkCorrectType(String type) throws BrokeManException {
        if (!type.equals("amount") && !type.equals("info") &&
                !type.equals("time")) {
            throw new IncorrectTypeException();
        }
    }
}


