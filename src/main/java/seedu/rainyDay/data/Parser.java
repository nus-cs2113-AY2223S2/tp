package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.modules.UI;

public class Parser {
    public static void parseUserInput(String userInput) throws IllegalArgumentException {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            addStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            deleteStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            Command.generateReport(RainyDay.financialReport);
        } else if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
            UI.displayHelp();
        } else {
            UI.unrecognisedInput();
        }
    }

    private static void addStatement(String userInput) {
        try {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            Command.addFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } catch (Exception e) {
            throw new IllegalArgumentException(UI.WRONG_ADD_FORMAT);
        }
    }

    public static void deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        if(tokens.length < 2) {
            throw new IllegalArgumentException(UI.NO_DELETE_INDEX);
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            Command.deleteFinancialStatement(index);
        } catch (Exception e) {
            throw new IllegalArgumentException(UI.WRONG_DELETE_INDEX);
        }
    }
}
