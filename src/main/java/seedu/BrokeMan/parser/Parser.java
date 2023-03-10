package seedu.BrokeMan.parser;

import seedu.BrokeMan.command.AddExpenseCommand;
import seedu.BrokeMan.command.Command;
import seedu.BrokeMan.command.DeleteExpenseCommand;
import seedu.BrokeMan.command.EditExpenseCommand;
import seedu.BrokeMan.command.ExitCommand;
import seedu.BrokeMan.command.InvalidCommand;
import seedu.BrokeMan.command.ListExpenseCommand;
import seedu.BrokeMan.command.SortExpenseCommand;
import seedu.BrokeMan.exception.CostIsNotADoubleException;
import seedu.BrokeMan.exception.IndexNotAnIntegerException;

import static seedu.BrokeMan.common.Messages.*;

public class Parser {

    public static Command parseCommand(String userFullInput) {
        if (!userFullInput.contains(" ")) {
            userFullInput = userFullInput.concat(" dummy");
        }
        String[] userSplitInputs = userFullInput.trim().split(" ", 2);
        String userCommand = userSplitInputs[0];
        String description = userSplitInputs[1];

        switch(userCommand) {
        case AddExpenseCommand.COMMAND_WORD:
            return prepareAddExpenseCommand(description);
        case ListExpenseCommand.COMMAND_WORD:
            return prepareListExpenseCommand();
        case SortExpenseCommand.COMMAND_WORD:
            return prepareSortExpenseCommand();
        case EditExpenseCommand.COMMAND_WORD:
            return prepareEditExpenseCommand(description);
        case DeleteExpenseCommand.COMMAND_WORD:
            return prepareDeleteExpenseCommand(description);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
//        case HelpCommand.COMMAND_WORD:
//        default:
//            return new HelpCommand();
        default:
            return new InvalidCommand("Invalid command word entered");
        }
    }

    private static Command prepareDeleteExpenseCommand(String description) {
        int index;
        try {
            if (description.equals("dummy")) {
                return new InvalidCommand(MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION);
            }

            index = Integer.parseInt(description);
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        return new DeleteExpenseCommand(index);
    }

    private static Command prepareAddExpenseCommand(String description) {
        // description in the form of "a/ <amount> d/ <description> t/ time"

        if (!description.contains("a/ ") || !description.contains(" d/ ") ||
                !description.contains(" t/ ")) {
            return new InvalidCommand(MESSAGE_INVALID_ADD_EXPENSE_COMMAND);
        }

        String[] splitDescriptions = description.split("/ ");
        double amount;

        // handle error for the input "addExpense a/ d/ t/ time"
        // similarly for prepareEditExpenseCommand
        try {
            int length = splitDescriptions[1].length();
            amount = Double.parseDouble(splitDescriptions[1].substring(0, length - 2));
        } catch (NumberFormatException nfe) {
            String errorMessage = new CostIsNotADoubleException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        String expenseDescription = splitDescriptions[2].substring(0, splitDescriptions[2].length() - 2);
        String time = splitDescriptions[3];

        return new AddExpenseCommand(amount, expenseDescription, time);
    }

    private static Command prepareListExpenseCommand() {
        return new ListExpenseCommand();
    }

    private static Command prepareSortExpenseCommand() {
        return new SortExpenseCommand();
    }

    private static Command prepareEditExpenseCommand(String description) {
        if (!description.contains("i/ ") || !description.contains(" t/ ") || !description.contains(" n/ ")) {
            return new InvalidCommand(MESSAGE_INVALID_EDIT_EXPENSE_COMMAND);
        }

        String[] splitDescriptions = description.split("/ ");

        int index;
        try {
            int length = splitDescriptions[1].length();
            index = Integer.parseInt(splitDescriptions[1].substring(0, length - 2));
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        String type = splitDescriptions[2].substring(0, splitDescriptions[2].length() - 2);
        String newExpense = splitDescriptions[3];

        if (isTypeEqualsCost(type)) {
            // do exception handling...
            double newCost = Double.parseDouble(newExpense);
            return new EditExpenseCommand(index, type, newCost);
        }
        return new EditExpenseCommand(index, type, newExpense);
    }

    private static boolean isTypeEqualsCost(String type) {
        return type.equals("cost");
    }
}


