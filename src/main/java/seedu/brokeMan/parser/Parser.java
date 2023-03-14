package seedu.brokeMan.parser;

import seedu.brokeMan.command.AddExpenseCommand;
import seedu.brokeMan.command.AddIncomeCommand;
import seedu.brokeMan.command.Command;
import seedu.brokeMan.command.DeleteExpenseCommand;
import seedu.brokeMan.command.DeleteIncomeCommand;
import seedu.brokeMan.command.EditExpenseCommand;
import seedu.brokeMan.command.EditIncomeCommand;
import seedu.brokeMan.command.ExitCommand;
import seedu.brokeMan.command.InvalidCommand;
import seedu.brokeMan.command.ListExpenseCommand;
import seedu.brokeMan.command.ListIncomeCommand;
import seedu.brokeMan.exception.AmountIsNotADoubleException;
import seedu.brokeMan.exception.IndexNotAnIntegerException;

import static seedu.brokeMan.common.Messages.MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION;
import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_EDIT_COMMAND;


/*
Some parts of the code are copied and adapted from TextUI.java of addressbook-level2
with the link to the original code at:
https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */
public class Parser {

    public static Command parseCommand(String userFullInput) {
        if (!userFullInput.contains(" ")) {
            userFullInput = userFullInput.concat(" dummy");
        }
        String[] userSplitInputs = userFullInput.split(" ", 2);
        String userCommand = userSplitInputs[0];
        String description = userSplitInputs[1];

        switch(userCommand) {
        case AddExpenseCommand.COMMAND_WORD:
            return prepareAddCommand(description, "expense");
        case AddIncomeCommand.COMMAND_WORD:
            return prepareAddCommand(description, "income");
        case ListExpenseCommand.COMMAND_WORD:
            return prepareListCommand("expense");
        case ListIncomeCommand.COMMAND_WORD:
            return prepareListCommand("income");
        case EditExpenseCommand.COMMAND_WORD:
            return prepareEditCommand(description, "expense");
        case EditIncomeCommand.COMMAND_WORD:
            return prepareEditCommand(description, "income");
        case DeleteExpenseCommand.COMMAND_WORD:
            return prepareDeleteCommand(description, "expense");
        case DeleteIncomeCommand.COMMAND_WORD:
            return prepareDeleteCommand(description, "income");
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand("Invalid command word entered");
        }
    }

    private static Command prepareDeleteCommand(String description, String type) {
        int index;
        try {
            if (description.equals("dummy")) {
                return new InvalidCommand(MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION);
            }

            assert !description.equals("dummy") : MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION;
            index = Integer.parseInt(description);
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        if (type.equals("expense")) {
            return new DeleteExpenseCommand(index);
        }
        return new DeleteIncomeCommand(index);
    }

    private static Command prepareAddCommand(String description, String type) {
        // description in the form of "a/ <amount> d/ <description> t/ time"

        if (!description.contains("a/ ") || !description.contains(" d/ ") ||
                !description.contains(" t/ ")) {
            return new InvalidCommand(MESSAGE_INVALID_ADD_COMMAND);
        }

        String[] splitDescriptions = description.split("/ ");
        double amount;

        // handle error for the input "addExpense a/ d/ t/ time"
        // similarly for prepareEditExpenseCommand
        try {
            int length = splitDescriptions[1].length();
            amount = Double.parseDouble(splitDescriptions[1].substring(0, length - 2));
        } catch (NumberFormatException nfe) {
            String errorMessage = new AmountIsNotADoubleException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        String newDescription = splitDescriptions[2].substring(0, splitDescriptions[2].length() - 2);
        String time = splitDescriptions[3];

        if (type.equals("expense")) {
            return new AddExpenseCommand(amount, newDescription, time);
        }
        assert type.equals("income") : "Type should be income";
        return new AddIncomeCommand(amount, newDescription, time);
    }

    private static Command prepareListCommand(String type) {
        if (type.equals("expense")) {
            return new ListExpenseCommand();
        }
        return new ListIncomeCommand();
    }

    private static Command prepareEditCommand(String description, String moneyType) {
        if (!description.contains("i/ ") || !description.contains(" t/ ") || !description.contains(" n/ ")) {
            return new InvalidCommand(MESSAGE_INVALID_EDIT_COMMAND);
        }

        String[] splitDescriptions = description.split("/ ");

        assert splitDescriptions.length == 4 : MESSAGE_INVALID_EDIT_COMMAND;
        int index;
        try {
            int length = splitDescriptions[1].length();
            index = Integer.parseInt(splitDescriptions[1].substring(0, length - 2));
        } catch (NumberFormatException nfe) {
            String errorMessage = new IndexNotAnIntegerException().getMessage();
            return new InvalidCommand(errorMessage);
        }

        String type = splitDescriptions[2].substring(0, splitDescriptions[2].length() - 2);
        String newMoney = splitDescriptions[3];

        if (isTypeEqualsCost(type)) {
            // do exception handling to check newMoney is double...
            double newAmount = Double.parseDouble(newMoney);
            if (moneyType.equals("expense")) {
                return new EditExpenseCommand(index, type, newAmount);
            }
            return new EditIncomeCommand(index, type, newAmount);
        }
        if (moneyType.equals("expense")) {
            return new EditExpenseCommand(index, type, newMoney);
        }
        return new EditIncomeCommand(index, type, newMoney);
    }

    private static boolean isTypeEqualsCost(String type) {
        return type.equals("cost") || type.equals("income");
    }
}


