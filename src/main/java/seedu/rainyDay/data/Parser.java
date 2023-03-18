package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger("Parser.log");

    public static Command parseUserInput(String userInput) throws IllegalArgumentException, RainyDayException {
        assert userInput != null : "Failed to read user input!";
        String[] action = userInput.split("\\s+", 2);
        if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
            logger.info("add command executing");
            return addStatement(action[1]);
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
            logger.info("delete command executing");
            return deleteStatement(userInput); //todo: fix this to reduce calls of split.();
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
            logger.info("view command executing");
            return generateReport();
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
            return displayHelp();
        } else {
            logger.warning("unrecognised input from user!");
            throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private static AddCommand addStatement(String userInput) {
        try {
            userInput = userInput.trim();
            String direction;
            if (userInput.substring(0, 3).equalsIgnoreCase("-in")) {
                direction = userInput.substring(1, 3);
                userInput = userInput.substring(3);
            } else if (userInput.substring(0, 4).equalsIgnoreCase("-out")) {
                direction = userInput.substring(1, 4);
                userInput = userInput.substring(4);
            } else {
                throw new IllegalArgumentException();
            }
            String[] data = userInput.split("\\$");
            String description = data[0].trim();
            data = data[1].split("-c");
            int amount = Integer.parseInt(data[0].trim());

            String category = "Default";
            if (data.length >= 2) {
                category = data[1].trim();
            }
            
            return new AddCommand(description, direction, amount, category);
        } catch (Exception e) {
            logger.warning("add command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    public static DeleteCommand deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        try {
            if (tokens.length != 2) {
                logger.warning("invalid delete index from user");
                throw new IllegalArgumentException(ErrorMessage.NO_DELETE_INDEX.toString());
            }
            int index = Integer.parseInt(tokens[1]);
            if (index > RainyDay.financialReport.getStatementCount()) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
            }
            return new DeleteCommand(index);
        } catch (Exception e) {
            logger.warning("delete index provided incorrectly");
            throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
        }
    }

    public static ViewCommand generateReport() {
        return new ViewCommand();
    }

    public static HelpCommand displayHelp() {
        return new HelpCommand();
    }
}
