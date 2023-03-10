package seedu.dukeofbooks.parser;

import static seedu.dukeofbooks.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dukeofbooks.command.Command;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.command.IncorrectCommand;
import seedu.dukeofbooks.command.InventoryCommand;
import seedu.dukeofbooks.command.ListCommand;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

        case InventoryCommand.COMMAND_WORD:
            return prepareInventoryCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
    }

    private Command prepareInventoryCommand(String arguments) {
        return null;
    }
}
