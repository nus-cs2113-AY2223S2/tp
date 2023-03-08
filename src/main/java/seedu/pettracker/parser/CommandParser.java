package seedu.pettracker.parser;

import seedu.pettracker.commands.Command;
import seedu.pettracker.commands.ExitCommand;

public class CommandParser {

    public Command parseCommand(String commandString) {
        Command command = getCommand(commandString);
        return command;
    }

    public Command getCommand(String commandString) {
        Command command;
        //only 1 command as of now
        switch (commandString) {
        case "exit":
            command = new ExitCommand();
            break;
        default:
            System.out.println("Unknown Command");
            command = new ExitCommand();
        }

        return command;
    }
}
