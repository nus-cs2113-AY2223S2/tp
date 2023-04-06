package seedu.rainyDay.parser;

import seedu.rainyDay.command.HelpCommand;

import java.util.logging.Logger;

//@@author BenjaminPoh
public class ParseHelp {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public static HelpCommand displayHelp(String input) {
        input = input.substring(4);
        return new HelpCommand(input.trim());
    }

}
