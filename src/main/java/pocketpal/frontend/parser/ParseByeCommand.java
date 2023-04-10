package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.ExitCommand;


public class ParseByeCommand extends ParseCommand {

    /**
     * Returns an ExitCommand object to be executed by the backend. The ExitCommand
     * will terminate the program.
     *
     * @param input User input after the bye command.
     * @return Command ExitCommand object to be executed.
     */
    @Override
    public Command parseArguments(String input) {
        return new ExitCommand();
    }
}
