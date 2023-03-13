package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.UI;
import seedu.rainyDay.command.Command;

public class Parser {
    public static void parseUserInput(String userInput) throws Exception {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            Command.addFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } else if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            String[] tokens = userInput.split("\\s+");
            int index = Integer.parseInt(tokens[1]);
            Command.deleteFinancialStatement(index);
        } else if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            Command.generateReport(RainyDay.financialReport);
        } else if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
            UI.displayHelp();
        } else {
            UI.unrecognisedInput();
        }
    }
}
