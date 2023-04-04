package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.ExitCommand;


public class ParseByeCommand extends ParseCommand {

    @Override
    public Command parseArguments(String input) {
        return new ExitCommand();
    }
}
