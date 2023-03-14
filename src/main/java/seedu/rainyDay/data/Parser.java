package seedu.rainyDay.data;

import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

public class Parser {
    public static Command parseUserInput(String userInput) throws IllegalArgumentException, RainyDayException {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            return addStatement(userInput);
        } if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            return deleteStatement(userInput);
        } if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            return generateReport();
        } if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
            return displayHelp();
        } else {
            throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private static AddCommand addStatement(String userInput) {
        try {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            return new AddCommand(description, flowDirection, Integer.parseInt(amount));
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    public static DeleteCommand deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        try {
            if (tokens.length < 2) {
                throw new IllegalArgumentException(ErrorMessage.NO_DELETE_INDEX.toString());
            }
            int index = Integer.parseInt(tokens[1]);
            return new DeleteCommand(index);
        } catch (Exception e) {
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
