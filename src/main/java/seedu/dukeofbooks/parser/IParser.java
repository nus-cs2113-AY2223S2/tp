package seedu.dukeofbooks.parser;

import seedu.dukeofbooks.command.Command;

interface IParser {
    Command parseCommand(String userInput);
}
