package seedu.rainyDay.data;

import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.Ui;

public class Parser {
    public static Command parseUserInput(String userInput) throws IllegalArgumentException, RainyDayException {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            return addStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            return deleteStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            return generateReport();
            //} else if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
            //    UI.displayHelp();
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
            throw new IllegalArgumentException(Ui.WRONG_ADD_FORMAT);
        }
    }

    public static DeleteCommand deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        if (tokens.length < 2) {
            throw new IllegalArgumentException(Ui.NO_DELETE_INDEX);
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            return new DeleteCommand(index);
        } catch (Exception e) {
            throw new IllegalArgumentException(Ui.WRONG_DELETE_INDEX);
        }
    }

    public static ViewCommand generateReport() {
        return new ViewCommand();
    }
}
