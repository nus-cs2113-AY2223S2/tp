package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.HelpCommand;

public class ParseHelpCommand extends ParseCommand {
    @Override
    public Command parseArguments(String input) {
        return new HelpCommand();
    }
}
